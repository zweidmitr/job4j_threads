package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class Wget implements Runnable {
    private final String url;
    private final int speed;

    public Wget(String url, int speed) {
        this.url = url;
        this.speed = speed;
    }

    @Override
    public void run() {
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("file_xml")) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            long start = System.currentTimeMillis();
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                try {
                    long checkTime = System.currentTimeMillis() - start;
                    fileOutputStream.write(dataBuffer, 0, bytesRead);
                    if (checkTime < (long) speed) {
                        Thread.sleep(speed - checkTime);
                    }
                    start = System.currentTimeMillis();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * чтобы ввести параметры можно использовать:
     * ALT + SHIFT + F10, Вправо, Edit, Enter
     *
     * @param args https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml  1020(speed)
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        for (String str : args) {
            System.out.println("аргумент = " + str);
        }
        String url = args[0];
        int speed = Integer.parseInt(args[1]);
        Thread wget = new Thread(new Wget(url, speed));
        wget.start();
        wget.join();
        System.out.println("done");
    }
}
