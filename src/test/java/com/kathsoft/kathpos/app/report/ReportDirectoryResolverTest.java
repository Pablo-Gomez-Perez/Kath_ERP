package com.kathsoft.kathpos.app.report;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class ReportDirectoryResolverTest {

    @Test
    void documentsDirectoryResolvesInsideUserEnvironment() {
        var documents = ReportDirectoryResolver.documentsDirectory();
        assertNotNull(documents);
        assertTrue(documents.isAbsolute());
    }
}
