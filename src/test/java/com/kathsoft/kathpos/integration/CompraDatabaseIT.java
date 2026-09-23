package com.kathsoft.kathpos.integration;

import java.time.Duration;

import org.junit.jupiter.api.BeforeAll;
import org.testcontainers.containers.output.OutputFrame;
import org.testcontainers.mysql.MySQLContainer;
import org.testcontainers.utility.MountableFile;

/**
 * Base de datos compartida para las pruebas de integración del módulo de compras.
 *
 * <p>Se inicia explícitamente una sola vez por JVM. Esto evita que cada clase cree
 * una instancia idéntica de MySQL y deja en el log de CI la salida del servidor si
 * falla la inicialización de los scripts SQL.</p>
 */
abstract class CompraDatabaseIT {

    protected static final String DATABASE_NAME = "kath_erp";

    private static final MySQLContainer<?> MYSQL = new MySQLContainer<>("mysql:8.0.39")
            .withDatabaseName(DATABASE_NAME)
            .withUsername("kath_test")
            .withPassword("kath_test")
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource("db/init/schema.sql"),
                    "/docker-entrypoint-initdb.d/01-schema.sql")
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource("db/init/procedures/procedures_articulos.sql"),
                    "/docker-entrypoint-initdb.d/02-procedures-articulos.sql")
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource("db/init/procedures/procedures_compras.sql"),
                    "/docker-entrypoint-initdb.d/03-procedures-compras.sql")
            .withCopyFileToContainer(
                    MountableFile.forClasspathResource("db/fixtures/compra_minima.sql"),
                    "/docker-entrypoint-initdb.d/04-compra-minima.sql")
            .withStartupAttempts(1)
            .withStartupTimeout(Duration.ofMinutes(2))
            .withLogConsumer(CompraDatabaseIT::registrarLogMySql);

    @BeforeAll
    static void iniciarBaseDeDatos() {
        if (!MYSQL.isRunning()) {
            MYSQL.start();
        }
    }

    protected static String jdbcUrl() {
        return MYSQL.getJdbcUrl();
    }

    protected static String username() {
        return MYSQL.getUsername();
    }

    protected static String password() {
        return MYSQL.getPassword();
    }

    protected static String host() {
        return MYSQL.getHost();
    }

    protected static int port() {
        return MYSQL.getMappedPort(3306);
    }

    private static void registrarLogMySql(OutputFrame outputFrame) {
        String salida = outputFrame.getUtf8String();
        if (salida != null && !salida.isBlank()) {
            System.err.print("[mysql-testcontainer] " + salida);
        }
    }
}
