package algorithms.practice_advanced.advanced_class_02;

import java.util.LinkedList;

/**
 *
 题目描述：


 解法思路：
 　　本题其实是滑动窗口的变形。主体思路为：

 　　１．从第一个元素开始依次向后遍历，同时维护两个窗口（由于要同时操作窗口的头部和尾部，故采用双端队列）：

 　　　　　　最大值窗口（递减），头部永远存最大值

 　　　　　　最小值窗口（递增），头部永远存最小值

 　　２．比较两个窗口的头部元素差值，若差值大于阈值，即可跳出内循环。

 　　３．跳出内循环后，检查头部元素是否过期，若过期，则清除。

 复杂度：
 　　时间复杂度：O(n)，注意虽然是两层循环，但元素只从滑动窗口尾部进，从头部清除，只是顺序扫描了一遍。

 　　空间复杂度：O(n),这里利用两个滑动窗口分别保存最大值和最小值。
 */

public class AllLessNumSubArray {

    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        LinkedList<Integer> qmin = new LinkedList<Integer>();
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        int i = 0;
        int j = 0;
        int res = 0;
        while (i < arr.length) {
            while (j < arr.length) {
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[j]) {
                    qmin.pollLast();
                }
                qmin.addLast(j);
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[j]) {
                    qmax.pollLast();
                }
                qmax.addLast(j);
                if (arr[qmax.getFirst()] - arr[qmin.getFirst()] > num) {
                    break;
                }
                j++;
            }
            if (qmin.peekFirst() == i) {
                qmin.pollFirst();
            }
            if (qmax.peekFirst() == i) {
                qmax.pollFirst();
            }
            res += j - i;
            i++;
        }
        return res;
    }

    // for test
    public static int[] getRandomArray(int len) {
        if (len < 0) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = getRandomArray(30);
        int num = 5;
        printArray(arr);
        System.out.println(getNum(arr, num));

    }

    // basic code
    public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums==null||k<1||nums.length<k)
            return new int[0];
        int len=nums.length;
        int[] res=new int[len-k+1];
        LinkedList<Integer> queue=new LinkedList<Integer>();
        int index=0;
        for(int i=0;i<len;i++){
            int cur=nums[i];
            while(!queue.isEmpty()&&nums[queue.peekLast()]<=cur){
                queue.pollLast();
            }
            queue.addLast(i);
            if(i-k==queue.peekFirst())
                queue.pollFirst();
            if(i>=k-1){
                res[index++]=nums[queue.peekFirst()];
            }
        }
        return res;
    }
}
