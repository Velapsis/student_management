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

import com.sms.model.Course;
import com.sms.service.StudentManagementService;

/**
 * Contrôleur REST pour gérer les cours
 */
@RestController
@RequestMapping("/courses")
public class CourseController {
    
    @Autowired
    private StudentManagementService service;
    
    /**
     * Crée un nouveau cours
     * 
     * @param requestBody Corps de la requête contenant les détails du cours
     * @return ResponseEntity avec le code du cours créé
     */
    @PostMapping
    public ResponseEntity<Map<String, String>> createCourse(@RequestBody Map<String, Object> requestBody) {
        String courseName = (String) requestBody.get("courseName");
        String courseCode = (String) requestBody.get("courseCode");
        Integer creditHours = (Integer) requestBody.get("creditHours");
        
        if (courseName == null || courseCode == null || creditHours == null) {
            return ResponseEntity.badRequest().body(
                Map.of("error", "Le nom du cours, le code et les heures de crédit sont requis"));
        }
        
        Course course = new Course(courseName, courseCode, creditHours);
        service.addCourse(course);
        
        Map<String, String> response = new HashMap<>();
        response.put("courseCode", courseCode);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    /**
     * Récupère les détails d'un cours
     * 
     * @param courseCode Code du cours
     * @return ResponseEntity avec les détails du cours
     */
    @GetMapping("/{courseCode}")
    public ResponseEntity<?> getCourse(@PathVariable String courseCode) {
        Course course = service.getCourse(courseCode);
        
        if (course == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Cours non trouvé"));
        }
        
        return ResponseEntity.ok(course.getDetails());
    }
    
    /**
     * Récupère tous les cours
     * 
     * @return Liste des détails de tous les cours
     */
    @GetMapping
    public ResponseEntity<List<Map<String, Object>>> getAllCourses() {
        return ResponseEntity.ok(service.getAllCourses());
    }
}