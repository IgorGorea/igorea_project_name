package unitTests;

public class Calculator {

    public static int sumOfTwoNumbers(int x, int y) {
        int sumTwo;
        return sumTwo = x + y;
    }
    public static int subtractionOfTwoNumbers(int x, int y) {
        int substrTwo;
        return substrTwo = x - y;
    }
    public static int multiplicationOfTwoNumbers(int x, int y) {
        int multipTwo;
        return multipTwo = x * y;
    }
    public static int divisionOfTwoNumbers(int x, int y) {
        int divisTwo;
        return divisTwo = x / y;
    }
    public static boolean isOdd(int x){
        if(x % 2 != 0)
            return true;
        return false;
    }
}
