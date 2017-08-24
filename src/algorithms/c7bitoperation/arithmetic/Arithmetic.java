package algorithms.c7bitoperation.arithmetic;

/**
 * Created by fisbii on 17-8-21.
 */
public class Arithmetic {
    public int add(int a, int b){
        int sum = a;
        while(b != 0){
            sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return sum;
    }

    public int minus(int a, int b){
        return add(a, negNum(b));
    }

    private int negNum(int n) {
        return add(~n, 1);
    }

    public int multi(int a,int b){
        int res = 0;
        while(b != 0){
            if((b & 1) != 0){
                res = add(res, a);
            }
            a <<= 1;
            b >>>= 1;
        }
        return res;
    }

    public boolean isNeg(int n){
        return n < 0;
    }

    public int div(int a,int b){
        int x = isNeg(a) ? negNum(a) : a;
        int y = isNeg(b) ? negNum(b) : b;
        int res = 0;
        for(int i = 31; i >= 0; i--){
            if((x >> i) > y){
                x = minus(x, y << i);
                res |= (1 << i);
            }
        }
        return isNeg(a) ^ isNeg(b) ? negNum(res) : res;
    }

    public int divide(int a,int b){
        if(b == 0){
            throw new RuntimeException("divisor is 0");
        }
        if(a == Integer.MIN_VALUE && b == Integer.MIN_VALUE){
            return 1;
        } else if(b == Integer.MIN_VALUE){
            return 0;
        } else if(a == Integer.MIN_VALUE){
            int res = div(add(a, 1), b);
            return add(res, div(minus(a, multi(res,b)),b));
        } else{
            return div(a,b);
        }
    }

}