package hospitalmanagementsystem.dsa;

import hospitalmanagementsystem.model.Appointments;
import java.util.PriorityQueue;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;

public class AppointmentPriorityQueue {
    
    private PriorityQueue<Appointments> appointmentQueue;

    public AppointmentPriorityQueue() {
        // FIXED: Using getNumericUrgency() instead of getUrgencyLevel() to handle the String properly
        appointmentQueue = new PriorityQueue<>(
            Comparator.comparingInt(Appointments::getNumericUrgency).reversed()
        );
    }

    // Add an appointment to the priority queue
    public void addAppointment(Appointments appointment) {
        appointmentQueue.offer(appointment);
    }

    // Retrieve and remove the most urgent appointment (e.g., for doctor consultation)
    public Appointments processNextAppointment() {
        return appointmentQueue.poll();
    }

    // View the most urgent appointment without removing it
    public Appointments peekNextAppointment() {
        return appointmentQueue.peek();
    }

    // Check if the priority queue is empty
    public boolean isEmpty() {
        return appointmentQueue.isEmpty();
    }

    // Return all queued appointments as a List (useful for refreshing UI tables)
    public List<Appointments> getAllAppointments() {
        return new ArrayList<>(appointmentQueue);
    }
}