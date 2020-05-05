package DataStructures.DisjointSet;

import DataStructures.Graphs.Vertex;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class DisjointSetTest {

    @Test
    void testInsert() {
        DisjointSet<Integer> set = new DisjointSet<>();
        Assertions.assertEquals(0, set.getNumberOfSets());
        set.insert(0);
        Assertions.assertEquals(1, set.getNumberOfSets());
        set.insert(0);
        Assertions.assertEquals(1, set.getNumberOfSets());
        set.insert(1);
        Assertions.assertEquals(2, set.getNumberOfSets());
        set.insert(1);
        Assertions.assertEquals(2, set.getNumberOfSets());
        set.insert(2);
        Assertions.assertEquals(3, set.getNumberOfSets());
        set.insert(3);
        Assertions.assertEquals(4, set.getNumberOfSets());

        set = new DisjointSet<>(10);
        Assertions.assertEquals(0, set.getNumberOfSets());
        set.insert(Arrays.asList(1, 2, 3, 5, 8, 13, 21, 34));
        Assertions.assertEquals(8, set.getNumberOfSets());
    }

    @Test
    void testUnion() {
        DisjointSet<Integer> set = new DisjointSet<>();
        Assertions.assertEquals(0, set.getNumberOfSets());
        set.insert(Arrays.asList(1, 2, 3, 5, 8, 13, 21, 34));
        Assertions.assertEquals(8, set.getNumberOfSets());
        Assertions.assertThrows(IllegalArgumentException.class, () -> set.union(0, 1));
        Assertions.assertTrue(set.union(1, 21));
        Assertions.assertTrue(set.areConnected(1, 21));
        Assertions.assertEquals(7, set.getNumberOfSets());
        Assertions.assertFalse(set.union(1, 21));
        Assertions.assertTrue(set.areConnected(1, 21));
        Assertions.assertEquals(7, set.getNumberOfSets());
        Assertions.assertTrue(set.union(2, 13));
        Assertions.assertEquals(6, set.getNumberOfSets());
        Assertions.assertTrue(set.union(3, 8));
        Assertions.assertEquals(5, set.getNumberOfSets());
        Assertions.assertTrue(set.union(1, 8));
        Assertions.assertEquals(4, set.getNumberOfSets());
        Assertions.assertTrue(set.union(3, 5));
        Assertions.assertEquals(3, set.getNumberOfSets());
        Assertions.assertThrows(IllegalArgumentException.class, () -> set.union(3, 35));
        Assertions.assertEquals(3, set.getNumberOfSets());
        Assertions.assertTrue(set.union(3, 34));
        Assertions.assertEquals(2, set.getNumberOfSets());

        DisjointSet<Vertex> vSet = new DisjointSet<>();
        Assertions.assertEquals(0, vSet.getNumberOfSets());
        Vertex V0 = new Vertex("V0");
        Vertex V1 = new Vertex("V1");
        Vertex V2 = new Vertex("V2");
        Vertex V3 = new Vertex("V3");
        Vertex V5 = new Vertex("V5");
        Vertex V8 = new Vertex("V8");
        Vertex V13 = new Vertex("V13");
        Vertex V21 = new Vertex("V21");
        Vertex V34 = new Vertex("V34");
        Vertex V35 = new Vertex("V35");
        vSet.insert(Arrays.asList(V1, V2, V3, V5, V8, V13, V21, V34));
        Assertions.assertEquals(8, vSet.getNumberOfSets());
        Assertions.assertThrows(IllegalArgumentException.class, () -> vSet.union(V0, V1));
        Assertions.assertTrue(vSet.union(V1, V21));
        Assertions.assertTrue(vSet.areConnected(V1, V21));
        Assertions.assertEquals(7, vSet.getNumberOfSets());
        Assertions.assertFalse(vSet.union(V1, V21));
        Assertions.assertTrue(vSet.areConnected(V1, V21));
        Assertions.assertEquals(7, vSet.getNumberOfSets());
        Assertions.assertTrue(vSet.union(V2, V13));
        Assertions.assertEquals(6, vSet.getNumberOfSets());
        Assertions.assertTrue(vSet.union(V3, V8));
        Assertions.assertEquals(5, vSet.getNumberOfSets());
        Assertions.assertTrue(vSet.union(V1, V8));
        Assertions.assertEquals(4, vSet.getNumberOfSets());
        Assertions.assertTrue(vSet.union(V3, V5));
        Assertions.assertEquals(3, vSet.getNumberOfSets());
        Assertions.assertThrows(IllegalArgumentException.class, () -> vSet.union(V3, V35));
        Assertions.assertEquals(3, vSet.getNumberOfSets());
        Assertions.assertTrue(vSet.union(V3, V34));
        Assertions.assertEquals(2, vSet.getNumberOfSets());
    }

    @Test
    void testAreConnected() {
        DisjointSet<Integer> set = new DisjointSet<>();
        Assertions.assertEquals(0, set.getNumberOfSets());
        List<Integer> items = Arrays.asList(1, 2, 3, 5, 8, 13, 21, 34);
        set.insert(items);
        Assertions.assertEquals(8, set.getNumberOfSets());
        for (int index = 0; index < items.size(); ++index) {
            for (int next = index + 1; next < items.size(); ++next) {
                Assertions.assertFalse(set.areConnected(items.get(index), items.get(next)));
            }
        }
        for (int item : items) {
            Assertions.assertTrue(set.areConnected(item, item));
        }
        Assertions.assertTrue(set.union(1, 21));
        Assertions.assertTrue(set.areConnected(1, 21));
        Assertions.assertEquals(7, set.getNumberOfSets());
        Assertions.assertFalse(set.union(1, 21));
        Assertions.assertTrue(set.areConnected(1, 21));
        Assertions.assertEquals(7, set.getNumberOfSets());
        Assertions.assertTrue(set.union(2, 13));
        Assertions.assertEquals(6, set.getNumberOfSets());
        Assertions.assertTrue(set.union(3, 8));
        Assertions.assertEquals(5, set.getNumberOfSets());
        Assertions.assertTrue(set.union(1, 8));
        Assertions.assertEquals(4, set.getNumberOfSets());
        Assertions.assertTrue(set.union(3, 5));
        Assertions.assertEquals(3, set.getNumberOfSets());
        Assertions.assertTrue(set.union(3, 34));
        Assertions.assertEquals(2, set.getNumberOfSets());
        Assertions.assertTrue(set.areConnected(5, 34));
        Assertions.assertTrue(set.areConnected(8, 34));
        Assertions.assertTrue(set.areConnected(1, 34));
        Assertions.assertTrue(set.areConnected(21, 34));
        Assertions.assertFalse(set.areConnected(2, 34));
        Assertions.assertFalse(set.areConnected(13, 5));
        Assertions.assertFalse(set.areConnected(2, 3));
        Assertions.assertTrue(set.areConnected(2, 13));
    }

    @Test
    void testFind() {
        DisjointSet<Vertex> vSet = new DisjointSet<>();
        Assertions.assertEquals(0, vSet.getNumberOfSets());
        Vertex V0 = new Vertex("V0");
        Vertex V1 = new Vertex("V1");
        Vertex V2 = new Vertex("V2");
        Vertex V3 = new Vertex("V3");
        Vertex V5 = new Vertex("V5");
        Vertex V8 = new Vertex("V8");
        Vertex V13 = new Vertex("V13");
        Vertex V21 = new Vertex("V21");
        Vertex V34 = new Vertex("V34");
        Vertex V35 = new Vertex("V35");
        vSet.insert(Arrays.asList(V1, V2, V3, V5, V8, V13, V21, V34));
        Assertions.assertTrue(vSet.union(V1, V21));
        Assertions.assertTrue(vSet.union(V2, V13));
        Assertions.assertTrue(vSet.union(V3, V8));
        Assertions.assertTrue(vSet.union(V1, V8));
        Assertions.assertTrue(vSet.union(V3, V5));
        Assertions.assertTrue(vSet.union(V3, V34));
        Assertions.assertEquals(V1, vSet.find(V5));
        Assertions.assertEquals(V1, vSet.find(V3));
        Assertions.assertEquals(V1, vSet.find(V21));
        Assertions.assertEquals(V1, vSet.find(V8));
        Assertions.assertEquals(V2, vSet.find(V13));
        Assertions.assertEquals(V2, vSet.find(V2));
        Assertions.assertNotEquals(V1, vSet.find(V13));
        Assertions.assertNotEquals(V1, vSet.find(V2));
    }
}