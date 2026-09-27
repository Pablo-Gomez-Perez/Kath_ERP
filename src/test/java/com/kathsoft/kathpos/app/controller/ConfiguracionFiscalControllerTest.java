package com.kathsoft.kathpos.app.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.kathsoft.kathpos.app.model.configuracion.ConfiguracionFiscal;
import com.kathsoft.kathpos.app.model.viewmodel.SpResponseModel;

class ConfiguracionFiscalControllerTest {

    @Test
    void updateRejectsNullConfigurationBeforeOpeningConnection() {
        ConfiguracionFiscalController controller = new ConfiguracionFiscalController();

        SpResponseModel response = controller.updateConfiguracionFiscal(null);

        assertTrue(response.id() < 0);
        assertTrue(response.message().contains("no es válida"));
    }

    @Test
    void updateRejectsInvalidIdentifierBeforeOpeningConnection() {
        ConfiguracionFiscalController controller = new ConfiguracionFiscalController();
        ConfiguracionFiscal configuracion = new ConfiguracionFiscal(
                0,
                "XAXX010101000",
                "Contribuyente de prueba",
                "",
                "601",
                "General de Ley Personas Morales",
                "",
                true);

        SpResponseModel response = controller.updateConfiguracionFiscal(configuracion);

        assertEquals(-1, response.id());
    }
}
