package com.kathsoft.kathpos.app.report.ventas.detalle;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.kathsoft.kathpos.app.report.JasperReportService;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Genera los archivos del reporte diario a partir de una única representación
 * documental. No realiza consultas a la base de datos.
 */
public class ReporteDetalleVentasExportService {

    static final String TEMPLATE = "/reports/reporte_detalle_ventas.jrxml";
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final String SEPARADOR = "------------------------------------------------------------";

    private final JasperReportService jasperReportService;

    public ReporteDetalleVentasExportService() {
        this(new JasperReportService());
    }

    ReporteDetalleVentasExportService(JasperReportService jasperReportService) {
        this.jasperReportService = jasperReportService;
    }

    public Path generarPdf(
            LocalDate fecha,
            List<ReporteDetalleVentasSeccion> secciones,
            Path destino) throws Exception {

        validar(fecha, secciones, destino);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("TITULO", titulo(fecha));

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(
                construirFilasPdf(secciones),
                false);

        return this.jasperReportService.exportPdf(
                TEMPLATE,
                parametros,
                dataSource,
                destino,
                0);
    }

    public Path generarTxt(
            LocalDate fecha,
            List<ReporteDetalleVentasSeccion> secciones,
            Path destino) throws IOException {

        validar(fecha, secciones, destino);
        return escribir(destino, generarContenidoTxt(fecha, secciones));
    }

    public Path generarCsv(
            LocalDate fecha,
            List<ReporteDetalleVentasSeccion> secciones,
            Path destino) throws IOException {

        validar(fecha, secciones, destino);
        return escribir(destino, generarContenidoCsv(fecha, secciones));
    }

    static String generarContenidoTxt(
            LocalDate fecha,
            List<ReporteDetalleVentasSeccion> secciones) {

        validarContenido(fecha, secciones);

        String nl = System.lineSeparator();
        StringBuilder texto = new StringBuilder();

        texto.append(titulo(fecha)).append(nl).append(nl);

        for (ReporteDetalleVentasSeccion seccion : secciones) {
            texto.append(seccion.titulo()).append(nl);
            texto.append(String.join("\t", seccion.columnas())).append(nl);

            if (seccion.filas().isEmpty()) {
                texto.append("Sin registros").append(nl);
            } else {
                for (List<String> fila : seccion.filas()) {
                    texto.append(String.join("\t", normalizarFila(fila))).append(nl);
                }
            }

            texto.append(SEPARADOR).append(nl).append(nl);
        }

        return texto.toString();
    }

    static String generarContenidoCsv(
            LocalDate fecha,
            List<ReporteDetalleVentasSeccion> secciones) {

        validarContenido(fecha, secciones);

        StringBuilder csv = new StringBuilder();
        csv.append(escaparCsv(titulo(fecha))).append("\r\n\r\n");

        for (int indiceSeccion = 0; indiceSeccion < secciones.size(); indiceSeccion++) {
            ReporteDetalleVentasSeccion seccion = secciones.get(indiceSeccion);

            appendCsvFila(csv, seccion.columnas());

            for (List<String> fila : seccion.filas()) {
                csv.append("\r\n");
                appendCsvFila(csv, fila);
            }

            if (indiceSeccion < secciones.size() - 1) {
                csv.append("\r\n\r\n");
            }
        }

        return csv.toString();
    }

