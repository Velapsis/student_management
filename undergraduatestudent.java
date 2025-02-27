package com.sms.model;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentant un étudiant de premier cycle
 */
public class UndergraduateStudent extends Student {
    @Getter @Setter
    private String major;
    
    /**
     * Constructeur
     * 
     * @param name Nom de l'étudiant
     * @param age Âge de l'étudiant
     * @param major Spécialité/Majeure de l'étudiant
     */
    public UndergraduateStudent(String name, int age, String major) {
        super(name, age);
        this.major = major;
    }
    
    /**
     * Constructeur avec ID spécifié
     * 
     * @param name Nom de l'étudiant
     * @param age Âge de l'étudiant
     * @param studentID ID de l'étudiant
     * @param major Spécialité/Majeure de l'étudiant
     */
    public UndergraduateStudent(String name, int age, String studentID, String major) {
        super(name, age, studentID);
        this.major = major;
    }
    
    /**
     * Surcharge de la méthode pour calculer la moyenne selon des règles spécifiques
     * aux étudiants de premier cycle
     * 
     * @return La moyenne des notes
     */
    @Override
    public double getAverageGrade() {
        // On pourrait implémenter une formule spécifique ici
        // Pour cet exemple, on utilise la même formule que la classe parente
        return super.getAverageGrade();
    }
    
    /**
     * Retourne les détails de l'étudiant de premier cycle
     * 
     * @return Map contenant les détails de l'étudiant
     */
    @Override
    public Map<String, Object> getDetails() {
        Map<String, Object> details = super.getDetails();
        details.put("type", "Undergraduate");
        details.put("major", major);
        
        return details;
    }
}