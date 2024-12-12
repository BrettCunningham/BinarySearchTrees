package edu.psu.ist;

public class NonEmptyList implements LispList {
    private final String head;
    private final LispList tail;

    public NonEmptyList(String head, LispList tail) {
        this.head = head;
        this.tail = tail;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    //Head
    @Override
    public String head() {
        return head;
    }

    //Tail
    @Override
    public LispList tail() {
        return tail;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(head);
        LispList current = tail;
        while (!current.isEmpty()) {
            builder.append(" ").append(current.head());
            current = current.tail();
        }
        return builder.toString();
    }

    // Easier construction method (as recommended in the directions)
    public LispList cons(String value) {
        return new NonEmptyList(value, this);
    }
}