package DataStructures.DisjointSet;

/*
 * Author: Honey Kakkar
 * Created on: December 28, 2016
 * Package: DataStructures.DisjointSet
 * Project: Coding Practice in JAVA
 */

class DSElement<E>{
    final E data;
    int rank;
    DSElement<E> parent;

    public DSElement(E data, int rank) {
        this.data = data;
        this.rank = rank;
        this.parent = null;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof DSElement))
            return false;
        DSElement dsObj = (DSElement) obj;
        return this.parent == dsObj.parent && this.data == dsObj.data && this.rank == dsObj.rank;
    }
}
