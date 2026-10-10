package hospitalmanagementsystem.logic;

import hospitalmanagementsystem.model.Doctor;
import java.util.ArrayList;
import java.util.List;

public class DoctorManager {
    
    private List<Doctor> doctorList;

    public DoctorManager() {
        this.doctorList = new ArrayList<>();
    }

    //add a new doctor 
    public void addDoctor(Doctor doctor){
        if (doctor != null){
            doctorList.add(doctor);
            System.out.println("Doctor Registered Successfully");
        }
    }
    //return the full list of doctors
    public List<Doctor> getAllDoctors(){
        return doctorList;
    }
   //continue based on what logic we need ;-;
}