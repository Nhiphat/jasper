package com.example.demojasperreport.controller;

import com.example.demojasperreport.entity.Employee;
import com.example.demojasperreport.repository.EmployeeRepository;
import com.example.demojasperreport.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@RestController
public class TestController {
    @Autowired
    private EmployeeRepository repository;
    @Autowired
    private ReportService service;

    @GetMapping("/getEmployees")
    public List<Employee> getEmployees() {

        return repository.findAll();
    }

    @GetMapping("/report/{format}")
    public HttpEntity<InputStreamResource> generateReport(@PathVariable String format) throws FileNotFoundException, JRException {
        String path = service.exportReport(format);
        File file = new File(path);
    
        InputStream inputStream = new FileInputStream(file);
    
        InputStreamResource inputStreamResource = new InputStreamResource(inputStream);
    
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=" + "export.pdf");
    
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(inputStreamResource);
    }
}
