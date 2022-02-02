package ru.job4j.ref;

import java.util.List;

/**
 * нить first работает с локальным объект user.
 * Изменение этого объекта не влечет изменений в самом кеше.
 */
public class ShareNotSafe {
    public static void main(String[] args) throws InterruptedException {
        UserCache cache = new UserCache();
        User user = User.of("name");
        cache.add(user);
        Thread first = new Thread(
                () -> {
                    user.setName("rename");
                }
        );
        first.start();
        first.join();
        cache.findById(1).setName("rename");
        System.out.println(cache.findById(1).getName());
        System.out.println();
        User userTwo = new User();
        user.setName("norm");
        cache.add(userTwo);
        cache.findAll().forEach(s -> s.setName("bu-ga-ga"));
        cache.findAll().forEach(s -> System.out.println(s.getName()));
    }
}
