package ru.job4j.coccurrent;

public class Wget {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                    try {
                        for(int index = 0; index <= 100; index++) {
                            System.out.println("\rLoading : " + index + "%");
                            Thread.sleep(1000);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        first.start();


    }
}
