package hospitalmanagementsystem.dsa;

import hospitalmanagementsystem.model.Patient;

public class PatientBST {

    private Node root;

    // Inner class representing a node in the binary search tree
    private static class Node {
        Patient patient;
        Node left;
        Node right;

        Node(Patient patient) {
            this.patient = patient;
            this.left = null;
            this.right = null;
        }
    }

    // Delete patient by ID
    public void delete(int id) {
        root = deleteRecursive(root, id);
    }

    private Node deleteRecursive(Node current, int id) {
        if (current == null) {
            return null;
        }

        if (id < current.patient.getId()) {
            current.left = deleteRecursive(current.left, id);
        } else if (id > current.patient.getId()) {
            current.right = deleteRecursive(current.right, id);
        } else {
            // Case 1: Node with 0 or 1 child
            if (current.left == null) {
                return current.right;
            } else if (current.right == null) {
                return current.left;
            }

            // Case 2: Node with 2 children
            current.patient = findMin(current.right);
            current.right = deleteRecursive(current.right, current.patient.getId());
        }

        return current;
    }

    private Patient findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.patient;
    }
}