package hospitalmanagementsystem.dsa;

import hospitalmanagementsystem.model.Appointments;
import hospitalmanagementsystem.model.Patient;
import java.util.Comparator;
import java.util.PriorityQueue;

public class SystemDataStructures {

    private PriorityQueue<Appointments> appointmentPriorityQueue;

    public SystemDataStructures() {
        // High urgency numbers come first (5 -> 1)
        this.appointmentPriorityQueue = new PriorityQueue<>(
            Comparator.comparingInt(Appointments::getUrgencyLevel).reversed()
        );
    }

    public void addPatient(Patient patient) {
        // Your patient logic here
    }
}