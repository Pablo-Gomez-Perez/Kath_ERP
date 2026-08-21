package com.kathsoft.kathpos.tools;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Base64;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class PasswordHashServiceTest {

    private static final String PASSWORD = "KathERP-2026!";
    private static final String KNOWN_HASH = "PBKDF2$120000$AAECAwQFBgcICQoLDA0ODw==$"
            + "KtglTnzB3+5y0n7CkrZzUK5W3hywSatcsu5VyAzRlqA=";

    @Nested
    class HashPassword {

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t", "\n"})
        void shouldRejectMissingPassword(String password) {
            IllegalArgumentException exception = assertThrows(
                    IllegalArgumentException.class,
                    () -> PasswordHashService.hashPassword(password)
            );

            assertEquals("La contraseña es obligatoria", exception.getMessage());
        }

        @Test
        void shouldGenerateExpectedPbkdf2Format() {
            String hash = PasswordHashService.hashPassword(PASSWORD);
            String[] parts = hash.split("\\$");

            assertEquals(4, parts.length);
            assertEquals("PBKDF2", parts[0]);
            assertEquals("120000", parts[1]);
            assertEquals(16, Base64.getDecoder().decode(parts[2]).length);
            assertEquals(32, Base64.getDecoder().decode(parts[3]).length);
        }

        @Test
        void shouldUseDifferentSaltForEachHash() {
            String firstHash = PasswordHashService.hashPassword(PASSWORD);
            String secondHash = PasswordHashService.hashPassword(PASSWORD);

            assertNotEquals(firstHash, secondHash);
        }
    }

    @Nested
    class HashIfPlain {

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t", "\n"})
        void shouldPreserveMissingPassword(String password) {
            assertEquals(password, PasswordHashService.hashIfPlain(password));
        }

        @Test
        void shouldPreserveValidStoredHash() {
            assertEquals(KNOWN_HASH, PasswordHashService.hashIfPlain(KNOWN_HASH));
        }

        @Test
        void shouldHashPlainPassword() {
            String result = PasswordHashService.hashIfPlain(PASSWORD);

            assertTrue(PasswordHashService.isHash(result));
            assertTrue(PasswordHashService.verifyPassword(PASSWORD.toCharArray(), result));
        }

        @Test
        void shouldHashMalformedValueThatOnlyHasPbkdf2Prefix() {
            String malformedHash = "PBKDF2$invalid";
            String result = PasswordHashService.hashIfPlain(malformedHash);

            assertNotEquals(malformedHash, result);
            assertTrue(PasswordHashService.verifyPassword(malformedHash.toCharArray(), result));
        }
    }

    @Nested
    class VerifyPassword {

        @Test
        void shouldVerifyKnownPbkdf2Vector() {
            assertTrue(PasswordHashService.verifyPassword(PASSWORD.toCharArray(), KNOWN_HASH));
        }

        @Test
        void shouldRejectIncorrectPassword() {
            assertFalse(PasswordHashService.verifyPassword("incorrecta".toCharArray(), KNOWN_HASH));
        }

        @Test
        void shouldSupportUnicodePassword() {
            String password = "Contraseña-segura-🔐";
            String hash = PasswordHashService.hashPassword(password);

            assertTrue(PasswordHashService.verifyPassword(password.toCharArray(), hash));
        }

        @Test
        void shouldRejectMissingInputs() {
            assertFalse(PasswordHashService.verifyPassword(null, KNOWN_HASH));
            assertFalse(PasswordHashService.verifyPassword(new char[0], KNOWN_HASH));
            assertFalse(PasswordHashService.verifyPassword(PASSWORD.toCharArray(), null));
            assertFalse(PasswordHashService.verifyPassword(PASSWORD.toCharArray(), ""));
            assertFalse(PasswordHashService.verifyPassword(PASSWORD.toCharArray(), " "));
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "texto-plano",
                "PBKDF2$invalid",
                "PBKDF2$abc$AAECAwQFBgcICQoLDA0ODw==$KtglTnzB3+5y0n7CkrZzUK5W3hywSatcsu5VyAzRlqA=",
                "PBKDF2$120000$base64-invalido$hash-invalido"
        })
        void shouldRejectMalformedStoredHash(String storedHash) {
            assertFalse(PasswordHashService.verifyPassword(PASSWORD.toCharArray(), storedHash));
        }
    }

    @Nested
    class IsHash {

        @Test
        void shouldRecognizeValidStoredHash() {
            assertTrue(PasswordHashService.isHash(KNOWN_HASH));
        }

        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {
                "texto-plano",
                "PBKDF2",
                "PBKDF2$invalid",
                "PBKDF2$120000$$",
                "PBKDF2$120000$salt-invalido$hash-invalido"
        })
        void shouldRejectValuesWithoutValidHashFormat(String value) {
            assertFalse(PasswordHashService.isHash(value));
        }
    }
}