    static List<ReporteDetalleVentasPdfRow> construirFilasPdf(
            List<ReporteDetalleVentasSeccion> secciones) {

        List<ReporteDetalleVentasPdfRow> filas = new ArrayList<>();

        for (ReporteDetalleVentasSeccion seccion : secciones) {
            int columnas = seccion.columnas().size();
            if (columnas != 2 && columnas != 5) {
                throw new IllegalArgumentException(
                        "La plantilla PDF sólo admite secciones de dos o cinco columnas");
            }

            filas.add(fila(
                    ReporteDetalleVentasPdfRow.SECCION,
                    List.of(seccion.titulo())));

            filas.add(fila(
                    columnas == 5
                            ? ReporteDetalleVentasPdfRow.CABECERA_5
                            : ReporteDetalleVentasPdfRow.CABECERA_2,
                    seccion.columnas()));

            if (seccion.filas().isEmpty()) {
                filas.add(fila(
                        columnas == 5
                                ? ReporteDetalleVentasPdfRow.DATO_5
                                : ReporteDetalleVentasPdfRow.DATO_2,
                        List.of("Sin registros")));
            } else {
                for (List<String> datos : seccion.filas()) {
                    filas.add(fila(
                            columnas == 5
                                    ? ReporteDetalleVentasPdfRow.DATO_5
                                    : ReporteDetalleVentasPdfRow.DATO_2,
                            datos));
                }
            }

            filas.add(fila(ReporteDetalleVentasPdfRow.SEPARADOR, List.of()));
        }

        return List.copyOf(filas);
    }

    static String titulo(LocalDate fecha) {
        if (fecha == null) {
            throw new IllegalArgumentException("La fecha del reporte es obligatoria");
        }
        return "Reporte de ventas a detalle del dia " + fecha.format(FORMATO_FECHA);
    }

    static void validar(
            LocalDate fecha,
            List<ReporteDetalleVentasSeccion> secciones,
            Path destino) {

        validarContenido(fecha, secciones);

        if (destino == null) {
            throw new IllegalArgumentException("La ruta de salida es obligatoria");
        }
    }

    private static void validarContenido(
            LocalDate fecha,
            List<ReporteDetalleVentasSeccion> secciones) {

        if (fecha == null) {
            throw new IllegalArgumentException("La fecha del reporte es obligatoria");
        }
        if (secciones == null || secciones.isEmpty()) {
            throw new IllegalArgumentException("El reporte no contiene secciones");
        }

        boolean existenDatos = secciones.stream()
                .anyMatch(ReporteDetalleVentasSeccion::tieneDatos);

        if (!existenDatos) {
            throw new IllegalArgumentException("No existen datos a exportar");
        }
    }

    private static ReporteDetalleVentasPdfRow fila(
            String tipo,
            List<String> valores) {

        List<String> columnas = new ArrayList<>(List.of("", "", "", "", ""));

        for (int i = 0; i < valores.size() && i < columnas.size(); i++) {
            columnas.set(i, normalizarTexto(valores.get(i)));
        }

        return new ReporteDetalleVentasPdfRow(
                tipo,
                columnas.get(0),
                columnas.get(1),
                columnas.get(2),
                columnas.get(3),
                columnas.get(4));
    }

    private static Path escribir(Path destino, String contenido) throws IOException {
        Path normalizado = destino.toAbsolutePath().normalize();
        Path parent = normalizado.getParent();

        if (parent != null) {
            Files.createDirectories(parent);
        }

        Files.writeString(normalizado, contenido, StandardCharsets.UTF_8);
        return normalizado;
    }

    private static List<String> normalizarFila(List<String> fila) {
        return fila.stream()
                .map(ReporteDetalleVentasExportService::normalizarTexto)
                .toList();
    }

    private static String normalizarTexto(String valor) {
        if (valor == null) {
            return "";
        }

        return valor
                .replace('\t', ' ')
                .replace('\r', ' ')
                .replace('\n', ' ');
    }

    private static void appendCsvFila(StringBuilder csv, List<String> valores) {
        for (int i = 0; i < valores.size(); i++) {
            if (i > 0) {
                csv.append(',');
            }
            csv.append(escaparCsv(normalizarTexto(valores.get(i))));
        }
    }

    private static String escaparCsv(String valor) {
        if (valor == null) {
            return "";
        }

        boolean requiereComillas = valor.indexOf(',') >= 0
                || valor.indexOf('"') >= 0
                || valor.indexOf('\n') >= 0
                || valor.indexOf('\r') >= 0;

        if (!requiereComillas) {
            return valor;
        }

        return "\"" + valor.replace("\"", "\"\"") + "\"";
    }
}
