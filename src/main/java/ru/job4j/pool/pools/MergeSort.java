package ru.job4j.pool.pools;

/**
 * реализуем рекурсивную сортировку слиянием (merge sort), которая используется в Java.
 * Сортировка является устойчивой, т.е. не изменяет исходный порядок элементов,
 * а возвращает новый отсортированный массив, поэтому создаются новые массивы в коде,
 * а не перестановка элементов в текущем.
 */
public class MergeSort {
    public static int[] sort(int[] array) {
        return sort(array, 0, array.length - 1);
    }

    private static int[] sort(int[] array, int from, int to) {

        if (from == to) {
            return new int[]{array[from]};
        }

        int mid = (from + to) / 2;
        return merge(
                sort(array, from, mid),
                sort(array, mid + 1, to)
        );
    }

    public static int[] merge(int[] left, int[] right) {
        int li = 0;
        int ri = 0;
        int resI = 0;
        int[] result = new int[left.length + right.length];
        while (resI != result.length) {
            if (li == left.length) {
                result[resI++] = right[ri++];
            } else if (ri == right.length) {
                result[resI++] = left[li++];
            } else if (left[li] <= right[ri]) {
                result[resI++] = left[li++];
            } else {
                result[resI++] = right[ri++];
            }
        }
        return result;
    }
}
