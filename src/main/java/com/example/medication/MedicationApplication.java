package com.example.medication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MedicationApplication {
    public static void main(String[] args) {
        SpringApplication.run(MedicationApplication.class, args);
        System.out.println("💊 Календарь приёма лекарств запущен! (Java 18 + Spring Boot 3)");
        System.out.println("📍 http://localhost:8080");
        System.out.println("📊 H2 Console: http://localhost:8080/h2-console");
        System.out.println("🔗 REST API: http://localhost:8080/api/medications");
    }
}