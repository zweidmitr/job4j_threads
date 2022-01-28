package ru.job4j.concurrent;

import java.time.LocalTime;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                }
        );
        System.out.println("Thread : "
                + first.getName() + " is " + first.getState());
        first.start();
        Thread second = new Thread(
                () -> {
                }
        );

        second.start();
        System.out.println(LocalTime.now() + " Thread: "
                + second.getName() + " is " + second.getState());

        while (first.getState() != Thread.State.TERMINATED) {
            System.out.println("Thread: "
                    + first.getState() + " is " + first.getState());
        }
        System.out.println(Thread.currentThread().getName());
        System.out.println("Thread: "
                + first.getName() + " is " + first.getState());
        System.out.println(LocalTime.now() + " Thread: "
                + second.getName() + " is " + second.getState());

    }
}
