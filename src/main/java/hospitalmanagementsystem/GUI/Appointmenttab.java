package hospitalmanagementsystem.GUI;

import hospitalmanagementsystem.database.AppointmentDAO;
import hospitalmanagementsystem.dsa.SystemDataStructures;
import hospitalmanagementsystem.logic.AppointmentManager;
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
    private JLabel txtTitle, searchLabel;
    private JTextField searchField;
    private JButton btnSearch, btnProcessNext, btnRefresh, btnBack;
    private DefaultTableModel model;
    private JTable table;
    private JPanel backgroundPanel;
    private ImageIcon image;
    
    private SystemDataStructures systemDataStructures;

    public Appointmenttab() {
        this(new SystemDataStructures());
    }

    public Appointmenttab(SystemDataStructures sharedDataStructures) {
        this.systemDataStructures = (sharedDataStructures != null) ? sharedDataStructures : new SystemDataStructures();

        setTitle("HOSPITAL MANAGEMENT APP - APPOINTMENTS");
        setSize(875, 520);
        setLayout(null);
        backgroundPanel = new JPanel();
        backgroundPanel.setLayout(null);
        backgroundPanel.setBackground(new Color(153, 197, 144));
        setContentPane(backgroundPanel);

        image = new ImageIcon("src/main/java/hospitalmanagementsystem/GUI/Logo.png");
        Image scaledImage = image.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
        setIconImage(scaledImage);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        // --- TITLE ---
        txtTitle = new JLabel("Appointment Queue Management");
        txtTitle.setBounds(20, 15, 300, 30);
        txtTitle.setFont(new Font("Western", Font.BOLD, 18));
        add(txtTitle);

        // --- SEARCH BAR COMPONENTS (Restored & Aligned) ---
        searchLabel = new JLabel("Search:");
        searchLabel.setBounds(335, 18, 55, 25);
        searchLabel.setFont(new Font("Western", Font.BOLD, 13));
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(390, 18, 240, 25);
        searchField.setFont(new Font("Western", Font.PLAIN, 13));
        add(searchField);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(635, 16, 90, 28);
        add(btnSearch);

        // --- TABLE SETUP ---
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        model.addColumn("Appt ID");
        model.addColumn("Patient Name");
        model.addColumn("Age");
        model.addColumn("Gender");
        model.addColumn("Doctor");
        model.addColumn("Specialty"); // shows the doctor's specialty for each appointment
        model.addColumn("Date");
        model.addColumn("Urgency");
        model.addColumn("Concern");

        table = new JTable(model);
        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setResizingAllowed(false);
        table.getTableHeader().setBackground(Color.decode("#bdfdc5"));
        table.getTableHeader().setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 60, 815, 340);
        add(scrollPane);

        // --- ACTION BUTTONS ---
        btnProcessNext = new JButton("Process Next Patient");
        btnProcessNext.setBounds(370, 420, 170, 35);
        add(btnProcessNext);

        btnRefresh = new JButton("Refresh Queue");
        btnRefresh.setBounds(550, 420, 135, 35);
        add(btnRefresh);

        btnBack = new JButton("Back");
        btnBack.setBounds(695, 420, 140, 35);
        add(btnBack);

        btnSearch.addActionListener(this);
        btnProcessNext.addActionListener(this);
        btnRefresh.addActionListener(this);
        btnBack.addActionListener(this);

        // Populate table from Priority Queue on launch
        refreshTableData();
    }

    // Builds one table row for an appointment, in the same order as the table columns (specialty is filled in from the doctor name when empty)
    private Object[] buildRow(Appointments appt) {
        return new Object[]{
            appt.getAppointmentId(),
            appt.getPatientName(),
            appt.getPatientAge(),
            appt.getPatientGender(),
            appt.getDoctorName(),
            AppointmentDAO.resolveSpecialty(appt),
            appt.getAppointmentDate(),
            appt.getUrgencyLevel(),
            appt.getConcern()
        };
    }

    public void refreshTableData() {
        // Pulls appointments saved in MySQL into the queue first, so rows booked in earlier runs show up too
        AppointmentManager.getInstance().loadFromDatabase();

        model.setRowCount(0); // Clear current table rows

        // The list comes back sorted by urgency number (highest first), then by appointment ID
        List<Appointments> appointments = AppointmentManager.getInstance().getPriorityQueueAppointments();

        for (Appointments appt : appointments) {
            model.addRow(buildRow(appt));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnBack) {
            HospitalSysApp menu = new HospitalSysApp();
            this.setVisible(false);
            menu.setVisible(true);
        } 
        else if (e.getSource() == btnRefresh) {
            searchField.setText("");
            refreshTableData();
            JOptionPane.showMessageDialog(this, "Appointment queue refreshed.");
        } 
        else if (e.getSource() == btnSearch) {
            String keyword = searchField.getText().trim().toLowerCase();
            if (keyword.isEmpty()) {
                refreshTableData();
                return;
            }

            model.setRowCount(0);
            List<Appointments> appointments = AppointmentManager.getInstance().getPriorityQueueAppointments();
            boolean found = false;

            for (Appointments appt : appointments) {
                if (appt.getPatientName().toLowerCase().contains(keyword) ||
                    String.valueOf(appt.getAppointmentId()).contains(keyword) ||
                    appt.getDoctorName().toLowerCase().contains(keyword) ||
                    AppointmentDAO.resolveSpecialty(appt).toLowerCase().contains(keyword) || // search also matches the specialty
                    appt.getUrgencyLevel().toLowerCase().contains(keyword)) {
                    
                    model.addRow(buildRow(appt));
                    found = true;
                }
            }

            if (!found) {
                JOptionPane.showMessageDialog(this, "No matching appointments found.", "Search Result", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        else if (e.getSource() == btnProcessNext) {
            Appointments nextPatient = AppointmentManager.getInstance().processNextAppointment();

            if (nextPatient != null) {
                AppointmentDAO dao = new AppointmentDAO();
                dao.delete(nextPatient.getAppointmentId());

                refreshTableData();
                JOptionPane.showMessageDialog(this, 
                    "Processing next patient:\nName: " + nextPatient.getPatientName() + 
                    "\nUrgency: " + nextPatient.getUrgencyLevel() + 
                    "\nDoctor: " + nextPatient.getDoctorName(), 
                    "Next Patient Called", 
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No patients in queue.");
            }
        }
    }
}