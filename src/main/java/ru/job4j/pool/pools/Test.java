package ru.job4j.pool.pools;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        int size = 150;
        int[] array = new int[size];
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(size);
        }
        for (int elem : array) {
            System.out.print(elem + " ");
        }
    }
}
