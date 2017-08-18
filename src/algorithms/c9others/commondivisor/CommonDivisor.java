package algorithms.c9others.commondivisor;

/**
 * Created by fisbii on 17-8-18.
 */
public class CommonDivisor {
    public int gcd(int m,int n){
        return m == 0 ? m : gcd(n, m%n);
    }
}
