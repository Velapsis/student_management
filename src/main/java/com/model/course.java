package com.sms.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentant un cours
 */
public class Course {
    @Getter @Setter
    private String courseName;
    
    @Getter
    private String courseCode;
    
    @Getter @Setter
    private int creditHours;
    
    @Getter
    private List<Student> students;
    
    /**
     * Constructeur
     * 
     * @param courseName Nom du cours
     * @param courseCode Code du cours
     * @param creditHours Heures de crédit
     */
    public Course(String courseName, String courseCode, int creditHours) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.creditHours = creditHours;
        this.students = new ArrayList<>();
    }
    
    /**
     * Inscrit un étudiant au cours
     * 
     * @param student Étudiant à inscrire
     * @return true si l'inscription a réussi, false sinon
     */
    public boolean enrollStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
            return true;
        }
        return false;
    }
    
    /**
     * Retourne la liste des étudiants inscrits
     * 
     * @return Liste des étudiants
     */
    public List<Student> getEnrolledStudents() {
        return students;
    }
    
    /**
     * Retourne les détails du cours
     * 
     * @return Map contenant les détails du cours
     */
    public Map<String, Object> getDetails() {
        Map<String, Object> details = new HashMap<>();
        details.put("courseName", courseName);
        details.put("courseCode", courseCode);
        details.put("creditHours", creditHours);
        
        List<Map<String, Object>> studentDetails = students.stream()
            .map(Student::getDetails)
            .collect(Collectors.toList());
            
        details.put("enrolledStudents", studentDetails);
        
        return details;
    }
}