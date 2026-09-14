package com.kathsoft.kathpos.app.report;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Resuelve una carpeta de documentos escribible sin asumir un sistema operativo
 * concreto. En Linux respeta XDG_DOCUMENTS_DIR cuando está disponible; en los
 * demás casos utiliza las carpetas convencionales Documents/Documentos.
 */
public final class ReportDirectoryResolver {

    private ReportDirectoryResolver() {
    }

    public static Path ticketsDirectory() throws IOException {
        Path base = documentsDirectory();
        Path tickets = base.resolve("Kath ERP").resolve("Tickets");
        Files.createDirectories(tickets);
        return tickets;
    }

    static Path documentsDirectory() {
        Path home = Paths.get(System.getProperty("user.home", ".")).toAbsolutePath().normalize();
        Path xdg = resolveXdgDocuments(home);
        if (xdg != null) {
            return xdg;
        }

        Path documents = home.resolve("Documents");
        if (Files.isDirectory(documents)) {
            return documents;
        }

        Path documentos = home.resolve("Documentos");
        if (Files.isDirectory(documentos)) {
            return documentos;
        }

        return documents;
    }

    private static Path resolveXdgDocuments(Path home) {
        Path config = home.resolve(".config").resolve("user-dirs.dirs");
        if (!Files.isRegularFile(config)) {
            return null;
        }

        try {
            List<String> lineas = Files.readAllLines(config, StandardCharsets.UTF_8);
            for (String linea : lineas) {
                String normalizada = linea == null ? "" : linea.trim();
                if (!normalizada.startsWith("XDG_DOCUMENTS_DIR=")) {
                    continue;
                }

                String valor = normalizada.substring("XDG_DOCUMENTS_DIR=".length()).trim();
                if (valor.startsWith("\"") && valor.endsWith("\"") && valor.length() >= 2) {
                    valor = valor.substring(1, valor.length() - 1);
                }
                valor = valor.replace("$HOME", home.toString());
                if (valor.isBlank()) {
                    return null;
                }
                return Paths.get(valor).toAbsolutePath().normalize();
            }
        } catch (IOException er) {
            return null;
        }
        return null;
    }
}
