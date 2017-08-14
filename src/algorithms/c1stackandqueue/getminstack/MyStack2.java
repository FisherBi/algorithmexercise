package algorithms.c1stackandqueue.getminstack;

import java.util.Stack;

/**
 * Created by fisbii on 17-8-14.
 */
public class MyStack2 {

    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    public MyStack2(){
        stackData = new Stack<>();
        stackMin = new Stack<>();
    }

    public void push(int newNum){
        if(stackMin.isEmpty()){
            stackMin.push(newNum);
        }else if(newNum <= getMin()){
            stackMin.push(newNum);
        }else{
            stackMin.push(stackMin.peek());
        }
        this.stackData.push(newNum);
    }

    public int pop(){
        if(stackMin.isEmpty()){
            throw new RuntimeException("Your statck is Empty!");
        }
        stackMin.pop();
        return stackData.pop();
    }

    private int getMin() {
        if(stackMin.isEmpty()){
            throw new RuntimeException("Your statck is Empty!");
        }
        return stackMin.peek();
    }
}
