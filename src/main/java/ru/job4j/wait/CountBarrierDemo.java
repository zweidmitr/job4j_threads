package ru.job4j.wait;

import javax.swing.plaf.TableHeaderUI;

public class CountBarrierDemo {
    public static void main(String[] args) throws InterruptedException {
        CountBarrier countBarrier = new CountBarrier(15);
        Thread master = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " started");
                    countBarrier.await();
                    System.out.println("sleeeep, tssssss");
                },
                "MASTER"
        );
        master.start();
    }
}

