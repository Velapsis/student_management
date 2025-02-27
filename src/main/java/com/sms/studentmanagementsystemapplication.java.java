package com.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import com.sms.service.StudentManagementService;

/**
 * Classe principale de l'application de gestion des étudiants
 */
@SpringBootApplication
public class StudentManagementSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(StudentManagementSystemApplication.class, args);
    }
    
    /**
     * Initialise des données de test au démarrage de l'application
     * 
     * @param service Service de gestion des étudiants
     * @return CommandLineRunner
     */
    @Bean
    public CommandLineRunner init(StudentManagementService service) {
        return args -> {
            // Initialiser des données de test
            service.initTestData();
            System.out.println("Système de Gestion des Étudiants démarré avec succès !");
            System.out.println("API disponible à l'adresse : http://localhost:8080");
        };
    }
}