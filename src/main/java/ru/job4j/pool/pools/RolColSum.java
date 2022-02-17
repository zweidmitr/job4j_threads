package ru.job4j.pool.pools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * только на квадратной матрице
 */
public class RolColSum {
    public static void main(String[] args) {
        int[][] array = new int[][]{
                {1, 1, 1},
                {2, 2, 2},
                {1, 2, 3}};
        for (int[] ar : array) {
            for (int num : ar) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
        System.out.println("================");
        Sums[] sums = sum(array);
        System.out.println(sums[0].rowSum + " - " + sums[0].colSum);
        System.out.println("================");
        Sums[] sumsAsync = sum(array);
        System.out.println(sumsAsync[0].rowSum + " - " + sumsAsync[0].colSum);
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] result = new Sums[matrix.length];

        for (int row = 0; row < matrix.length; row++) {
            int rowSum = 0;
            int colSum = 0;
            Sums ups = new Sums();
            for (int col = 0; col < matrix[row].length; col++) {
                rowSum += matrix[row][col];
                colSum += matrix[col][row];
            }
            ups.setRowSum(rowSum);
            ups.setColSum(colSum);
            result[row] = ups;
        }
        return result;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] result = new Sums[matrix.length];
        for (int row = 0; row < matrix.length; row++) {
            result[row] = getTask(matrix, row).get();
        }
        return result;
    }

    public static CompletableFuture<Sums> getTask(int[][] matrix, int row) {
        return CompletableFuture.supplyAsync(() -> {
                    int rowSum = 0;
                    int colSum = 0;
                    Sums ups = new Sums();
                    for (int col = 0; col < matrix[row].length; col++) {
                        rowSum += matrix[row][col];
                        colSum += matrix[col][row];
                    }
                    ups.setRowSum(rowSum);
                    ups.setColSum(colSum);
                    return ups;
                }
        );
    }

    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }
}
