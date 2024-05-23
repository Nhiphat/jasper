package com.example.demojasperreport.service;


import com.example.demojasperreport.entity.Employee;
import com.example.demojasperreport.repository.EmployeeRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import net.sf.jasperreports.engine.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private EmployeeRepository repository;


    public String exportReport(String reportFormat) throws FileNotFoundException, JRException {
        String path = getDayDirectory("tmp").getPath();
        List<Employee> employees = repository.findAll();
        //load file and compile it
        File file = ResourceUtils.getFile("classpath:employees.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employees);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Java Techie");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (reportFormat.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\employees.html");
        }
        if (reportFormat.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\employees.pdf");
        }

        return path + "\\employees.pdf";
    }

    private File getDayDirectory(String path) {
        File file = new File(path);
        if (!file.exists()) {
            boolean createFile = file.mkdirs();
            if (!createFile || !file.canWrite()) {
                throw new RuntimeException("error");
            }
        }

        return file;
    }
}
