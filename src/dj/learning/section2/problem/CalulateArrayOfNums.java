package dj.learning.section2.problem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CalulateArrayOfNums {
    public static void main(String[] args) throws Exception {
        int[] nums = {10,20,30,40,50,60,70,80,90};
        long startTime = System.currentTimeMillis();
        System.out.println("Result is : "+calculateNums(nums));
        System.out.println("Time taken : "+ (System.currentTimeMillis()- startTime));
    }

    private static int calculateNums(int[] nums) throws Exception {
        ExecutorService ex = Executors.newFixedThreadPool((nums.length/3)+1);
        List<Future<Integer>> futureList = new ArrayList<>();
        for (int i=0; i<=nums.length/3;i++){
            int current = i;
            futureList.add(ex.submit(()->{
                return calculate(nums, (current*3), (current*3)+2);
            }));
        }
        int result=0;
        for (Future<Integer> future: futureList) {
            result+=future.get();
        }
       return (int)result;
    }

    private static Integer calculate(int[] nums, int start, int end) throws Exception {
        int result = 0;
        if (end >= nums.length){
            for (int i=start; i<nums.length; i++){
                result+=nums[i];
            }
        } else{
            for (int i=start; i<=end; i++){
                result+=nums[i];
            }
        }
        Thread.sleep(0);
        return (Integer) result;
    }
}
