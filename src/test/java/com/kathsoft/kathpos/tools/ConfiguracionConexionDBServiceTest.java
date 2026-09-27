package com.kathsoft.kathpos.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import com.kathsoft.kathpos.app.model.configuracion.ParametrosDeConexion;

class ConfiguracionConexionDBServiceTest {

    @TempDir
    Path temporal;

    @Test
    void cifraYDescifraArchivoSinGuardarContraseniaEnClaro() throws IOException {
        ConfiguracionConexionDBService almacenamiento = new ConfiguracionConexionDBService(temporal);
        ParametrosDeConexion datos = datos("servidor-remoto.example.test", "credencial-ficticia-de-prueba");

        assertFalse(almacenamiento.existe());
        almacenamiento.guardar(datos);

        assertTrue(almacenamiento.existe());
        assertEquals("database.properties", almacenamiento.rutaArchivo().getFileName().toString());
        assertEquals(datos, almacenamiento.cargar());

        String contenido = Files.readString(almacenamiento.rutaArchivo(), StandardCharsets.ISO_8859_1);
        assertFalse(contenido.contains("credencial-ficticia-de-prueba"));
        assertFalse(contenido.contains("db.password"));
        assertTrue(Files.isRegularFile(temporal.resolve("connection.key")));
    }

    @Test
    void cadaGuardadoUtilizaUnNonceDiferente() throws IOException {
        ConfiguracionConexionDBService almacenamiento = new ConfiguracionConexionDBService(temporal);
        ParametrosDeConexion datos = datos("localhost", "clave-ficticia");
        almacenamiento.guardar(datos);
        byte[] primero = Files.readAllBytes(almacenamiento.rutaArchivo());
        almacenamiento.guardar(datos);
        byte[] segundo = Files.readAllBytes(almacenamiento.rutaArchivo());

        assertNotEquals(java.util.Arrays.toString(primero), java.util.Arrays.toString(segundo));
        assertEquals(datos, almacenamiento.cargar());
    }

    @Test
    void rechazaConfiguracionAlterada() throws IOException {
        ConfiguracionConexionDBService almacenamiento = new ConfiguracionConexionDBService(temporal);
        almacenamiento.guardar(datos("localhost", "clave-ficticia"));
        byte[] contenido = Files.readAllBytes(almacenamiento.rutaArchivo());
        contenido[contenido.length - 1] ^= 1;
        Files.write(almacenamiento.rutaArchivo(), contenido);

        assertThrows(IOException.class, almacenamiento::cargar);
    }

    @Test
    void rechazaSiFaltaClaveCifrado() throws IOException {
        ConfiguracionConexionDBService almacenamiento = new ConfiguracionConexionDBService(temporal);
        almacenamiento.guardar(datos("localhost", "clave-ficticia"));
        Files.delete(temporal.resolve("connection.key"));

        assertThrows(IOException.class, almacenamiento::cargar);
    }

    @Test
    void rechazaValoresObligatoriosInvalidosAntesDeEscribir() {
        ConfiguracionConexionDBService almacenamiento = new ConfiguracionConexionDBService(temporal);
        ParametrosDeConexion puertoInvalido = new ParametrosDeConexion(
                "localhost", 0, "kath_erp", "usuario", "", "");
        ParametrosDeConexion hostInvalido = new ParametrosDeConexion(
                "localhost/otra", 3306, "kath_erp", "usuario", "", "");
        ParametrosDeConexion baseInvalida = new ParametrosDeConexion(
                "localhost", 3306, "kath_erp?otro=1", "usuario", "", "");

        assertThrows(IllegalArgumentException.class, () -> almacenamiento.guardar(puertoInvalido));
        assertThrows(IllegalArgumentException.class, () -> almacenamiento.guardar(hostInvalido));
        assertThrows(IllegalArgumentException.class, () -> almacenamiento.guardar(baseInvalida));
        assertFalse(almacenamiento.existe());
    }

    private static ParametrosDeConexion datos(String servidor, String contrasenia) {
        return new ParametrosDeConexion(servidor, 3306, "kath_erp",
                "usuario_de_prueba", contrasenia, "serverTimezone=UTC&useSSL=true");
    }
}
