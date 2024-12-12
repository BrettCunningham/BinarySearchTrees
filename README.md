[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/45AIHa3L)
# Binary Search Trees

In this assignment you'll be implementing a mutable binary search tree started/outlined in the lecture videos M07-L03. For the purposes of this assignment, the tree you develop will not store duplicated items. All methods are to be recusive unless otherwise specified -- see the rubric for additional criteria.

## Initial Setup

Create a class `BinarySearchTree` that is parameterized by a generic type parameter, `T` that `extends Comparable<T>`. 
Next you'll need a `Node` class that stores both the data for a given node in the tree as well as its left and right children. Your `BinarySearchTree` class should maintain a field, `Node root`, that stores a reference to the root of the tree as well as a constructor that takes no parameters and initializes the `root` to null.

## Required Operations

Your binary search tree class should export the following (`public`) methods: 

#### `insert(x)`

This method takes a piece of data `x` of type `T` and adds it to the tree as outlined in lecture (returns nothing). 
Throw an `IllegalArgumentException` if `data` already exists in the tree.

#### `delete(x)`

This method takes a piece of data `x` of type `T` and deletes it from the tree using the approach outlined in lecture. The method should return nothing. Throw an `IllegalArgumentException` if the data doesn't exist within the tree. 

#### `contains(x)`

Returns `true` only if `data` exists in the tree; `false` otherwise.

#### `toString()`

Your class should provide a toString method that recursively renders the tree using a pre-order traversal (you can use a helper method if needed to accomplish this).

#### `partition(data)`

This method takes a piece of data of type `T` and returns an arraylist containing all items in the tree that are >= to `data`

## Unit Testing

Your tests should cover all the methods described above. I won't prescribe a fixed number other than to say that they should make the case that each operation is working as expected.

# Optional Extra Credit: LISPy Lists

> Completing this part of the assignment, testing (and doing both well) will net you anywhere from 1-50pts bonus pts on a prior PA. Below is sketch of how it can be started, feel free to talk to me if you would like to know more or if you are on the right track.

The LISP language, created in 1958 by John McCarthy (MIT), implements linked lists in a very elegant way. You will explore a Java analog in this additional part. Conceptually, the *tail* of a list -- that is, the list with its head node removed -- is also a list. The tail of that list is again a list and so on, until you reach the empty list. 

Here is a tentative Java interface for such a list:
```java
/** A list of entries of some number of (homogenously) typed elements {@code A}. */
public interface LispList {
  // a "static factory" method for producing an empty list instance
  static boolean empty() { return new EmptyList(); }

  boolean isEmpty();
  String head(); // NOTE: unlike your BST above, our LispList can only hold Strings
  LispList tail();
  // ...
}
```

There are two kinds/varieties of a `LispList`, empty and non-empty lists:
```java
public class EmptyList     implements LispList { ... }
public class NonEmptyList  implements LispList { ... }
```
These classes are pretty trivial. The `EmptyList` has no instance variables/fields. Its `head` and `tail` methods simply throw an `UnsupportedOperationException` and its empty method returns `true`. The `NonEmptyList` class will need to store fields to track the head and tail.

Here is one way of making a LISP list with three elements:

```java
LispList list = new NonEmptyList("A", new NonEmptyList("B",
  new NonEmptyList("C", new EmptyList())));
```

This is a bit tedious, and it is a good idea to supply a convenience method, `cons` that calls the constructor, as well a static variable `Nil` that is an instance of the empty list.

Then the list construction code becomes:
```java
// toString will ideally give: [A, B, C]
LispList list = LispList.empty().cons("C").cons("B").cons("A");
```

#### breaking down what's going on above:

We'll look at the construction of the above list:

So, starting with the empty list we get:
```
new EmptyList()
```

A new list is constructed with `C` as the head and `Nil` as the tail:
```
[C] -> EmptyList                    // "high level view"
new LispList("C", new EmptyList())  // "structure view"
```

Next, a new list is constructed with `B` as the head and the previous list:
```
[B] -> [C] -> Nil
new LispList("B", new LispList("C", new EmptyList()))
```

Next the `A` gets tacked on the front, etc.

## Some exercises

These should all be done in a separate file called `LispListExercises` and the methods should all be marked `static`. Use recursion for all of these -- write the unit tests as you work through these.

### method: `length`

Takes a `LispList` as a parameter and computes and returns the length of the list.

```java
length([A, B, C]) // 3
```

### method: `contains` 

Takes a `LispList` and some String `key` and and returns true only if `key` appears in the list; false otherwise.
```java
contains([A, B, C], "B") // true
```

### method: `delete` 

Takes a `LispList` as well as the item to be removed and returns a `LispList` with the item removed.
```java
delete([A, B, C], "B")   // returned list: [A, C]
delete([A, B, C], "D")   // returned list: [A, B, C]
```

### method: `cat` 

Takes two `LispList`s and appends them together and returns the resulting (concatenated) list.

```java
cat([A, B], [C, D]) // [A, B, C, D]
cat([], [X, Y])     // [X, Y]
```
   
### method: `merge` 

Takes two LispLists and alternates between the elements, adding the remainder to the end.

```java
merge([A, C, E], [B, D, F])   // [A, B, C, D, E, F]
merge([A, B], [1, 2, 3, 4])   // [A, 1, B, 2, 3, 4]
```
(note: the numbers in the second example will just be strings, i.e.: `"1"`)

## Handin

When you are ready to submit (or simply want to 'checkin' your work for the day), open the terminal, cd to the project directory, make a commit by typing:

> git commit -am "message goes here"

then follow this up with a

> git push origin main
