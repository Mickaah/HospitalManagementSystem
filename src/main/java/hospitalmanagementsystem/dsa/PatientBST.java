package hospitalmanagementsystem.dsa;

import hospitalmanagementsystem.model.Patient;

public class PatientBST {

    // Node stores one patient in the BST
    private class Node {
        Patient patient;
        Node left, right;

        Node(Patient patient) {
            this.patient = patient;
        }
    }

    private Node root;

    // Adds a patient to the BST
    public void insert(Patient patient) {
        if (patient != null) {
            root = insert(root, patient);
        }
    }

    // Finds the correct position for a patient
    private Node insert(Node node, Patient patient) {
        if (node == null) {
            return new Node(patient);
        }

        if (patient.getId() < node.patient.getId()) {
            node.left = insert(node.left, patient);
        } else if (patient.getId() > node.patient.getId()) {
            node.right = insert(node.right, patient);
        }

        // Duplicate patient IDs are ignored
        return node;
    }

    // Searches for a patient using their ID
    public Patient search(int id) {
        Node current = root;

        while (current != null) {
            if (id == current.patient.getId()) {
                return current.patient;
            }

            if (id < current.patient.getId()) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        return null;
    }

    // Removes a patient using their ID
    public void remove(int id) {
        root = remove(root, id);
    }

    // Finds the patient node that needs to be removed
    private Node remove(Node node, int id) {
        if (node == null) {
            return null;
        }

        if (id < node.patient.getId()) {
            node.left = remove(node.left, id);
        } else if (id > node.patient.getId()) {
            node.right = remove(node.right, id);
        } else {
            // No left child
            if (node.left == null) {
                return node.right;
            }

            // No right child
            if (node.right == null) {
                return node.left;
            }

            // Find the smallest node in the right subtree
            Node smallest = node.right;

            while (smallest.left != null) {
                smallest = smallest.left;
            }

            // Replace the current patient with the smallest patient
            node.patient = smallest.patient;

            // Remove the duplicate node
            node.right = remove(node.right, smallest.patient.getId());
        }

        return node;
    }
}