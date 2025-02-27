package com.sms.controller;

import java.util.HashMap;
import java.util.List;
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

import com.sms.model.Student;
import com.sms.service.StudentManagementService;

/**
 * Contrôleur REST pour gérer les étudiants
 */
@RestController
@RequestMapping("/students")
public class StudentController {
    
    @Autowired
    private StudentManagementService service;
    
    /**
     * Crée un nouvel étudiant
     * 
     * @param requestBody Corps de la requête contenant les détails de l'étudiant
     * @return ResponseEntity avec l'ID de l'étudiant créé
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> createStudent(@RequestBody Map<String, Object> requestBody) {
        String name = (String) requestBody.get("name");
        Integer age = (Integer) requestBody.get("age");
        String type = (String) requestBody.get("type");
        
        if (name == null || age == null) {
            return ResponseEntity.badRequest().body(
                Map.of("error", "Le nom et l'âge sont requis"));
        }
        
        String studentID;
        
        if ("undergraduate".equals(type)) {
            String major = (String) requestBody.get("major");
            studentID = service.createUndergraduateStudent(name, age, major);
        } else if ("graduate".equals(type)) {
            String researchArea = (String) requestBody.get("researchArea");
            studentID = service.createGraduateStudent(name, age, researchArea);
        } else {
            // Étudiant standard
            Student student = new Student(name, age);
            studentID = service.addStudent(student);
        }
        
        Map<String, String> response = new HashMap<>();
        response.put("studentID", studentID);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    /**
     * Récupère les détails d'un étudiant
     * 
     * @param studentID ID de l'étudiant
     * @return ResponseEntity avec les détails de l'étudiant
     */
    @GetMapping("/{studentID}")
    public ResponseEntity<?> getStudent(@PathVariable String studentID) {
        Student student = service.getStudent(studentID);
        
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Étudiant non trouvé"));
        }
        
        return ResponseEntity.ok(student.getDetails());
    }
    
    /**
     * Récupère tous les étudiants
     * 
     * @return Liste des détails de tous les étudiants
     */
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllStudents() {
        return ResponseEntity.ok(service.getAllStudents());
    }
    
    /**
     * Ajoute une note à un étudiant
     * 
     * @param studentID ID de l'étudiant
     * @param requestBody Corps de la requête contenant la note
     * @return ResponseEntity avec un message de confirmation
     */
    @PostMapping("/{studentID}/grades")
    public ResponseEntity<?> addGrade(@PathVariable String studentID, @RequestBody Map<String, Object> requestBody) {
        Double grade = ((Number) requestBody.get("grade")).doubleValue();
        
        if (grade == null) {
            return ResponseEntity.badRequest().body(
                Map.of("error", "La note est requise"));
        }
        
        boolean success = service.addGradeToStudent(studentID, grade);
        
        if (!success) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Étudiant non trouvé"));
        }
        
        return ResponseEntity.ok(Map.of("message", "Note ajoutée avec succès"));
    }
}