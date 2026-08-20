package com.kathsoft.kathpos.app;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BuildEnvironmentTest {

    @Test
    void shouldRunTestsWithJava21OrNewer() {
        assertTrue(
                Runtime.version().feature() >= 21,
                "Kath ERP requires Java 21 or newer"
        );
    }
}
