package algorithms.c5string.removekzeros;

/**
 * Created by fisbii on 17-8-24.
 */
public class KZero {
    public static String removeKZeros(String str, int k){
        if(str == null || k < 1){
            return str;
        }
        char[] chas = str.toCharArray();
        int count = 0, start = -1;
        for(int i = 0; i < chas.length; i++){
            if(chas[i] == '0'){
                count++;
                start = start == -1 ? i : start;
            } else{
                if(count == k){
                    while(count-- != 0){
                        chas[start++] = 0;
                    }
                    count = 0;
                    start = -1;
                }
            }
        }
        if(count == k){
            while(count-- != 0)
                chas[start++] = 0;
        }
        return String.valueOf(chas);
    }

    public static void main(String args[]){
        String str = "A00B";
        String a = removeKZeros(str, 2);
        System.out.println(a);
    }
}
