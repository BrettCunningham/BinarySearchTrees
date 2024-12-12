package edu.psu.ist;

public interface LispList {

    // This produces instances of empty lists
    static LispList empty() {
        return new EmptyList();
    }

    boolean isEmpty();  // Returns true if the list is empty, else false
    String head();      // Returns the head, which is the first element of the list
    LispList tail();    // Returns the tail, which are the remaining elements of the list
}