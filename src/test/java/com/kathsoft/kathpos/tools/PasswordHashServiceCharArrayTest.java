package com.kathsoft.kathpos.tools;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

class PasswordHashServiceCharArrayTest {

    @Test
    void hashEnCharArrayEsCompatibleConLoginActual() {
        char[] clave = "UnicaClaveDeInicio2026".toCharArray();
        try {
            String hash = PasswordHashService.hashPassword(clave);
            assertTrue(hash.startsWith("PBKDF2$"));
            assertTrue(PasswordHashService.verifyPassword(clave, hash));
            assertFalse(PasswordHashService.verifyPassword("incorrecta".toCharArray(), hash));
        } finally {
            Arrays.fill(clave, '\0');
        }
    }

    @Test
    void rechazaClavesVaciasYPermiteAPIAnterior() {
        assertThrows(IllegalArgumentException.class,
                () -> PasswordHashService.hashPassword(new char[0]));
        assertThrows(IllegalArgumentException.class,
                () -> PasswordHashService.hashPassword("  ".toCharArray()));
        assertTrue(PasswordHashService.verifyPassword(
                "clave".toCharArray(), PasswordHashService.hashPassword("clave")));
    }
}
