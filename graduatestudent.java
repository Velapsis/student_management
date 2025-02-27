package com.sms.model;

import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * Classe représentant un étudiant diplômé (de cycle supérieur)
 */
public class GraduateStudent extends Student {
    @Getter @Setter
    private String researchArea;
    
    /**
     * Constructeur
     * 
     * @param name Nom de l'étudiant
     * @param age Âge de l'étudiant
     * @param researchArea Domaine de recherche
     */
    public GraduateStudent(String name, int age, String researchArea) {
        super(name, age);
        this.researchArea = researchArea;
    }
    
    /**
     * Constructeur avec ID spécifié
     * 
     * @param name Nom de l'étudiant
     * @param age Âge de l'étudiant
     * @param studentID ID de l'étudiant
     * @param researchArea Domaine de recherche
     */
    public GraduateStudent(String name, int age, String studentID, String researchArea) {
        super(name, age, studentID);
        this.researchArea = researchArea;
    }
    
    /**
     * Surcharge de la méthode pour calculer la moyenne selon des règles spécifiques
     * aux étudiants de cycle supérieur
     * 
     * @return La moyenne des notes pondérée
     */
    @Override
    public double getAverageGrade() {
        List<Double> grades = getGrades();
        
        if (grades.isEmpty()) {
            return 0.0;
        }
        
        // Calcul pondéré pour les étudiants diplômés
        // On donne plus de poids aux notes les plus récentes
        double weightedSum = 0.0;
        double weightSum = 0.0;
        
        for (int i = 0; i < grades.size(); i++) {
            double weight = i + 1; // Plus d'importance aux notes récentes
            weightedSum += grades.get(i) * weight;
            weightSum += weight;
        }
        
        return weightSum > 0 ? weightedSum / weightSum : 0.0;
    }
    
    /**
     * Retourne les détails de l'étudiant diplômé
     * 
     * @return Map contenant les détails de l'étudiant
     */
    @Override
    public Map<String, Object> getDetails() {
        Map<String, Object> details = super.getDetails();
        details.put("type", "Graduate");
        details.put("researchArea", researchArea);
        
        return details;
    }
}