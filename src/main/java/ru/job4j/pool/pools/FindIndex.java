package ru.job4j.pool.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * поиск индекса по элементу, в массиве
 */
public class FindIndex extends RecursiveTask<Integer> {
    private final int[] array;
    private final int from;
    private final int to;
    private final int element;

    public FindIndex(int[] array, int from, int to, int index) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.element = index;
    }

    @Override
    protected Integer compute() {
        if ((to - from) < 10) {
            for (int i = 0; i < array.length; i++) {
                if (array[i] == element) {
                    return i;
                }
            }
        }
        int mid = (from + to) / 2;
        /* создаем задачи для сортировки частей */
        FindIndex leftFind = new FindIndex(array, from, mid, element);
        FindIndex rightFind = new FindIndex(array, mid + 1, to, element);
        /* производим деление.
        оно будет происходить, пока в частях не останется по одному элементу */
        /* - метод fork() служит для деления. Это аналогично тому,
        что мы запустили бы рекурсивный метод еще раз */
        leftFind.fork();
        rightFind.fork();
        /* объединяем полученные результаты */
        /* - метод join(). Этот метод как раз дает пулу знать,
        что нужно запустить задачу в отдельном потоке */
        int left = leftFind.join();
        int right = rightFind.join();
        return Math.max(left, right);
    }

    public static Integer find(int[] array, int element) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        /* - метод invoke(). Этот метод служит для запуска выполнения */
        return forkJoinPool.invoke(new FindIndex(array, 0, array.length - 1, element));
    }
}
