package com.sms.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import lombok.Getter;

/**
 * Classe représentant un étudiant
 */
public class Student extends Person {
    @Getter
    private String studentID;
    
    @Getter
    private List<Double> grades;
    
    /**
     * Constructeur
     * 
     * @param name Nom de l'étudiant
     * @param age Âge de l'étudiant
     */
    public Student(String name, int age) {
        super(name, age);
        this.studentID = UUID.randomUUID().toString();
        this.grades = new ArrayList<>();
    }
    
    /**
     * Constructeur avec ID étudiant spécifié
     * 
     * @param name Nom de l'étudiant
     * @param age Âge de l'étudiant
     * @param studentID ID de l'étudiant
     */
    public Student(String name, int age, String studentID) {
        super(name, age);
        this.studentID = studentID != null ? studentID : UUID.randomUUID().toString();
        this.grades = new ArrayList<>();
    }
    
    /**
     * Ajoute une note à la liste des notes de l'étudiant
     * 
     * @param grade Note à ajouter
     */
    public void addGrade(double grade) {
        this.grades.add(grade);
    }
    
    /**
     * Calcule la moyenne des notes de l'étudiant
     * 
     * @return La moyenne des notes
     */
    public double getAverageGrade() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        
        double sum = 0.0;
        for (Double grade : grades) {
            sum += grade;
        }
        
        return sum / grades.size();
    }
    
    /**
     * Retourne les détails de l'étudiant
     * 
     * @return Map contenant les détails de l'étudiant
     */
    @Override
    public Map<String, Object> getDetails() {
        Map<String, Object> details = new HashMap<>();
        details.put("name", getName());
        details.put("age", getAge());
        details.put("studentID", studentID);
        details.put("grades", grades);
        details.put("averageGrade", getAverageGrade());
        
        return details;
    }
}