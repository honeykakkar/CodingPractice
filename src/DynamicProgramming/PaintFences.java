package DynamicProgramming;

/*
 * Author: Honey Kakkar
 * Created on: December 22, 2016
 * Package: DynamicProgramming
 * Project: Coding Practice in JAVA
 */

/*
There is a fence with n posts, each post can be painted with one of the k colors.
You have to paint all the posts such that no more than two adjacent fence posts have the same color.
Return the total number of ways you can paint the fence.

Assuming there are 3 posts, if the first one and the second one has the same color,
 then the third one has k-1 options. The first and second together has k options.

If the first and the second do not have same color, the total is k * (k-1),
then the third one has k options. Therefore, f(3) = (k-1)*k + k*(k-1)*k*/

public class PaintFences {

    private int numWays(int n, int k) {
        if (n == 0)
            return 0;
        if (n == 1)
            return k;
        int diffColorCounts = k * (k - 1);
        int sameColorCounts = k;

        for (int i = 2; i < n; i++) {
            int temp = diffColorCounts;
            diffColorCounts = (diffColorCounts + sameColorCounts) * (k - 1);
            sameColorCounts = temp;
        }
        return diffColorCounts + sameColorCounts;
    }

    public static void main(String[] args) {
        PaintFences painter = new PaintFences();
        System.out.println(painter.numWays(3,5));
    }
}
