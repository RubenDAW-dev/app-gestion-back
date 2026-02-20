package com.appgestion.app.services;
import java.io.InputStream;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

@Service
public class ReportService {

    private final DataSource dataSource;

    public ReportService(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // ============================
    // MÉTODO GENÉRICO QUE USAS
    // ============================
    public byte[] generarReport(String nombreReporte, Long idProyecto) throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("idProyecto", idProyecto);
        return exportPdf(nombreReporte, params);
    }

    // ================================================
    // MÉTODO QUE FALTABA: exportPdf()
    // ================================================
    private byte[] exportPdf(String reportName, Map<String, Object> params) throws Exception {

        // Intentamos cargar .jasper
        InputStream jasperFile = getClass().getResourceAsStream("/reports/" + reportName + ".jasper");

        JasperPrint print;

        try (Connection conn = dataSource.getConnection()) {

            if (jasperFile != null) {
                // Ya hay .jasper → se usa directamente
                print = JasperFillManager.fillReport(jasperFile, params, conn);

            } else {
                // No existe .jasper → compilar .jrxml y ejecutarlo
                InputStream jrxmlFile = getClass().getResourceAsStream("/reports/" + reportName + ".jrxml");

                if (jrxmlFile == null) {
                    throw new RuntimeException("No se encontró el archivo: " + reportName + ".jrxml");
                }

                JasperReport jasperReport = JasperCompileManager.compileReport(jrxmlFile);
                print = JasperFillManager.fillReport(jasperReport, params, conn);
            }

            // Exportar PDF a byte[]
            return JasperExportManager.exportReportToPdf(print);
        }
    }
}