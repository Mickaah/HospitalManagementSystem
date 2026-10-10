package hospitalmanagementsystem.logic;

import hospitalmanagementsystem.dsa.PatientBST;
import hospitalmanagementsystem.model.Patient;

public class PatientManager {
    
    private PatientBST patientBST;

    public PatientManager() {
        this.patientBST = new PatientBST();
    }

    // add a patient to the PatientBST from DSA package
    public void addPatient(Patient patient){
        if(patient != null){
            patientBST.insert(patient);
            System.out.println("Patient added succesfully by the Patient Manager");
        }
    }
    //search for a patient by their id
    public Patient searchPatient(int id){
        return patientBST.search(id);
    }
    // get the underlying tree if needed for traverl or display
    public PatientBST getPatientBST(){
        return patientBST;
}
    //continue based on what logic we need ;-;
}