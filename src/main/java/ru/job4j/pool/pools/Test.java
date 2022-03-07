package ru.job4j.pool.pools;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        int size = 200;
        int[] array = new int[size];
        Random rand = new Random();
        for (int index = 0; index < array.length; index++) {
            array[index] = rand.nextInt(size);
        }
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%03d ", array[i]);
            count++;
            if (count == 10) {
                System.out.println();
                count = 0;
            }
        }
    }
}
