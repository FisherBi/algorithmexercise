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
}
