package dj.learning.section5.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7,8,9,6};
        int searchEle = 6;
        int start = 0;
        int end = arr.length-1;
        SearchTask task = new SearchTask(arr, start, end, searchEle);
        ForkJoinPool pool = ForkJoinPool.commonPool();
        int result = pool.invoke(task);
        System.out.printf("%d found %d times", searchEle, result);
    }
}

class SearchTask extends RecursiveTask<Integer> {
    int arr [];
    int start, end;
    int searchEle;

    public SearchTask(int[] arr, int start, int end, int searchEle) {
        this.arr = arr;
        this.start = start;
        this.end = end;
        this.searchEle = searchEle;
    }

    @Override
    protected Integer compute() {
        System.out.println(Thread.currentThread());
        int size = end-start;
        if (size > 3) {
            int mid = (end+start)/2;
            SearchTask t1 = new SearchTask(arr, start, mid, searchEle);
            SearchTask t2 = new SearchTask(arr, mid+1, end, searchEle);
            t1.fork();
            t2.fork();
            return t1.join()+t2.join();
        } else {
            return processSearch();
        }
    }

    private Integer processSearch() {
        int count = 0;
        for (int i=start; i <= end; i++) {
            if (arr[i] == searchEle) {
                count++;
            }
        }
        return count;
    }
}