package com.kathsoft.kathpos.app.model.configuracion;

/**
 * Representa el único registro activo de configuración fiscal del emisor.
 */
public record ConfiguracionFiscal(
        int idConfiguracion,
        String rfcEmisor,
        String nombreRazonSocial,
        String nombreComercial,
        String regimenFiscalClave,
        String regimenFiscalDescripcion,
        String numeroRegistroSistema,
        boolean activo) {
}
