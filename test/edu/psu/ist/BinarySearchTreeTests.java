package edu.psu.ist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class BinarySearchTreeTests {

    @Test
    public void testInsert() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        //Testing values
        tree.insert(100);
        tree.insert(50);
        tree.insert(150);
        tree.insert(101);
        tree.insert(140);

        //Testing root and children
        assertEquals(100, tree.root.data);
        assertEquals(50, tree.root.left.data);
        assertEquals(150, tree.root.right.data);

        //Testing other nodes
        assertEquals(101, tree.root.right.left.data);
        assertEquals(140, tree.root.right.left.right.data);
    }

    @Test
    public void testInOrder() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        //Testing values
        tree.insert(100);
        tree.insert(50);
        tree.insert(150);
        tree.insert(101);
        tree.insert(140);

        //Test the in-order traversal
        assertEquals("50 100 101 140 150 ", tree.inOrderTraversal());
    }

    @Test
    public void testInOrderEmptyTree() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        //Testing the in-order traversal for an empty tree
        assertEquals("", tree.inOrderTraversal());
    }

    @Test
    public void testInOrderSingleNode() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        //Testing value
        tree.insert(100);

        assertEquals("100 ", tree.inOrderTraversal());
    }

    @Test
    public void testContains() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        //Testing values
        tree.insert(100);
        tree.insert(50);
        tree.insert(150);
        tree.insert(101);
        tree.insert(140);

        assertTrue(tree.contains(100)); //root
        assertTrue(tree.contains(50));  //left child
        assertTrue(tree.contains(150)); //right child
        assertTrue(tree.contains(101)); //left child of right node
        assertTrue(tree.contains(140)); //right child of left node

        //Testing for a value that is not in the tree
        assertFalse(tree.contains(200));
    }

    @Test
    public void testDelete() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        //Testing values
        tree.insert(100);
        tree.insert(50);
        tree.insert(150);
        tree.insert(101);
        tree.insert(140);

        //Deleting node that has no children
        tree.delete(140);
        assertFalse(tree.contains(140));
        assertEquals("50 100 101 150 ", tree.inOrderTraversal());

        //Deleting node that has one child
        tree.delete(101);
        assertFalse(tree.contains(101));
        assertEquals("50 100 150 ", tree.inOrderTraversal());

        // Deleting node with two children
        tree.delete(50);
        assertFalse(tree.contains(50));
        assertEquals("100 150 ", tree.inOrderTraversal());

        // Checking if the tree has other nodes
        assertTrue(tree.contains(100)); // root
        assertTrue(tree.contains(150)); // right child

        // Deleting the root, which is 100, and check if the tree is updated
        tree.delete(100);
        assertFalse(tree.contains(100)); //Root shouldn't exist
        assertTrue(tree.contains(150));
        assertEquals("150 ", tree.inOrderTraversal());

        assertThrows(IllegalArgumentException.class, () -> tree.delete(500));
    }

    @Test
    public void testToString() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        //Testing values
        tree.insert(100);
        tree.insert(50);
        tree.insert(150);
        tree.insert(101);
        tree.insert(140);

        assertEquals("100 50 150 101 140 ", tree.toString());
    }

    // New test for partition method
    @Test
    public void testPartition() {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        //Testing values
        tree.insert(100);
        tree.insert(50);
        tree.insert(150);
        tree.insert(101);
        tree.insert(140);

        // Test partition method to get all values that are greater or equal to 100
        ArrayList<Integer> result = tree.partition(100);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(100);
        expected.add(150);
        expected.add(101);
        expected.add(140);

        assertEquals(expected, result);
    }
}
