package SystemDesigning;

/*
 * Author: Honey Kakkar
 * Created on: December 26, 2016
 * Package: SystemDesigning
 * Project: Coding Practice in JAVA
 */

/*
Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and

You may assume that calls are being made to the system in chronological order
(ie, the timestamp is monotonically increasing).

You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.
 */
class HitCounter {
    private final int[] times;
    private final int[] hits;
    /** Initialize your data structure here. */

    public HitCounter() {
        times = new int[300];
        hits = new int[300];
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        int index = timestamp % 300;
        if (times[index] != timestamp) {
            times[index] = timestamp;
            hits[index] = 1;
        } else {
            hits[index]++;
        }
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        int total = 0;
        for (int i = 0; i < 300; i++) {
            if (timestamp - times[i] < 300) {
                total += hits[i];
            }
        }
        return total;
    }
}
