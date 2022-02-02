package ru.job4j.concurrent;

public class Count {
    public int value;

    public synchronized void increment() {
        value++;
    }

    public int getValue() {
        return value;
    }
}
