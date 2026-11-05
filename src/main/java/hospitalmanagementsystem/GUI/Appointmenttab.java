package hospitalmanagementsystem.GUI;

import hospitalmanagementsystem.database.AppointmentDAO;
import hospitalmanagementsystem.model.Appointments;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mikeyks
 */
public class Appointmenttab extends JFrame implements ActionListener {
    private JLabel title, seeappt, search;
    private JTextField searchF;
    private DefaultTableModel sched;
    private JButton btnsrch, btnback;
    private JTable tab;
    private JPanel backgroundPanel;
    private ImageIcon image;
    
    public Appointmenttab() {
        setTitle("APPOINTMENT MANAGER");
        setSize(870, 500);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        backgroundPanel = new JPanel();
        backgroundPanel.setLayout(null);
        backgroundPanel.setBackground(new Color(153, 197, 144));
        setContentPane(backgroundPanel);
        image = new ImageIcon("src/main/java/hospitalmanagementsystem/GUI/Logo.png");
        Image scaledImage = image.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        setIconImage(scaledImage);
        this.setLocationRelativeTo(null);
        
        title = new JLabel("Welcome!");
        title.setBounds(10, 5, 280, 50);
        title.setFont(new Font("Western", Font.PLAIN, 20));
        add(title);
        
        seeappt = new JLabel("see appointments here: ");
        seeappt.setBounds(10, 30, 280, 50);
        seeappt.setFont(new Font("Western", Font.PLAIN, 16));
        add(seeappt);
        
        search = new JLabel("Search Appointments");
        search.setBounds(10, 57, 200, 50);
        search.setFont(new Font("Western", Font.PLAIN, 14));
        add(search);
        
        searchF = new JTextField();
        searchF.setBounds(150, 74, 200, 20);
        searchF.setFont(new Font("Western", Font.PLAIN, 14));
        add(searchF);
        
        sched = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        sched.addColumn("patient name:");
        sched.addColumn("doctor name:");
        sched.addColumn("specialty:");
        sched.addColumn("Appointment Date");
        sched.addColumn("ID:"); 
        
        tab = new JTable(sched);
        tab.getTableHeader().setReorderingAllowed(false);
        tab.getTableHeader().setResizingAllowed(false);
        tab.getTableHeader().setBackground(Color.decode("#bdfdc5"));
        tab.getTableHeader().setForeground(Color.BLACK);
        
        JScrollPane scroll = new JScrollPane(tab);
        scroll.setBounds(10, 100, 720, 335);
        scroll.setFont(new Font("Western", Font.PLAIN, 14));
        add(scroll);
        
        btnsrch = new JButton("Search");
        btnsrch.setBounds(750, 340, 90, 50);
        add(btnsrch); 
        
        btnback = new JButton("Back");
        btnback.setBounds(760, 400, 70, 50);
        add(btnback); 
        
        btnsrch.addActionListener(this);
        btnback.addActionListener(this);
        
        loadAppointmentsFromDatabase();
    }
   
    private void loadAppointmentsFromDatabase() {
        sched.setRowCount(0);
        AppointmentDAO dao = new AppointmentDAO();
        List<Appointments> list = dao.getAll();
        
        for (Appointments a : list) {
            Object[] row = {
                a.getPatientName(),
                a.getDoctorName(),
                a.getSpecialty(),
                a.getAppointmentDate(),
                a.getAppointmentId() // Correctly targets appointment_id
            };
            sched.addRow(row);
        }
    }
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnsrch) {
            // Optional search logic using searchF.getText()
        }    
        if (e.getSource() == btnback) {
           hospitalmanagementsystem.GUI.HospitalSysApp menu = new hospitalmanagementsystem.GUI.HospitalSysApp();
            this.setVisible(false);
            menu.setVisible(true);
       }
    }
}