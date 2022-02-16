package ru.job4j.pool.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * поиск индекса по элементу, в массиве
 */
public class FindIndex<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final int from;
    private final int to;
    private final T element;

    public FindIndex(T[] array, int from, int to, T index) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.element = index;
    }

    @Override
    protected Integer compute() {
        if ((to - from) < 10) {
            return find();
        }
        int mid = (from + to) / 2;
        FindIndex<T> leftFind = new FindIndex<>(array, from, mid, element);
        FindIndex<T> rightFind = new FindIndex<>(array, mid + 1, to, element);
        leftFind.fork();
        rightFind.fork();
        int left = leftFind.join();
        int right = rightFind.join();
        return Math.max(left, right);
    }

    private int find() {
        int result = -1;
        for (int i = from; i <= to; i++) {
            if (element.equals(array[i])) {
                result = i;
                break;
            }
        }
        return result;
    }

    public static <T> Integer find(T[] array, T element) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new FindIndex<>(array, 0, array.length - 1, element));
    }
}
