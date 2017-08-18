package algorithms.c9others.randomnumberextension;

/**
 * Created by fisbii on 17-8-17.
 */
public class RandomNumber {
    public int rand1To5(){
        return (int) (Math.random() * 5) + 1;
    }

    public int rand1To7(){
        int num = 0;
        do {
            num = (rand1To5()-1)*5 + rand1To5()-1;
        } while (num > 20);
        return num % 7 + 1;

    }

    public int rand1ToM(int m){
        return (int) (Math.random() * m) + 1;
    }

    public int rand1ToN(int n, int m){
        int[] nMSys = getMSysNum(n-1, m);
        int[] randNum = getRanMSysNumLessN(nMSys, m);
        return getNumFromMSysNum(randNum, m) + 1;
    }

    private int[] getMSysNum(int value, int m) {
        int[] res = new int[32];
        int index = res.length - 1;
        while(value != 0){
            res[index--] = value % m;
            value /= m;
        }
        return res;
    }

    private int[] getRanMSysNumLessN(int[] nMSys, int m) {
        int[] res = new int[nMSys.length];
        int start = 0;
        while (nMSys[start] == 0){
            start++;
        }
        int index = start;
        boolean lastEqual = true;
        while (index != nMSys.length){
            res[index] = rand1ToM(m) - 1;
            if(lastEqual){
                if(res[index] > nMSys[index]){
                    index = start;
                    lastEqual = true;
                    continue;
                }else{
                    lastEqual = res[index] == nMSys[index];
                }
            }
            index++;
        }
        return res;
    }

    private int getNumFromMSysNum(int[] mSysNum, int m) {
        int res = 0;
        for(int i = 0; i != mSysNum.length; i++){
            res = res * m + mSysNum[i];
        }
        return res;
    }

}
