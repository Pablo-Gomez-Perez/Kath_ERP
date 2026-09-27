package com.kathsoft.kathpos.tools;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.PosixFilePermission;
import java.security.GeneralSecurityException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Properties;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.kathsoft.kathpos.app.model.configuracion.ParametrosDeConexion;

/**
 * Persistencia cifrada de la configuración JDBC del usuario actual.
 *
 * <p>Almacena `database.properties` cifrado íntegramente con AES-256-GCM y
 * la clave aleatoria en `connection.key`. Ambos archivos se protegen con
 * permisos de propietario cuando POSIX está disponible. La ruta por SO es:
 * Windows: APPDATA/KathERP; macOS: Library/Application Support/KathERP;
 * Linux: XDG_CONFIG_HOME/kath-erp o ~/.config/kath-erp.</p>
 *
 * <p>Esta medida evita la lectura casual del archivo, pero no constituye una
 * bóveda de credenciales: quien pueda leer los dos archivos puede descifrarlo.
 * No se debe distribuir ni sincronizar `connection.key`.</p>
 */
public final class ConfiguracionConexionDBService {

    private static final byte[] CABECERA = "KATHDB01".getBytes(StandardCharsets.US_ASCII);
    private static final int LONGITUD_CLAVE = 32;
    private static final int LONGITUD_NONCE = 12;
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final Set<PosixFilePermission> PERMISOS_DIRECTORIO = EnumSet.of(
            PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE,
            PosixFilePermission.OWNER_EXECUTE);
    private static final Set<PosixFilePermission> PERMISOS_ARCHIVO = EnumSet.of(
            PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE);

    private final Path directorio;

    public ConfiguracionConexionDBService() {
        this(obtenerDirectorioSistema());
    }

    /**
     * Constructor con directorio explícito para pruebas automatizadas.
     *
     * @param directorio ruta donde se encuentran archivo cifrado y clave
     */
    public ConfiguracionConexionDBService(Path directorio) {
        this.directorio = directorio.toAbsolutePath().normalize();
    }

    public Path rutaArchivo() {
        return directorio.resolve("database.properties");
    }

    private Path rutaClave() {
        return directorio.resolve("connection.key");
    }

    public boolean existe() {
        return Files.isRegularFile(rutaArchivo());
    }

