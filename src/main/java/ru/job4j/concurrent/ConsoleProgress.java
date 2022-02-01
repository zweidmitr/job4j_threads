package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        String[] process = {"-", "\\", "|", "/"};
        int temp = 0;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.print("\rLoading: " + process[temp++]);
                Thread.sleep(250);

                if (temp > process.length - 1) {
                    temp = 0;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(7000);
        progress.interrupt();
    }
}