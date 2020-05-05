package DataStructures.DisjointSet;

/**
 * Author: Honey Kakkar
 * Project: CodingPractice
 * Date created: 6/22/2016
 */

class DSElement<E> {
    final E data;
    int rank;
    DSElement<E> parent;

    public DSElement(E data) {
        this.data = data;
        this.rank = 0;
        this.parent = this;
    }
}
