package Mathematics;

/**
 * Author: honey
 * Project: Coding Practice in JAVA
 * Date created: 11/22/2016
 */
public class BaseConverter {

    public String toString(int n, int base) {
        if (n == 0)
            return "0";

        StringBuilder result = new StringBuilder();
        char mod;
        while (n > 0) {
            mod = toChar(n % base);
            n = n / base;
            result.append(mod);
        }
        return result.reverse().toString();
    }

    public int parseInt(String s, int base) {
        int n = 0;
        for (int i = 0; i < s.length(); i++)
            n = base * n + toInt(s.charAt(i));
        return n;
    }

    public int toInt(char c) {
        if (c < '0' || c > 'Z')
            throw new IllegalArgumentException("invalid char");

        if ((c >= '0') && (c <= '9'))
            return c - '0';

        return c - 'A' + 10;
    }

    public char toChar(int i) {
        if (i < 0 || i > 36)
            throw new IllegalArgumentException("invalid digit");
        if (i < 10)
            return (char) ('0' + i);

        return (char) ('A' + i - 10);
    }

    public static void main(String[] args) {

        BaseConverter baseConverter = new BaseConverter();
        int number = 99;
        int desiredBase = 7;
        String result = baseConverter.toString(number, desiredBase);
        System.out.format("%d (in base 10) is %s in base %d.%n", number, result, desiredBase);

        int givenBase = 7;
        number = baseConverter.parseInt(result, desiredBase);
        System.out.format("%s (in base %d) is %s in base 10.%n", result, givenBase, number);

        number = 300;
        desiredBase = 16;
        result = baseConverter.toString(number, desiredBase);
        System.out.format("%d (in base 10) is %s in base %d.%n", number, result, desiredBase);

        givenBase = 16;
        number = baseConverter.parseInt(result, desiredBase);
        System.out.format("%s (in base %d) is %s in base 10.%n", result, givenBase, number);

        number = -1;
        desiredBase = 16;
        result = baseConverter.toString(number, desiredBase);
        System.out.format("%d (in base 10) is %s in base %d.%n", number, result, desiredBase);
    }
}