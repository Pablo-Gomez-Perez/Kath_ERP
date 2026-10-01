package com.kathsoft.kathpos.app.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import com.kathsoft.kathpos.app.model.configuracion.EstadoInicializacion;
import com.kathsoft.kathpos.app.model.configuracion.SolicitudInicializacion;

class InicializacionSistemaControllerTest {

    private SolicitudInicializacion nuevaSucursal() {
        return new SolicitudInicializacion(null, "Matriz", "",
                "9611234567", "", "Chiapas", "Tuxtla Gutiérrez",
                "Centro 123", "29000",
                "GOPP900101AB1", "GOPP900101HCSMNR01",
                "Pablo Gómez Pérez", LocalDate.of(1990, 1, 1),
                "pablo@ejemplo.mx");
    }

    @Test
    void reconocePrimerArranqueYReutilizacionDeSucursal() {
        EstadoInicializacion nuevo = new EstadoInicializacion(true, 0, 0);
        assertTrue(nuevo.necesitaCrearSucursal());
        assertFalse(nuevo.necesitaSeleccionarSucursal());
        assertTrue(new EstadoInicializacion(true, 2, 2).necesitaSeleccionarSucursal());
        assertTrue(new EstadoInicializacion(true, 1, 0).requiereIntervencion());
        assertFalse(new EstadoInicializacion(false, 2, 2).necesitaCrearSucursal());
    }

    @Test
    void validaAltaYEvitaClaveDeCincoCaracteres() {
        SolicitudInicializacion datos = nuevaSucursal();
        assertNull(InicializacionSistemaController.validar(datos, "clave-larga-123".toCharArray()));
        assertEquals(500,
                InicializacionSistemaController.validar(datos, "ADMIN".toCharArray()).id());
        assertEquals(500,
                InicializacionSistemaController.validar(datos, null).id());
        assertEquals(500,
                InicializacionSistemaController.validar(null, "clave-larga-123".toCharArray()).id());
    }

    @Test
    void obligaACapturarRFCyCURPRealesEnVezDeValoresInventados() {
        SolicitudInicializacion d = nuevaSucursal();
        SolicitudInicializacion incompleta = new SolicitudInicializacion(
                null, d.nombreSucursal(), d.descripcionSucursal(),
                d.telefonoSucursal(), d.correoSucursal(), d.estadoSucursal(),
                d.ciudadSucursal(), d.direccionSucursal(), d.codigoPostalSucursal(),
                "ADMIN", d.curpAdministrador(), d.nombreAdministrador(),
                d.fechaNacimientoAdministrador(), d.correoAdministrador());
        assertEquals(500, InicializacionSistemaController
                .validar(incompleta, "clave-larga-123".toCharArray()).id());
    }

    @Test
    void permiteUsarSucursalPreexistenteSinRecapturarSusDatos() {
        SolicitudInicializacion d = nuevaSucursal();
        SolicitudInicializacion existente = new SolicitudInicializacion(
                4L, "", "", "", "", "", "", "", "",
                d.rfcAdministrador(), d.curpAdministrador(),
                d.nombreAdministrador(), d.fechaNacimientoAdministrador(),
                d.correoAdministrador());
        assertNull(InicializacionSistemaController
                .validar(existente, "clave-larga-123".toCharArray()));
    }
}
