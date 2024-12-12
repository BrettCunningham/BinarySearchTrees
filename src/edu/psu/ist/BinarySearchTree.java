package edu.psu.ist;

import java.util.ArrayList;

public class BinarySearchTree<T extends Comparable<T>> {
    protected Node<T> root;

    public BinarySearchTree() {
        this.root = null;
    }

    //Big O of O(log n) avg (balanced tree), O(n) worst (unbalanced tree).
    public void insert(T data) {
        this.root = this.insert(this.root, data);
    }

    private Node<T> insert(Node<T> current, T data) {
        // if the current is null, just set the current to the new node
        if (current == null) {
            return new Node<>(data);
        }

        // comparing the data to the current node to see which way to go
        int comparison = data.compareTo(current.data);

        if (comparison < 0) {
            current.left = this.insert(current.left, data);
        } else if (comparison > 0) {
            current.right = this.insert(current.right, data);
        } else {
            // Exception if data is already there
            throw new IllegalArgumentException("Data already exists in the tree.");
        }

        return current;
    }

    //Big O of O(log n) avg (balanced tree), O(n) worst (unbalanced tree).
    public boolean contains(T data) {
        // trying to find the node
        return contains(this.root, data);
    }

    private boolean contains(Node<T> current, T data) {

        if (current == null) {
            return false; // base case is that the data is not found
        }

        // comparing data to current node
        int comparison = data.compareTo(current.data);

        if (comparison < 0) {
            // searching left subtree
            return contains(current.left, data);
        } else if (comparison > 0) {
            // searching right subtree
            return contains(current.right, data);
        } else {
            // finding data
            return true;
        }
    }

    //Big O of O(log n) avg, O(n) worst.
    public void delete(T data) {
        this.root = delete(this.root, data); // "kick off method"
    }

    private Node<T> delete(Node<T> current, T data) {
        // finding location the node that has data
        if (current == null) {
            throw new IllegalArgumentException("Data is not found in the tre!");
        }

        if (data.compareTo(current.data) < 0) {
            current.left = delete(current.left, data);
        } else if (data.compareTo(current.data) > 0) {
            current.right = delete(current.right, data);
        } else {
            // if it is a leaf note simply deleting it
            if (current.left == null && current.right == null) {
                return null;  //removing since it has no children
            }

            // if only one child, reassign the parent pointer to the child
            if (current.left == null) {
                return current.right;  // replacing with right child
            } else if (current.right == null) {
                return current.left;  // replacing  with left child
            }

            // seeing if it has children; left then right
            // then replacing the deleted node with found node
            Node<T> successor = findMin(current.right);
            current.data = successor.data;  // replacing with the successor's data
            current.right = delete(current.right, successor.data);  // deleting successor
        }

        return current;
    }

    //Finds the smallest value in the BST
    private Node<T> findMin(Node<T> current) {
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public String inOrderTraversal() {
        return this.inOrderTraversal(this.root);
    }

    private String inOrderTraversal(Node<T> current) {
        StringBuilder stringBuilder = new StringBuilder();

        if (current != null) {
            // Going left first
            stringBuilder.append(this.inOrderTraversal(current.left));

            // Printing current node
            stringBuilder.append(current.data);
            stringBuilder.append(" ");

            // Going right
            stringBuilder.append(this.inOrderTraversal(current.right));
        }

        return stringBuilder.toString();
    }


    public String toString() {
        return this.toString(this.root);
    }

    //Big O of O(n) avg, O(n) worst.
    private String toString(Node<T> current) {
        StringBuilder stringBuilder = new StringBuilder();

        if (current != null) {
            // Visiting root , then left subtree, then right subtree
            stringBuilder.append(current.data).append(" ");
            stringBuilder.append(this.toString(current.left));
            stringBuilder.append(this.toString(current.right));
        }

        return stringBuilder.toString();
    }

    //Big O of O(n) avg, O(n) worst.
    // Returns all >= data
    public ArrayList<T> partition(T data) {
        ArrayList<T> result = new ArrayList<>();
        this.partition(this.root, data, result);
        return result;
    }

    private void partition(Node<T> current, T data, ArrayList<T> result) {
        if (current != null) {
            if (current.data.compareTo(data) >= 0) {
                result.add(current.data);
            }

            // Going to left and right subtree
            partition(current.left, data, result);
            partition(current.right, data, result);
        }
    }
}
