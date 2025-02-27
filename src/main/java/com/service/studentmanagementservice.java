package com.sms.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.sms.model.Course;
import com.sms.model.Enrollment;
import com.sms.model.GraduateStudent;
import com.sms.model.Student;
import com.sms.model.UndergraduateStudent;

/**
 * Service pour gérer les étudiants, cours et inscriptions
 */
@Service
public class StudentManagementService {
    private Map<String, Student> students = new HashMap<>();
    private Map<String, Course> courses = new HashMap<>();
    private Map<String, Enrollment> enrollments = new HashMap<>();
    
    /**
     * Ajoute un étudiant
     * 
     * @param student Étudiant à ajouter
     * @return ID de l'étudiant
     */
    public String addStudent(Student student) {
        students.put(student.getStudentID(), student);
        return student.getStudentID();
    }
    
    /**
     * Récupère un étudiant par son ID
     * 
     * @param studentID ID de l'étudiant
     * @return Étudiant ou null si non trouvé
     */
    public Student getStudent(String studentID) {
        return students.get(studentID);
    }
    
    /**
     * Ajoute un cours
     * 
     * @param course Cours à ajouter
     * @return Code du cours
     */
    public String addCourse(Course course) {
        courses.put(course.getCourseCode(), course);
        return course.getCourseCode();
    }
    
    /**
     * Récupère un cours par son code
     * 
     * @param courseCode Code du cours
     * @return Cours ou null si non trouvé
     */
    public Course getCourse(String courseCode) {
        return courses.get(courseCode);
    }
    
    /**
     * Crée une inscription
     * 
     * @param studentID ID de l'étudiant
     * @param courseCode Code du cours
     * @return ID de l'inscription ou null si échec
     */
    public String createEnrollment(String studentID, String courseCode) {
        Student student = getStudent(studentID);
        Course course = getCourse(courseCode);
        
        if (student == null || course == null) {
            return null;
        }
        
        Enrollment enrollment = new Enrollment(student, course);
        if (enrollment.register()) {
            enrollments.put(enrollment.getEnrollmentID(), enrollment);
            return enrollment.getEnrollmentID();
        }
        
        return null;
    }
    
    /**
     * Récupère une inscription par son ID
     * 
     * @param enrollmentID ID de l'inscription
     * @return Inscription ou null si non trouvée
     */
    public Enrollment getEnrollment(String enrollmentID) {
        return enrollments.get(enrollmentID);
    }
    
    /**
     * Récupère tous les étudiants
     * 
     * @return Liste des détails de tous les étudiants
     */
    public List<Map<String, Object>> getAllStudents() {
        return students.values().stream()
            .map(Student::getDetails)
            .collect(Collectors.toList());
    }
    
    /**
     * Récupère tous les cours
     * 
     * @return Liste des détails de tous les cours
     */
    public List<Map<String, Object>> getAllCourses() {
        return courses.values().stream()
            .map(Course::getDetails)
            .collect(Collectors.toList());
    }
    
    /**
     * Ajoute une note à un étudiant
     * 
     * @param studentID ID de l'étudiant
     * @param grade Note à ajouter
     * @return true si succès, false sinon
     */
    public boolean addGradeToStudent(String studentID, double grade) {
        Student student = getStudent(studentID);
        
        if (student != null) {
            student.addGrade(grade);
            return true;
        }
        
        return false;
    }
    
    /**
     * Crée un étudiant de premier cycle
     * 
     * @param name Nom
     * @param age Âge
     * @param major Spécialité
     * @return ID de l'étudiant créé
     */
    public String createUndergraduateStudent(String name, int age, String major) {
        UndergraduateStudent student = new UndergraduateStudent(name, age, major);
        return addStudent(student);
    }
    
    /**
     * Crée un étudiant diplômé
     * 
     * @param name Nom
     * @param age Âge
     * @param researchArea Domaine de recherche
     * @return ID de l'étudiant créé
     */
    public String createGraduateStudent(String name, int age, String researchArea) {
        GraduateStudent student = new GraduateStudent(name, age, researchArea);
        return addStudent(student);
    }
    
    /**
     * Initialise des données de test
     */
    public void initTestData() {
        // Créer des étudiants de test
        UndergraduateStudent undergrad = new UndergraduateStudent("Jean Dupont", 20, "Informatique");
        GraduateStudent grad = new GraduateStudent("Marie Martin", 25, "Intelligence Artificielle");
        
        addStudent(undergrad);
        addStudent(grad);
        
        // Créer des cours de test
        Course programming = new Course("Programmation Java", "CS101", 3);
        Course algorithms = new Course("Algorithmes", "CS201", 4);
        
        addCourse(programming);
        addCourse(algorithms);
        
        // Créer des inscriptions de test
        createEnrollment(undergrad.getStudentID(), programming.getCourseCode());
        createEnrollment(grad.getStudentID(), algorithms.getCourseCode());
        
        // Ajouter des notes de test
        undergrad.addGrade(85.0);
        undergrad.addGrade(90.0);
        grad.addGrade(95.0);
    }
}