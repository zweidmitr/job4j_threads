package ru.job4j.pool.pools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SimpleClass extends RecursiveTask<String> {
    @Override
    protected String compute() {
        System.out.println("I am work in Thread: " + Thread.currentThread().getName());
        return "I am just innocent simple class";
    }

    public static void main(String[] args) {
        SimpleClass simpleClass = new SimpleClass();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        System.out.println(forkJoinPool.invoke(simpleClass));
    }
}
