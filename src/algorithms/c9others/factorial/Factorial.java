package algorithms.c9others.factorial;

/**
 * Created by fisbii on 17-8-18.
 */
public class Factorial {
    public int zeroNum1(int num){
        if(num < 0){
            return 0;
        }
        int res = 0;
        int cur = 0;
        for(int i = 5; i < num+1; i += 5){
            cur = i;
            while(cur % 5 == 0){
                res++;
                cur /= 5;
            }
        }
        return res;
    }

    public int zeroNum2(int num){
        if(num < 0){
            return 0;
        }
        int res = 0;
        while(num != 0){
            res += num / 5;
            num /= 5;
        }
        return res;
    }
}
