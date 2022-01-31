package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        String[] process = {"-", "\\", "|", "/"};
        int temp = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("\r Loading: " + process[temp++]);
            if (temp > 3) {
                temp = 0;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
    }
}