    /**
     * Lee los parámetros cifrados y autentica su contenido antes de usarlo.
     *
     * @return configuración previamente guardada
     * @throws IOException si faltan archivos, la configuración se dañó o se
     *                     produjo un error de acceso
     */
    public ParametrosDeConexion cargar() throws IOException {
        if (Files.isSymbolicLink(rutaArchivo()) || Files.isSymbolicLink(rutaClave())) {
            throw new IOException("No se admiten enlaces simbólicos en los archivos de conexión");
        }
        byte[] datos = Files.readAllBytes(rutaArchivo());
        if (datos.length < CABECERA.length + LONGITUD_NONCE + 16) {
            throw new IOException("Formato de configuración inválido");
        }
        for (int i = 0; i < CABECERA.length; i++) {
            if (datos[i] != CABECERA[i]) {
                throw new IOException("Formato de configuración no reconocido");
            }
        }
        byte[] clave = leerClave();
        byte[] nonce = Arrays.copyOfRange(datos, CABECERA.length,
                CABECERA.length + LONGITUD_NONCE);
        byte[] cifrado = Arrays.copyOfRange(datos,
                CABECERA.length + LONGITUD_NONCE, datos.length);
        try {
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(clave, "AES"),
                    new GCMParameterSpec(128, nonce));
            byte[] texto = cipher.doFinal(cifrado);
            Properties properties = new Properties();
            try (ByteArrayInputStream input = new ByteArrayInputStream(texto)) {
                properties.load(input);
            } finally {
                Arrays.fill(texto, (byte) 0);
            }
            ParametrosDeConexion parametros = new ParametrosDeConexion(
                    properties.getProperty("db.host"),
                    Integer.parseInt(properties.getProperty("db.port", "0")),
                    properties.getProperty("db.name"),
                    properties.getProperty("db.user"),
                    properties.getProperty("db.password"),
                    properties.getProperty("db.params", ""));
            parametros.validar();
            return parametros;
        } catch (GeneralSecurityException | IllegalArgumentException ex) {
            throw new IOException("La configuración no se pudo descifrar o contiene valores inválidos", ex);
        } finally {
            Arrays.fill(clave, (byte) 0);
        }
    }

    /**
     * Guarda un nuevo fichero cifrado sin dejar archivos temporales con
     * credenciales en claro. El cambio de fichero se realiza atómicamente
     * cuando lo permite el sistema de archivos.
     *
     * @param parametros configuración validada
     * @throws IOException si falla el cifrado o la escritura
     */
    public void guardar(ParametrosDeConexion parametros) throws IOException {
        parametros.validar();
        if (Files.isSymbolicLink(directorio) || Files.isSymbolicLink(rutaArchivo())
                || Files.isSymbolicLink(rutaClave())) {
            throw new IOException("No se admiten enlaces simbólicos en la ubicación de configuración");
        }
        Files.createDirectories(directorio);
        protegerDirectorio(directorio);
        byte[] clave = obtenerOCrearClave();
        Path temporal = null;
        try {
            Properties properties = new Properties();
            properties.setProperty("db.host", parametros.host().trim());
            properties.setProperty("db.port", Integer.toString(parametros.port()));
            properties.setProperty("db.name", parametros.name().trim());
            properties.setProperty("db.user", parametros.user().trim());
            properties.setProperty("db.password", parametros.password());
            properties.setProperty("db.params", parametros.params().trim());

            byte[] nonce = new byte[LONGITUD_NONCE];
            RANDOM.nextBytes(nonce);
            Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(clave, "AES"),
                    new GCMParameterSpec(128, nonce));

            byte[] plano;
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                properties.store(out, "Kath ERP - configuración de conexión cifrada");
                plano = out.toByteArray();
            }
            byte[] cifrado;
            try {
                cifrado = cipher.doFinal(plano);
            } finally {
                Arrays.fill(plano, (byte) 0);
            }
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                out.write(CABECERA);
                out.write(nonce);
                out.write(cifrado);
                temporal = Files.createTempFile(directorio, "database-", ".tmp");
                protegerArchivo(temporal);
                Files.write(temporal, out.toByteArray());
            }
            try {
                Files.move(temporal, rutaArchivo(), StandardCopyOption.ATOMIC_MOVE,
                        StandardCopyOption.REPLACE_EXISTING);
            } catch (AtomicMoveNotSupportedException ignored) {
                Files.move(temporal, rutaArchivo(), StandardCopyOption.REPLACE_EXISTING);
            }
            temporal = null;
            protegerArchivo(rutaArchivo());
        } catch (GeneralSecurityException ex) {
            throw new IOException("No fue posible cifrar la configuración de conexión", ex);
        } finally {
            Arrays.fill(clave, (byte) 0);
            if (temporal != null) {
                Files.deleteIfExists(temporal);
            }
        }
    }

    private byte[] obtenerOCrearClave() throws IOException {
        if (Files.exists(rutaClave())) {
            return leerClave();
        }
        byte[] nueva = new byte[LONGITUD_CLAVE];
        RANDOM.nextBytes(nueva);
        try {
            Files.write(rutaClave(), nueva, java.nio.file.StandardOpenOption.CREATE_NEW);
            protegerArchivo(rutaClave());
            return nueva;
        } catch (java.nio.file.FileAlreadyExistsException ex) {
            Arrays.fill(nueva, (byte) 0);
            return leerClave();
        } catch (IOException ex) {
            Arrays.fill(nueva, (byte) 0);
            throw ex;
        }
    }

    private byte[] leerClave() throws IOException {
        byte[] clave = Files.readAllBytes(rutaClave());
        if (clave.length != LONGITUD_CLAVE) {
            Arrays.fill(clave, (byte) 0);
            throw new IOException("La clave de configuración es inválida");
        }
        return clave;
    }

    private static void protegerDirectorio(Path path) throws IOException {
        if (Files.getFileStore(path).supportsFileAttributeView("posix")) {
            Files.setPosixFilePermissions(path, PERMISOS_DIRECTORIO);
        }
    }

    private static void protegerArchivo(Path path) throws IOException {
        if (Files.getFileStore(path).supportsFileAttributeView("posix")) {
            Files.setPosixFilePermissions(path, PERMISOS_ARCHIVO);
        }
    }

    /**
     * Resuelve una carpeta reproducible y privada del usuario, independiente
     * de dónde se haya instalado o desde dónde se ejecute el JAR.
     */
    public static Path obtenerDirectorioSistema() {
        String override = System.getProperty("kath.config.dir");
        if (override != null && !override.isBlank()) {
            return Paths.get(override);
        }
        String sistema = System.getProperty("os.name", "").toLowerCase(java.util.Locale.ROOT);
        String home = System.getProperty("user.home");
        if (sistema.contains("win")) {
            String appdata = System.getenv("APPDATA");
            return appdata != null && !appdata.isBlank()
                    ? Paths.get(appdata, "KathERP")
                    : Paths.get(home, "AppData", "Roaming", "KathERP");
        }
        if (sistema.contains("mac")) {
            return Paths.get(home, "Library", "Application Support", "KathERP");
        }
        String xdg = System.getenv("XDG_CONFIG_HOME");
        return xdg != null && !xdg.isBlank()
                ? Paths.get(xdg, "kath-erp")
                : Paths.get(home, ".config", "kath-erp");
    }
}
