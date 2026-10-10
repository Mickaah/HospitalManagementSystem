package hospitalmanagementsystem.logic;

import hospitalmanagementsystem.model.Appointments;
import java.util.ArrayList;
import java.util.List;

public class AppointmentManager {
    
    private List<Appointments> appointmentList;

    public AppointmentManager() {
        this.appointmentList = new ArrayList<>();
    }

    //schedule a new appointments
    public void scheduleAppointment(Appointments appointment){
        if(appointment != null){
            appointmentList.add(appointment);
            System.out.println("Appointment Scheduled Successfully");
        }
    }
    
    
    //continue based on what logic we need ;-;
    
}