package BitManipulation;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 11/1/2016
 */

/*Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100),
return 964176192 (represented in binary as 00111001011110000010100101000000).
    */

public class ReverseBits {

    int reverseBits(int num) {
        int NO_OF_BITS = 32;
        System.out.println(Integer.toBinaryString(num));
        int reverse_num = 0;
        int i;
        for (i = 0; i < NO_OF_BITS; i++) {
            if ((num & (1 << i)) != 0)   // checking if the bit is set. If yes, shift it to right to (32 - i)th position
                reverse_num |= 1 << ((NO_OF_BITS - 1) - i);
        }
        System.out.println(Integer.toBinaryString(reverse_num));
        return reverse_num;
    }

    public static void main(String[] args) {
        ReverseBits reverseBits = new ReverseBits();
        System.out.println(reverseBits.reverseBits(43261596));
    }
}
