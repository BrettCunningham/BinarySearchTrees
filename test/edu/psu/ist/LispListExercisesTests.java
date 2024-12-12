package edu.psu.ist;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LispListExercisesTests {

    @Test
    public void testLength() {
        // Testing for non-empty list
        LispList list = new NonEmptyList("A", new NonEmptyList("B", new NonEmptyList("C", new EmptyList())));
        Assertions.assertEquals(3, LispListExercises.length(list));

        // Testing for empty list
        list = new EmptyList();
        Assertions.assertEquals(0, LispListExercises.length(list));
    }

    @Test
    public void testContains() {
        // Test for list that is containing the key
        LispList list = new NonEmptyList("A", new NonEmptyList("B", new NonEmptyList("C", new EmptyList())));
        Assertions.assertTrue(LispListExercises.contains(list, "B"));

        // Testing for a list that is not containing the key
        Assertions.assertFalse(LispListExercises.contains(list, "D"));

        // Testing for empty list
        list = new EmptyList();
        Assertions.assertFalse(LispListExercises.contains(list, "A"));
    }

    @Test
    public void testDelete() {
        // Testing deleting an element in a list
        LispList list = new NonEmptyList("A", new NonEmptyList("B", new NonEmptyList("C", new EmptyList())));
        LispList result = LispListExercises.delete(list, "B");
        Assertions.assertEquals("A C", result.toString());

        // Testing deleting an element not in the list
        list = new NonEmptyList("A", new NonEmptyList("B", new NonEmptyList("C", new EmptyList())));
        result = LispListExercises.delete(list, "D");
        Assertions.assertEquals("A B C", result.toString());

        // Testing deleting from an empty list
        list = new EmptyList();
        result = LispListExercises.delete(list, "A");
        Assertions.assertEquals("", result.toString());
    }

    @Test
    public void testCat() {
        // Testing concatenating two non-empty lists
        LispList list1 = new NonEmptyList("A", new NonEmptyList("B", new EmptyList()));
        LispList list2 = new NonEmptyList("C", new NonEmptyList("D", new EmptyList()));
        LispList result = LispListExercises.cat(list1, list2);
        Assertions.assertEquals("A B C D", result.toString());

        // Testing concatenating a non-empty list with an empty list
        list2 = new EmptyList();
        result = LispListExercises.cat(list1, list2);
        Assertions.assertEquals("A B", result.toString());

        // Testing concatenating two empty lists
        list1 = new EmptyList();
        list2 = new EmptyList();
        result = LispListExercises.cat(list1, list2);
        Assertions.assertEquals("", result.toString());
    }

    @Test
    public void testMerge() {
        // Testing two lists merging
        LispList list1 = new NonEmptyList("A", new NonEmptyList("C", new NonEmptyList("E", new EmptyList())));
        LispList list2 = new NonEmptyList("B", new NonEmptyList("D", new NonEmptyList("F", new EmptyList())));
        LispList result = LispListExercises.merge(list1, list2);
        Assertions.assertEquals("A B C D E F", result.toString());

        // Testing lists merging when one has less elements than the other
        list1 = new NonEmptyList("A", new NonEmptyList("B", new EmptyList()));
        list2 = new NonEmptyList("1", new NonEmptyList("2", new NonEmptyList("3", new NonEmptyList("4", new EmptyList()))));
        result = LispListExercises.merge(list1, list2);
        Assertions.assertEquals("A 1 B 2 3 4", result.toString());

        // Testing when  one empty list merges
        list1 = new EmptyList();
        list2 = new NonEmptyList("X", new NonEmptyList("Y", new EmptyList()));
        result = LispListExercises.merge(list1, list2);
        Assertions.assertEquals("X Y", result.toString());

        // Testing when two empty lists merge
        list1 = new EmptyList();
        list2 = new EmptyList();
        result = LispListExercises.merge(list1, list2);
        Assertions.assertEquals("", result.toString());
    }
}