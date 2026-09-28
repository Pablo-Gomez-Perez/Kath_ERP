package com.kathsoft.kathpos.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import com.kathsoft.kathpos.app.model.empleado.EmpleadoLogin;
import com.kathsoft.kathpos.app.model.retiros.RetiroDeEfectivoRegistro;
import com.kathsoft.kathpos.tools.PasswordHashService;

/**
 * Pruebas de autorización sin MySQL mediante inyección de las consultas
 * al SP y la misma verificación PBKDF2 utilizada por LoginController.
 */
class AutorizacionRetiroServiceTest {

    @Test
    void autorizaUnicamenteClaveCorrectaDelEmpleadoYDeSuSucursal() throws Exception {
        String hash = PasswordHashService.hashPassword("clave-segura");
        AutorizacionRetiroService servicio = new AutorizacionRetiroService(
                id -> responsable(7, 3, "CAJERO", true),
                (nombre, pass) -> PasswordHashService.verifyPassword(pass, hash)
                        ? login(7, 3, true) : null);

        char[] valida = "clave-segura".toCharArray();
        char[] invalida = "otra-clave".toCharArray();
        try {
            assertTrue(servicio.autorizar(7, 3, valida));
            assertFalse(servicio.autorizar(7, 3, invalida));
        } finally {
            Arrays.fill(valida, '\0');
            Arrays.fill(invalida, '\0');
        }
    }

    @Test
    void rechazaIdentidadYAutenticacionCruzadas() throws Exception {
        AutorizacionRetiroService servicio = new AutorizacionRetiroService(
                id -> responsable(7, 3, "CAJERO", true),
                (nombre, pass) -> login(9, 3, true));

        assertFalse(servicio.autorizar(7, 3, "clave".toCharArray()));
        assertFalse(AutorizacionRetiroService.coincideEmpleadoAutorizado(
                login(7, 9, true), 7, 3));
        assertFalse(AutorizacionRetiroService.coincideEmpleadoAutorizado(
                login(7, 3, false), 7, 3));
        assertFalse(AutorizacionRetiroService.coincideEmpleadoAutorizado(
                null, 7, 3));
    }

    @Test
    void noConsultaLoginNiBaseConClavesAusentesOIdentidadesInvalidas() throws Exception {
        AtomicInteger consultas = new AtomicInteger();
        AtomicInteger sesiones = new AtomicInteger();
        AutorizacionRetiroService servicio = new AutorizacionRetiroService(
                id -> {
                    consultas.incrementAndGet();
                    return responsable(7, 3, "CAJERO", true);
                },
                (nombre, pass) -> {
                    sesiones.incrementAndGet();
                    return login(7, 3, true);
                });

        assertFalse(servicio.autorizar(7, 3, null));
        assertFalse(servicio.autorizar(7, 3, new char[0]));
        assertFalse(servicio.autorizar(0, 3, "clave".toCharArray()));
        assertFalse(servicio.autorizar(7, 0, "clave".toCharArray()));
        assertEquals(0, consultas.get());
        assertEquals(0, sesiones.get());
    }

    @Test
    void impideLoginCuandoResponsableSeaInactivoODeOtraSucursal() throws Exception {
        AtomicInteger sesiones = new AtomicInteger();
        AutorizacionRetiroService empleadoInactivo = new AutorizacionRetiroService(
                id -> responsable(7, 3, "CAJERO", false),
                (nombre, pass) -> {
                    sesiones.incrementAndGet();
                    return login(7, 3, true);
                });
        AutorizacionRetiroService otraSucursal = new AutorizacionRetiroService(
                id -> responsable(7, 9, "CAJERO", true),
                (nombre, pass) -> {
                    sesiones.incrementAndGet();
                    return login(7, 9, true);
                });

        assertFalse(empleadoInactivo.autorizar(7, 3, "clave".toCharArray()));
        assertFalse(otraSucursal.autorizar(7, 3, "clave".toCharArray()));
        assertEquals(0, sesiones.get());
    }

    @Test
    void controllerRechazaAmbasEscriturasSinContraseniaSinAccederAJdbc() {
        AutorizacionRetiroService servicio = new AutorizacionRetiroService(
                id -> { throw new AssertionError("No debe acceder al SP"); },
                (alias, pass) -> { throw new AssertionError("No debe autenticarse"); });
        RetiroDeEfectivoController controller = new RetiroDeEfectivoController(servicio);
        RetiroDeEfectivoRegistro retiro = new RetiroDeEfectivoRegistro(
                3, 7, "R-123", "Retiro autorizado", new java.math.BigDecimal("20.00"));

        assertEquals(401, controller.registrarRetiro(retiro, null).id());
        assertEquals(401, controller.registrarRetiro(retiro, new char[0]).id());
        assertEquals(401, controller.inhabilitarRetiro(1, 3, null).id());
        assertEquals(401, controller.inhabilitarRetiro(1, 3, new char[0]).id());
    }

    @Test
    void controllerNoLlamaAlProcedimientoDeAltaSiFallaAutorizacion() {
        AutorizacionRetiroService servicio = new AutorizacionRetiroService(
                id -> responsable(7, 3, "CAJERO", true),
                (alias, pass) -> null);
        RetiroDeEfectivoController controller = new RetiroDeEfectivoController(servicio);
        RetiroDeEfectivoRegistro retiro = new RetiroDeEfectivoRegistro(
                3, 7, "R-123", "Retiro autorizado", new java.math.BigDecimal("20.00"));

        assertEquals(401, controller.registrarRetiro(retiro, "incorrecta".toCharArray()).id());
    }

    private static AutorizacionRetiroService.EmpleadoResponsable responsable(
            int id, long sucursal, String alias, boolean activo) {
        return new AutorizacionRetiroService.EmpleadoResponsable(id, sucursal, alias, activo);
    }

    private static EmpleadoLogin login(int id, int sucursal, boolean activo) {
        EmpleadoLogin empleado = new EmpleadoLogin();
        empleado.setIdEmpleado(id);
        empleado.setIdSucursal(sucursal);
        empleado.setActivo(activo);
        return empleado;
    }
}
