package com.sms.model;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lombok.Getter;

/**
 * Classe représentant une inscription d'un étudiant à un cours
 */
public class Enrollment {
    @Getter
    private String enrollmentID;
    
    @Getter
    private Student student;
    
    @Getter
    private Course course;
    
    /**
     * Constructeur
     * 
     * @param student Étudiant à inscrire
     * @param course Cours auquel s'inscrire
     */
    public Enrollment(Student student, Course course) {
        this.enrollmentID = UUID.randomUUID().toString();
        this.student = student;
        this.course = course;
    }
    
    /**
     * Enregistre l'étudiant dans le cours
     * 
     * @return true si l'enregistrement a réussi, false sinon
     */
    public boolean register() {
        return course.enrollStudent(student);
    }
    
    /**
     * Retourne les détails de l'inscription
     * 
     * @return Map contenant les détails de l'inscription
     */
    public Map<String, Object> getDetails() {
        Map<String, Object> details = new HashMap<>();
        details.put("enrollmentID", enrollmentID);
        details.put("student", student.getDetails());
        
        Map<String, Object> courseDetails = new HashMap<>();
        courseDetails.put("courseName", course.getCourseName());
        courseDetails.put("courseCode", course.getCourseCode());
        
        details.put("course", courseDetails);
        
        return details;
    }
}