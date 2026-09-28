package com.kathsoft.kathpos.app.model.configuracion;

import java.time.LocalDate;

/**
 * Alta transaccional del primer empleado y, si la BD no contiene sucursales,
 * de la primera sucursal. No conserva ni recibe contraseñas.
 */
public record SolicitudInicializacion(
        Long idSucursalExistente,
        String nombreSucursal, String descripcionSucursal,
        String telefonoSucursal, String correoSucursal, String estadoSucursal,
        String ciudadSucursal, String direccionSucursal, String codigoPostalSucursal,
        String rfcAdministrador, String curpAdministrador,
        String nombreAdministrador, LocalDate fechaNacimientoAdministrador,
        String correoAdministrador) {

    public static final String USUARIO_ADMINISTRADOR = "ADMIN";

    public void validar() {
        if (idSucursalExistente != null && idSucursalExistente <= 0) {
            throw new IllegalArgumentException("Seleccione una sucursal existente válida");
        }
        if (idSucursalExistente == null) {
            requerido(nombreSucursal, 100, "Nombre de sucursal");
            requerido(telefonoSucursal, 10, "Teléfono de sucursal");
            if (!telefonoSucursal.matches("[0-9]{10}")) {
                throw new IllegalArgumentException("El teléfono de sucursal debe tener diez dígitos");
            }
            requerido(estadoSucursal, 60, "Estado de sucursal");
            requerido(ciudadSucursal, 60, "Ciudad de sucursal");
            requerido(direccionSucursal, 255, "Dirección de sucursal");
            requerido(codigoPostalSucursal, 5, "Código postal de sucursal");
            if (!codigoPostalSucursal.matches("[0-9]{5}")) {
                throw new IllegalArgumentException("El código postal debe tener cinco dígitos");
            }
            opcional(descripcionSucursal, 65535, "Descripción");
            opcional(correoSucursal, 255, "Correo de sucursal");
        }
        requerido(rfcAdministrador, 13, "RFC del administrador");
        if (!rfcAdministrador.trim().matches("[A-Za-zÑñ&]{3,4}[0-9]{6}[A-Za-z0-9]{3}")) {
            throw new IllegalArgumentException("El RFC del administrador debe tener 12 o 13 caracteres válidos");
        }
        requerido(curpAdministrador, 18, "CURP del administrador");
        if (!curpAdministrador.trim().matches("[A-Za-z0-9]{18}")) {
            throw new IllegalArgumentException("La CURP debe contener 18 caracteres alfanuméricos");
        }
        requerido(nombreAdministrador, 30, "Nombre completo del administrador");
        if (fechaNacimientoAdministrador == null ||
                fechaNacimientoAdministrador.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Ingrese una fecha de nacimiento válida");
        }
        requerido(correoAdministrador, 30, "Correo del administrador");
        if (!correoAdministrador.contains("@")) {
            throw new IllegalArgumentException("El correo del administrador es inválido");
        }
    }

    private static void requerido(String dato, int max, String etiqueta) {
        if (dato == null || dato.isBlank() || dato.trim().length() > max) {
            throw new IllegalArgumentException(etiqueta + " es obligatorio (máximo " + max + " caracteres)");
        }
    }
    private static void opcional(String dato, int max, String etiqueta) {
        if (dato != null && dato.length() > max) {
            throw new IllegalArgumentException(etiqueta + " supera " + max + " caracteres");
        }
    }
}
