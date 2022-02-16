package ru.job4j.pool.pools;

import java.util.Random;

public class Test {
    public static void main(String[] args) {
        int size = 10;
        int[] array = new int[size];
        Random rand = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i] = rand.nextInt(size);
        }
        int[] newSortArray = MergeSort.sort(array);
        for (int index = 0; index < array.length; index++) {
            System.out.println("было: "
                    + array[index]
                    + " стало: "
                    + newSortArray[index]
                    + "   index [" + index + "]");
        }
    }
}
