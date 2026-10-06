package hospitalmanagementsystem.dsa;

import hospitalmanagementsystem.model.Patient;

public class PatientBST {

    private class Node {
        Patient patient;
        Node left, right;

        Node(Patient patient) {
            this.patient = patient;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;

    public PatientBST() {
        this.root = null;
    }

    // --- FIX FOR LINE 29: PUBLIC INSERT METHOD ---
    public void insert(Patient patient) {
        root = insertRec(root, patient);
    }

    private Node insertRec(Node root, Patient patient) {
        if (root == null) {
            return new Node(patient);
        }
        if (patient.getId() < root.patient.getId()) {
            root.left = insertRec(root.left, patient);
        } else if (patient.getId() > root.patient.getId()) {
            root.right = insertRec(root.right, patient);
        }
        return root;
    }

    // --- SEARCH METHOD ---
    public Patient search(int id) {
        return searchRec(root, id);
    }

    private Patient searchRec(Node root, int id) {
        if (root == null || root.patient.getId() == id) {
            return (root != null) ? root.patient : null;
        }
        if (id < root.patient.getId()) {
            return searchRec(root.left, id);
        }
        return searchRec(root.right, id);
    }

    // --- FIX FOR LINE 37: PUBLIC REMOVE METHOD ---
    public void remove(int id) {
        root = removeRec(root, id);
    }

    private Node removeRec(Node root, int id) {
        if (root == null) return null;

        if (id < root.patient.getId()) {
            root.left = removeRec(root.left, id);
        } else if (id > root.patient.getId()) {
            root.right = removeRec(root.right, id);
        } else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            root.patient = minValue(root.right);
            root.right = removeRec(root.right, root.patient.getId());
        }
        return root;
    }

    private Patient minValue(Node root) {
        Patient minv = root.patient;
        while (root.left != null) {
            minv = root.left.patient;
            root = root.left;
        }
        return minv;
    }
}