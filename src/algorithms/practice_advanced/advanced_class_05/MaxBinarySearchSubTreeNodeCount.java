package algorithms.practice_advanced.advanced_class_05;

public class MaxBinarySearchSubTreeNodeCount {
    public static class Node{
        int data;
        Node left;
        Node right;
        public Node(int data) {
            this.data = data;
        }
    }

    public static class ReturnData {
        int size;
        Node head;
        int max;
        int min;
        public ReturnData(int size, Node head, int max, int min) {
            this.size = size;
            this.head = head;
            this.max = max;
            this.min = min;
        }
    }

    public static ReturnData process(Node root) {
        if (root == null) {
            return new ReturnData(0, null, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        ReturnData leftInfo = process(root.left);
        ReturnData rightInfo = process(root.right);

        //case 1
        int leftSize = leftInfo.size;
        //case 2
        int rightSize = rightInfo.size;
        int selfSize = 0;
        if (leftInfo.head == root.left && rightInfo.head == root.right
                && leftInfo.max < root.data && rightInfo.min > root.data) {
            //case 3
            selfSize = leftInfo.size + rightInfo.size + 1;
        }
        int maxSize = Math.max(Math.max(leftSize, rightSize), selfSize);
        Node maxHead = leftSize > rightSize ? leftInfo.head :
                selfSize > rightSize ? root : rightInfo.head;

        return new ReturnData(maxSize, maxHead,
                Math.max(Math.max(leftInfo.max, rightInfo.max), root.data),
                Math.min(Math.min(leftInfo.min, rightInfo.min), root.data));
    }

    public static void main(String[] args) {
        Node root = new Node(0);
        root.left = new Node(5);
        root.right = new Node(1);
        root.left.left = new Node(3);
        root.left.left.left = new Node(2);
        root.left.left.right = new Node(4);
        System.out.println(process(root).size);//4
    }
}
