package unitTests;

import java.util.ArrayList;

public class IsPrime {

    public static boolean isPrime(int n){
        ArrayList<Integer> arr= new ArrayList<>(n);
        for( int i=2; i <= n/2; i++ ){
            boolean isprime;
            if (n % i == 0) {
                isprime = true;
                for (var j = 2; j <= i; j++) {
                    if (i % j == 0) {
                        isprime = false;
                    }
                }
            }
            if (isprime == true) {
                arr.add(i);
            }
        }

        return false;
    }


}
