package ru.job4j.pool.pools;

import java.util.Date;

public class HabrTest {
    public static void main(String[] args) throws InterruptedException {
        int[] array = getInitArray(10_000);
        System.out.println(new Date());
        long sum = 0;
        for (int i = 0; i < array.length; i++) {
            Thread.sleep(1);
            sum += array[i];
        }
        System.out.println(sum);
        System.out.println(new Date());
    }

    private static int[] getInitArray(int capacity) {
        int[] array = new int[capacity];
        for (int i = 0; i < capacity; i++) {
            array[i] = i;
        }
        return array;
    }
}
