package algorithms.c1stackandqueue.getminstack;

import java.util.Stack;

/**
 * Created by FisherBi on 2017/8/13.
 */
public class MyStack1 {
    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public MyStack1(){
        stackData = new Stack<>();
        stackMin = new Stack<>();
    }

    public void push(int newNum){
        if(stackMin.isEmpty()){
            stackMin.push(newNum);
        }else if(newNum <= getMin()){
            stackMin.push(newNum);
        }
        this.stackData.push(newNum);
    }

    public int pop(){
        if(stackMin.isEmpty()){
            throw new RuntimeException("Your statck is Empty!");
        }
        int num = stackData.pop();
        if(num == this.getMin()){
            stackMin.pop();
        }
        return num;
    }

    private int getMin() {
        if(stackMin.isEmpty()){
            throw new RuntimeException("Your statck is Empty!");
        }
        return stackMin.peek();
    }
}
