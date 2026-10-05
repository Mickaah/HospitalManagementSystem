package hospitalmanagementsystem.model;
import hospitalmanagementsystem.MODEL.Appointments;
import java.util.ArrayList;
import java.util.List;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import hospitalmanagementsystem.GUI.HospitalSysApp;

public class Patient extends JFrame implements ActionListener{
    private JLabel txtTitle;
    private JButton btnBack;
    private int id;
    private String name;
    private int age;
    private String gender;
    private String illness;
    private List<String> medicalHistory;
    private List<Appointments> appointments;

    public Patient(int id, String name, int age, String gender, String illness) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.illness = illness;
        this.medicalHistory = new ArrayList<>();
        this.appointments = new ArrayList<>();
    }
    public Patient(){
    setTitle("HOSPITAL MANAGEMENT APP");
        setSize(600, 600);
        setLayout(null);
        setVisible(true);
        this.setLocationRelativeTo(this);
        
        txtTitle = new JLabel("Book an Appointment");
        txtTitle.setBounds(160, 5, 280, 50);
        txtTitle.setFont(new Font("Western", Font.PLAIN, 20));
        add(txtTitle);
        
        btnBack = new JButton("Back");
        btnBack.setBounds(450, 500, 100, 50);
        add(btnBack);
        
        btnBack.addActionListener(this);
}

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getIllness() { return illness; }
    public void setIllness(String illness) { this.illness = illness; }

    public List<String> getMedicalHistory() { return medicalHistory; }
    public void addMedicalRecord(String record) { this.medicalHistory.add(record); }

    public List<Appointments> getAppointments() { return appointments; }
    public void addAppointment(Appointments appointment) { this.appointments.add(appointment); }

    @Override
    public String toString() {
        return "Patient ID: " + id + " | Name: " + name + " | Age: " + age + " | Illness: " + illness;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnBack){
            hospitalmanagementsystem.GUI.HospitalSysApp menu = new hospitalmanagementsystem.GUI.HospitalSysApp();
            this.setVisible(false);
            menu.setVisible(true);
        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}