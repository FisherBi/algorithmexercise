package algorithms.c3binarytree.traversingbinarytree;

import java.util.Stack;

/**
 * Created by fisbii on 17-8-14.
 */
public class BinaryTreeTraversing {

    public void preOrderRecur(Node head){
        if(head == null){
            return;
        }
        System.out.println(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    public void inOrderRecur(Node head){
        if(head == null){
            return;
        }
        inOrderRecur(head.left);
        System.out.println(head.value + " ");
        inOrderRecur(head.right);
    }

    public void postOrderRecur(Node head){
        if(head == null){
            return;
        }
        postOrderRecur(head.left);
        postOrderRecur(head.right);
        System.out.println(head.value + " ");
    }

    public void preOrderUnRecur(Node head){
        if(head != null){
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            while (!stack.isEmpty()){
                head = stack.pop();
                System.out.println(head.value + " ");
                if(head.right != null){
                    stack.push(head.right);
                }
                if(head.left != null){
                    stack.push(head.left);
                }
            }
            System.out.println();
        }
    }

    public void inOrderUnRecur(Node head){
        if(head != null){
            Stack<Node> stack = new Stack<>();
            while(head != null || !stack.isEmpty()){
                if(head != null){
                    stack.push(head);
                    head = head.left;
                }else {
                    head = stack.pop();
                    System.out.println(head.value + " ");
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    public void postOrderUnRecur1(Node head){
        if(head != null){
            Stack<Node> stack1 = new Stack<>();
            Stack<Node> stack2 = new Stack<>();
            stack1.push(head);
            while(!stack1.isEmpty()){
                head = stack1.pop();
                stack2.push(head);
                if(head.left != null){
                    stack1.push(head.left);
                }
                if(head.right != null){
                    stack1.push(head.right);
                }
            }
            while(!stack2.isEmpty()){
                System.out.println(stack2.pop().value + " ");
            }
        }
        System.out.println();
    }

    public void postOrderUnRecur2(Node head){
        if(head != null){
            Stack<Node> stack = new Stack<>();
            stack.push(head);
            Node c = null;
            Node h = head;
            while(!stack.isEmpty()){
                c = stack.peek();
                if(c.left != null && h != c.left && h != c.right){
                    stack.push(c.left);
                } else if(c.right != null && h != c.right){
                    stack.push(c.right);
                } else{
                    System.out.println(stack.pop().value + " ");
                    h = c;
                }
            }
        }
    }
}
