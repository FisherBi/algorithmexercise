package algorithms.c7bitoperation.exchangetwointeger;

/**
 * Created by fisbii on 17-8-17.
 */
public class ExchangeTwoInteger {
    public void exchange(int a,int b){
        a = a^b;
        b = a^b;
        a = a^b;
        System.out.println("a=" + a + ",b=" + b);
    }
}
