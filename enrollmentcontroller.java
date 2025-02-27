package com.sms.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sms.model.Enrollment;
import com.sms.service.StudentManagementService;

/**
 * Contrôleur REST pour gérer les inscriptions
 */
@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
    
    @Autowired
    private StudentManagementService service;
    
    /**
     * Crée une nouvelle inscription
     * 
     * @param requestBody Corps de la requête contenant les détails de l'inscription
     * @return ResponseEntity avec l'ID de l'inscription créée
     */
    @PostMapping
    public ResponseEntity<?> createEnrollment(@RequestBody Map<String, Object> requestBody) {
        String studentID = (String) requestBody.get("studentID");
        String courseCode = (String) requestBody.get("courseCode");
        
        if (studentID == null || courseCode == null) {
            return ResponseEntity.badRequest().body(
                Map.of("error", "L'ID de l'étudiant et le code du cours sont requis"));
        }
        
        String enrollmentID = service.createEnrollment(studentID, courseCode);
        
        if (enrollmentID == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", "Impossible de créer l'inscription"));
        }
        
        Map<String, String> response = new HashMap<>();
        response.put("enrollmentID", enrollmentID);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    /**
     * Récupère les détails d'une inscription
     * 
     * @param enrollmentID ID de l'inscription
     * @return ResponseEntity avec les détails de l'inscription
     */
    @GetMapping("/{enrollmentID}")
    public ResponseEntity<?> getEnrollment(@PathVariable String enrollmentID) {
        Enrollment enrollment = service.getEnrollment(enrollmentID);
        
        if (enrollment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Inscription non trouvée"));
        }
        
        return ResponseEntity.ok(enrollment.getDetails());
    }
}