package StringManipulation;

import java.util.*;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 11/11/2016
 */

/*
Given an array of strings (all lowercase letters), the task is to group them in such a way that
 all strings in a group are shifted versions of each other.

 Two string S and T are called shifted if,

S.length = T.length
and
S[i] = T[i] + K for
1 <= i <= S.length  for a constant integer K

 */
public class GroupShiftedOnes {

    // The idea is to calculate a hash string of differences of characters for each string.
    // The hash is calculated by calculating the difference of each character with the first character of the string
    // Two shifted strings would have same hash string value

    private List<List<String>> groupShiftedOnes(List<String> strings) {
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strings) {
            int offset = str.charAt(0) - 'a';
            String key = "";
            for (int i = 0; i < str.length(); i++) {
                char c = (char) (str.charAt(i) - offset);
                if (c < 'a') {
                    c += 26;
                }
                key += c;
            }

            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);
        }
        for (String key : map.keySet()) {
            List<String> list = map.get(key);
            Collections.sort(list);
            result.add(list);
        }
        return result;
    }

    public static void main(String[] args) {
        String strs[] = {"acd", "dfg", "wyz", "yab", "mop", "bdfh", "a", "x", "moqs"};
        List<String> strings = new ArrayList<>();
        Collections.addAll(strings, strs);

        GroupShiftedOnes groupMaker = new GroupShiftedOnes();
        List<List<String>> groups = groupMaker.groupShiftedOnes(strings);
        System.out.println(groups);
    }
}
