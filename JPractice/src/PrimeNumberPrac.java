import java.util.*;

public class PrimeNumberPrac {
    public static void main(String[] args) {
        int range = 300;



        int r = estimateSize(range);
        System.out.println("estimateSize : " + r);




        int[] primes = primeNumbers(range);
        System.out.println("Prime Numbers are: " + primes.length);
        for (int i : primes){
            System.out.print(i + " ");
        }



    }

    public static int[] primeNumbers(int range){
        int r = estimateSize(range);
        int[] prime = new int[r];
        int c = 0;
        for(int i = 2; i <= range; i++){
            if(isPrime(i)){
                prime[c] = i;
                c++;
            }
        }

        return Arrays.copyOf(prime,c);
    }

    public static int estimateSize(int range){
        if(range < 1 ) return 0;
        if(range >= 2){

            Double n = range / Math.log(range);
            n =  Math.ceil(n * 1.2); //add the safe size
            int i = n.intValue();
            return i;
        }

        return 0;
    }


    public static Boolean isPrime(int n){
        if (n<=1){
            return false;
        }
        if(n==2 || n==3){
            return true;
        }

        for(int i= 2; i<= Math.sqrt(n); i++){
            if(n%i==0){
                return false;
            }
        }

        return true;
    }
}