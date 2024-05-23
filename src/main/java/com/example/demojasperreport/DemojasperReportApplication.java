package com.example.demojasperreport;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.*")
@EnableJpaRepositories
public class DemojasperReportApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemojasperReportApplication.class, args);
    }
    
}
