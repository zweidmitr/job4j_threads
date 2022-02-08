package ru.job4j.wait;

public class Barrier {
    private boolean flag = false;

    private final Object monitor = this;

    /**
     * Метод notifyAll переводит все нити из состояния wait в runnable.
     */
    public void on() {
        synchronized (monitor) {
            flag = true;
            monitor.notifyAll();
        }
    }

    public void off() {
        synchronized (monitor) {
            flag = false;
            monitor.notifyAll();
        }
    }

    /**
     * Если флаг = false, то нить засыпает.
     */
    public void check() {
        synchronized (monitor) {
            while (!flag) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
