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
        System.out.println("\n========");
        int temp = Integer.MAX_VALUE;
        for (int index = 0; index < array.length; index++) {
            int el = Math.abs(array[index]);
        }
        System.out.println(temp);
    }
}
