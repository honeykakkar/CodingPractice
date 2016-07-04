package Algorithms;

import java.util.*;

/**
 * Author: honey
 * Project: CodingPractice
 * Date created: 7/3/2016
 */
public class ChangeTracker {

    class Pair{
        int first;
        int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        public int getFirst() {
            return first;
        }

        public void setFirst(int first) {
            this.first = first;
        }

        public int getSecond() {
            return second;
        }

        public void setSecond(int second) {
            this.second = second;
        }

        @Override
        public String toString() {
            return "{" + first +
                    ", " + second +
                    '}';
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Pair))
                return false;
            Pair Obj = (Pair) obj;
            return this.first == Obj.first && this.second == Obj.second;
        }
    }

    HashMap<String, ArrayList<Pair>> trackChanges(String original, String modified){
        HashMap<String, ArrayList<Pair>> result = new HashMap<>();
        ArrayList<Pair> newWords = new ArrayList<>();
        ArrayList<Pair> delWords = new ArrayList<>();
        result.put("New", newWords);
        result.put("Delete", delWords);

        HashMap<String, Pair> orgMap = new HashMap<>();
        HashMap<String, Pair> modMap = new HashMap<>();
        int i=0;
        while(i<original.length()){
            while (i<original.length() && original.charAt(i) == ' ')
                ++i;
            int begin, end;
            begin = i;
            while (i<original.length() && original.charAt(i) != ' '){
                ++i;
            }
            end = i;
            orgMap.put(original.substring(begin,end), new Pair(begin, end));
        }

        i=0;
        while(i<modified.length()){
            while (i<modified.length() && modified.charAt(i) == ' ')
                ++i;
            int begin, end;
            begin = i;
            while (i<modified.length() && modified.charAt(i) != ' '){
                ++i;
            }
            end = i;
            modMap.put(modified.substring(begin,end), new Pair(begin, end));
        }

        for (String orgCurr : orgMap.keySet()){
            for (String modCurr : modMap.keySet()){
                if (Objects.equals(modCurr, orgCurr) && modMap.get(modCurr) == orgMap.get(orgCurr))
                    continue;
                if (modMap.get(orgCurr) == null){
                    if(! delWords.contains(orgMap.get(orgCurr)))
                        delWords.add(orgMap.get(orgCurr));
                }

                if (orgMap.get(modCurr) == null){
                    if(! newWords.contains(modMap.get(modCurr)))
                        newWords.add(modMap.get(modCurr));
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ChangeTracker currObj = new ChangeTracker();
        String original = "I am junior professor the school.";
        String modified = "I am a professor in the school.";
        System.out.println("Original caption : " + original + "\nEdited caption : " + modified);
        HashMap<String, ArrayList<Pair>> result = currObj.trackChanges(original, modified);
        System.out.println("\nNew words:");
        for ( Pair newPair : result.get("New"))
            System.out.println(modified.substring(newPair.getFirst(), newPair.getSecond()) +newPair);
        System.out.println("\nDeleted words:");
        for ( Pair newPair : result.get("Delete"))
            System.out.println( original.substring(newPair.getFirst(), newPair.getSecond()) + newPair);
        StringBuilder org = new StringBuilder(original);
        int offset = 0;
        for ( Pair newPair : result.get("New")){
            String newWord = modified.substring(newPair.getFirst() + offset, newPair.getSecond() + offset);
            org.insert(newPair.getFirst() + offset, newWord);
            offset += newWord.length();
        }
        System.out.println(org);
    }
}