package hospitalmanagementsystem.model;

/**
 * Model class representing a Patient in the Hospital Management System.
 * Stores core patient details including unique ID, name, age, and diagnosed disease.
 */
public class Patient {

    // Member Variables (Encapsulated fields storing patient details)
    private int id;             // Unique identifier for the patient
    private String name;        // Full name of the patient
    private int age;            // Age of the patient in years
    private String disease;     // Diagnosed illness or medical condition

    /**
     * Default / No-argument constructor.
     * Allows instantiation of an empty Patient object for flexible initialization.
     */
    public Patient() {
    }

    /**
     * Single-parameter constructor.
     * Initializes a Patient object with only a unique ID (useful for lookups or comparisons).
     *
     * @param id Unique identification number for the patient
     */
    public Patient(int id) {
        this.id = id;
    }

    /**
     * Parameterized constructor.
     * Initializes a fully populated Patient object with all attributes.
     *
     * @param id      Unique identification number
     * @param name    Full name of the patient
     * @param age     Age of the patient
     * @param disease Diagnosed illness or medical condition
     */
    public Patient(int id, String name, int age, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    // ==========================================
    // GETTERS AND SETTERS
    // ==========================================

    /**
     * Gets the patient's unique ID.
     * @return Patient ID
     */
    public int getId() {
        return id;
    }

    /**
     * Sets or updates the patient's unique ID.
     * @param id Patient ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the patient's name.
     * @return Patient name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets or updates the patient's name.
     * @param name Patient name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the patient's age.
     * @return Patient age
     */
    public int getAge() {
        return age;
    }

    /**
     * Sets or updates the patient's age.
     * @param age Patient age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Gets the patient's diagnosed disease or condition.
     * @return Medical condition/disease
     */
    public String getDisease() {
        return disease;
    }

    /**
     * Sets or updates the patient's diagnosed disease or condition.
     * @param disease Medical condition/disease
     */
    public void setDisease(String disease) {
        this.disease = disease;
    }

    /**
     * Overrides the default toString method to return a structured representation 
     * of the Patient object (useful for debugging, printing, or GUI display).
     *
     * @return Formatted string containing patient field values
     */
    @Override
    public String toString() {
        return "Patient{id=" + id + ", name='" + name + "', age=" + age + ", disease='" + disease + "'}";
    }
}