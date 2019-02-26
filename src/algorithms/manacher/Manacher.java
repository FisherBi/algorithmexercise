package algorithms.manacher;

public class Manacher {
    public static char[] manacherString(String str){
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static int maxLcpsLength(String str) {
        if (str == null || str.length() == 0) {
            return 0;
        }
        char[] charArr = manacherString(str);
        int[] pArr = new int[charArr.length];
        int index = -1;
        int pR = -1;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i != charArr.length; i++) {
            pArr[i] = pR > i ? Math.min(pArr[2 * index - i], pR - i) : 1;
            while (i + pArr[i] < charArr.length && i - pArr[i] > -1) {
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]])
                    pArr[i]++;
                else {
                    break;
                }
            }
            if (i + pArr[i] > pR) {
                pR = i + pArr[i];
                index = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    public static char[] manacherString1(String str){
        char[] s = str.toCharArray();
        char[] t = new char[s.length * 2 + 1];
        int index = 0;
        for(int i = 0; i < t.length; i++){
            t[i] = (i & 1) == 0 ? '#' : s[index++];
        }
        return t;
    }

    public int maxLcpsLength1(String str){
        char[] charArr = manacherString1(str);
        int[] pArr = new int[charArr.length];
        int index = -1;
        int pR = -1;
        int max = Integer.MAX_VALUE;
        for(int i = 0; i < charArr.length; i++){
            pArr[i] = i < pR ? Math.min(pArr[2*index-i], pR-i) : 1;
            while(i+pArr[i] < charArr.length && i-pArr[i] > -1){
                if(charArr[i+pArr[i]] == charArr[i-pArr[i]]){
                    pArr[i]++;
                }else{
                    break;
                }
            }
            if(pArr[i]+i > pR){
                pR = pArr[i]+i;
                index = i;
            }
            max = Math.max(max, pArr[i]);
        }
        return max - 1;
    }

    public static void main(String[] args) {
        String str1 = "abc1234321ab";
        System.out.println(maxLcpsLength(str1));
    }

}
