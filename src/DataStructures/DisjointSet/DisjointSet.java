package DataStructures.DisjointSet;

import java.util.HashMap;
import java.util.List;

/**
 * Author: Honey Kakkar
 * Project: CodingPractice
 * Date created: 6/22/2016
 */
public class DisjointSet<T> {

    private final HashMap<T, DSElement<T>> elements;     // map of data and its respective DSElement
    private int numberOfSets;     // number of disjoint set components

    /**
     * Initializes an empty union-find data structure.
     */
    public DisjointSet() {
        elements = new HashMap<>();
        numberOfSets = 0;
    }

    /**
     * Initializes an empty union-find data structure
     * when maximum capacity of set is known.
     *
     * @param N = number of elements expected to be inserted into set
     */
    public DisjointSet(int N) {
        elements = new HashMap<>(N);
        numberOfSets = 0;
    }

    /**
     * Inserts new element in the given list to disjoint set.
     * Initially, new element is in its own set (means it is its own parent).
     *
     * @param listOfItems: list of items to be inserted
     */
    public void insert(List<T> listOfItems) {
        if (listOfItems == null) {
            return;
        }
        for (T item : listOfItems) {
            insert(item);
        }
    }

    /**
     * Inserts new element to disjoint set.
     * Initially, new element is in its own set (means it is its own parent).
     *
     * @param newData: value of new element to be inserted
     */
    public void insert(T newData) {
        if (elements.containsKey(newData)) {
            return;
        }
        DSElement<T> newElement = new DSElement<>(newData);
        elements.put(newData, newElement);
        ++numberOfSets;
    }

    /**
     * MMerges together the sets that the given two elements belong to,
     * set containing element {@code data1} and set containing element {@code data2}.
     *
     * @param data1 one element
     * @param data2 the other element
     * @return true if sets containing both elements are merged;
     * false if elements already belong to same set
     * @throws IllegalArgumentException if any of the elements not present in set
     */
    public boolean union(T data1, T data2) {
        T rep1 = find(data1);
        T rep2 = find(data2);
        if (rep1 == rep2) {
            return false;
        }

        DSElement<T> root1 = elements.get(rep1);
        DSElement<T> root2 = elements.get(rep2);
        // make root of smaller rank point to root of larger rank
        if (root1.rank < root2.rank) {
            root1.parent = root2;
        } else if (root1.rank > root2.rank) {
            root2.parent = root1;
        } else {
            root2.parent = root1;
            root1.rank++;
        }
        --numberOfSets;
        return true;
    }

    /**
     * Returns the representative DSElement for the set containing the given element.
     *
     * @param element: an element
     * @return the representative element of the set containing {@code element}
     * @throws IllegalArgumentException if element not present in set
     */
    public T find(T element) {
        if (!elements.containsKey(element)) {
            throw new IllegalArgumentException("element not in the set");
        }
        return find(elements.get(element)).data;
    }

    /**
     * Returns the representative DSElement for the passed DSElement.
     * Performs full path compression
     * <p>
     * Path compression flattens the structure of the tree
     * by making every node point to the root
     *
     * @param current: a DSElement
     * @return the representative DSElement of the set containing {@code current}
     */
    private DSElement<T> find(DSElement<T> current) {
        if (current.parent != current) {
            current.parent = find(current.parent);
        }
        return current.parent;
    }

    /**
     * Returns true if the two elements are in the same set.
     *
     * @param data1 one element
     * @param data2 the other element
     * @return {@code true} if {@code data1} and {@code data2} are in the same set;
     * {@code false} otherwise
     * @throws IllegalArgumentException if any of elements not present in set
     */
    public boolean areConnected(T data1, T data2) {
        return find(data1) == find(data2);
    }

    /**
     * Returns the number of disjoint sets overall. This number decreases monotonically as time progresses;
     * Each call to union() either decrements the number by one or leaves it unchanged.
     *
     * @return the number of disjoint sets
     */
    public int getNumberOfSets() {
        return numberOfSets;
    }
}
