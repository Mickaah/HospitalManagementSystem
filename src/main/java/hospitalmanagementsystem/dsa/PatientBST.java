package hospitalmanagementsystem.dsa;

import hospitalmanagementsystem.model.Patient;

public class PatientBST {

    // Node stores one patient in the BST
    private class Node {
        Patient patient;
        Node left, right;

        Node(Patient p) {
            patient = p;
        }
    }

    private Node root;

    // Adds a patient to the BST
    public void insert(Patient p) {
        root = insert(root, p);
    }

    // Finds the correct position for a patient
    private Node insert(Node n, Patient p) {
        if (n == null)
            return new Node(p);

        if (p.getId() < n.patient.getId())
            n.left = insert(n.left, p);
        else if (p.getId() > n.patient.getId())
            n.right = insert(n.right, p);

        return n;
    }

    // Searches for a patient by ID
    public Patient search(int id) {
        Node n = root;

        while (n != null) {
            if (id == n.patient.getId())
                return n.patient;

            n = id < n.patient.getId() ? n.left : n.right;
        }

        return null;
    }

    // Removes a patient from the BST
    public void remove(int id) {
        root = remove(root, id);
    }

    // Finds and removes the patient
    private Node remove(Node n, int id) {
        if (n == null)
            return null;

        if (id < n.patient.getId())
            n.left = remove(n.left, id);

        else if (id > n.patient.getId())
            n.right = remove(n.right, id);

        else {
            // If there is no left child
            if (n.left == null)
                return n.right;

            // If there is no right child
            if (n.right == null)
                return n.left;

            // Finds the smallest value on the right
            Node min = n.right;

            while (min.left != null)
                min = min.left;

            n.patient = min.patient;
            n.right = remove(n.right, min.patient.getId());
        }

        return n;
    }
}