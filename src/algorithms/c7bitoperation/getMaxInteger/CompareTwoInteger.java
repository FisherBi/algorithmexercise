package algorithms.c7bitoperation.getMaxInteger;

/**
 * Created by fisbii on 17-8-17.
 */
public class CompareTwoInteger {

    public static int sign(int n){
        return flip((n >> 31) & 1);
    }

    private static int flip(int n) {
        return n ^ 1;
    }

    public static int getMax1(int a, int b){
        int c = a - b;
        int scA = sign(c);
        int scB = flip(scA);
        return a*scA + b*scB;
    }

    public static int getMax2(int a, int b){
        int c = a - b;
        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(c);
        int difSab = sa ^ sb;
        int sameSab = flip(difSab);
        int returnA = difSab * sa + sameSab * sc;
        int returnB = flip(returnA);
        return a * returnA + b * returnB;
    }

    public static void main(String args[]){
        System.out.println(getMax1(1,2));
    }
}
