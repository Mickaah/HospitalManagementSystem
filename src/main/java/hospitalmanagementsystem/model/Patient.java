package hospitalmanagementsystem.model;

import java.util.ArrayList;
import java.util.List;

public class Patient {
    private int id;
    private String name;
    private int age;
    private List<Integer> appointmentIds;

    public Patient(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.appointmentIds = new ArrayList<>();
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }

    public void addAppointmentId(int apptId) {
        appointmentIds.add(apptId);
    }

    public List<Integer> getAppointmentIds() {
        return appointmentIds;
    }

    @Override
    public String toString() {
        return name + " (ID: " + id + ")";
    }
}