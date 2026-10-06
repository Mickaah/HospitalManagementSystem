package hospitalmanagementsystem.DSA;

import hospitalmanagementsystem.model.*;
import java.util.*;

public class SystemDataStructures {

    // Hashing for quick patient verification
    public Map<Integer, Patient> patientMap = new HashMap<>();

    // Priority queue for appointment scheduling based on urgency
    public PriorityQueue<Appointments> appointmentQueue = new PriorityQueue<>();

    // Binary Search Tree for organizing patients by ID
    private class Node {
        Patient patient;
        Node left, right;
        Node(Patient patient) { this.patient = patient; }
    }

    public Node bstRoot;

    public void insertBST(Patient p) {
        bstRoot = insert(bstRoot, p);
    }

    private Node insert(Node root, Patient p) {
        if (root == null) return new Node(p);
        if (p.getId() < root.patient.getId()) root.left = insert(root.left, p);
        else if (p.getId() > root.patient.getId()) root.right = insert(root.right, p);
        return root;
    }
}