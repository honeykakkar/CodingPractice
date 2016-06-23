package DataStructures;

import java.util.HashMap;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 6/22/2016
 */
public class DisjointSet {

    class DSElement{
        long data;
        int rank;
        DSElement parent;

        public DSElement(long data, int rank) {
            this.data = data;
            this.rank = rank;
            this.parent = null;
        }

        @Override
        public boolean equals(Object obj) {
            DSElement dsObj = (DSElement) obj;
            return this.parent == dsObj.parent && this.data == dsObj.data && this.rank == dsObj.rank;
        }
    }

    HashMap<Long, DSElement> elements;

    public DisjointSet() {
        elements = new HashMap<>();
    }

    public void insert(long newData){
        DSElement newElement = new DSElement(newData, 0);
        newElement.parent = newElement;
        elements.put(newData, newElement);
    }

    public boolean union(long data1, long data2){
        DSElement element1 = elements.get(data1);
        DSElement element2 = elements.get(data2);
        if(element1 == null || element2 == null || element1.equals(element2))
            return false;

        if(element1.rank >= element2.rank){
            element2.parent = element1;
            if(element1.rank == element2.rank)
                ++element1.rank;
        }
        else
            element1.parent = element2;
        return true;
    }

    public long find(long data){
        if(elements.get(data) != null)
            return find(elements.get(data)).data;
        return Long.MIN_VALUE;
    }

    public DSElement find(DSElement current){
        DSElement currParent = current.parent;
        if(currParent == current)
            return currParent;
        current.parent = find(currParent);
        return currParent;
    }

    public static void main(String[] args) {
        DisjointSet set = new DisjointSet();
        set.insert(1);
        set.insert(2);
        set.insert(3);
        set.insert(5);
        set.insert(8);
        set.insert(13);
        set.insert(21);

        set.union(1,21);
        set.union(2,13);
        set.union(3,8);
        set.union(1,8);

        System.out.println("The representative of the set with element 5 is : " + set.find(5));
        System.out.println("The representative of the set with element 3 is : " + set.find(3));
        System.out.println("The representative of the set with element 21 is : " + set.find(21));
        System.out.println("The representative of the set with element 8 is : " + set.find(8));
        System.out.println("The representative of the set with element 13 is : " + set.find(13));
        System.out.println("The representative of the set with element 2 is : " + set.find(2));
    }
}
