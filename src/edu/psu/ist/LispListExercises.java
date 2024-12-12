package edu.psu.ist;

public class LispListExercises {

    //Getting the length of the list
    public static int length(LispList list) {
        switch (list) {
            case EmptyList e:
                return 0;
            case NonEmptyList n:
                return 1 + length(n.tail());
            default:
                throw new IllegalStateException("List type is unknown!");
        }
    }

    //Seeing T/F is the list contains the key
    public static boolean contains(LispList list, String key) {
        switch (list) {
            case EmptyList e:
                return false;
            case NonEmptyList n:
                if (n.head().equals(key)) {
                    return true;
                } else {
                    return contains(n.tail(), key);
                }
            default:
                throw new IllegalStateException("List type is unknown!");
        }
    }

    //Deleting item in a list
    public static LispList delete(LispList list, String item) {
        switch (list) {
            case EmptyList e:
                return list;
            case NonEmptyList n:
                if (n.head().equals(item)) {
                    return n.tail();
                } else {
                    return new NonEmptyList(n.head(), delete(n.tail(), item));
                }
            default:
                throw new IllegalStateException("List type is unknown!");
        }
    }

    //Concatenating two lists (linking them together)
    public static LispList cat(LispList list1, LispList list2) {
        switch (list1) {
            case EmptyList e:
                return list2;
            case NonEmptyList n:
                return new NonEmptyList(n.head(), cat(n.tail(), list2));
            default:
                throw new IllegalStateException("List type is unknown!");
        }
    }

    //Merging two lists
    public static LispList merge(LispList list1, LispList list2) {
        switch (list1) {
            case EmptyList e:
                return list2;
            case NonEmptyList n:
                switch (list2) {
                    case EmptyList e2:
                        return list1;
                    case NonEmptyList n2:
                        return new NonEmptyList(n.head(), new NonEmptyList(n2.head(), merge(n.tail(), n2.tail())));
                    default:
                        throw new IllegalStateException("List type is unknown!");
                }
            default:
                throw new IllegalStateException("List type is unknown!");
        }
    }
}