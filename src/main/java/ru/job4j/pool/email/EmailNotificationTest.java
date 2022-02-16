package ru.job4j.pool.email;

import java.util.ArrayList;
import java.util.List;

public class EmailNotificationTest {
    public static void main(String[] args) throws InterruptedException {
        EmailNotification emNo = new EmailNotification();
        List<User> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(new User("test: " + i, "test@"));
        }
        list.forEach(emNo::emailTo);

        Thread.sleep(3000);
        emNo.close();
    }
}
