package hospitalmanagementsystem.model;

public class Patient {
    private int id;
    private String name;
    private int age;
    private String disease;

    // Constructors
    public Patient() {
    }

    public Patient(int id) {
        this.id = id;
    }

    public Patient(int id, String name, int age, String disease) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.disease = disease;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    @Override
    public String toString() {
        return "Patient{id=" + id + ", name='" + name + "', age=" + age + ", disease='" + disease + "'}";
    }
}