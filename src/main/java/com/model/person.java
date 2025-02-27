package com.sms.model;

import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * Classe abstraite représentant une personne
 */
public abstract class Person {
    @Getter @Setter
    private String name;
    
    @Getter @Setter
    private int age;
    
    /**
     * Constructeur
     * 
     * @param name Nom de la personne
     * @param age Âge de la personne
     */
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    /**
     * Méthode abstraite pour obtenir les détails de la personne
     * 
     * @return Map contenant les détails de la personne
     */
    public abstract Map<String, Object> getDetails();
}