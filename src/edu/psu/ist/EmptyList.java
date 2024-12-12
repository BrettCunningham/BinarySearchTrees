package edu.psu.ist;

public class EmptyList implements LispList {
    @Override
    public boolean isEmpty() {
        return true;
    }

    //Exceptions for no head
    @Override
    public String head() {
        throw new UnsupportedOperationException("Empty list has no head!");
    }

    //Exceptions for no tail
    @Override
    public LispList tail() {
        throw new UnsupportedOperationException("Empty list has no tail!");
    }

    @Override
    public String toString() {
        return "";
    }
}