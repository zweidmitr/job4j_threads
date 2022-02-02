package ru.job4j.ref;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * в кеш нужно добавлять копию объекта и возвращать копию
 * Методы add и findById работают с копиями объекта User.
 * смотреть на метод User.findById(int id) - возвращает копию объекта
 */
public class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName()));
    }

    public User findById(int id) {
        return User.of(users.get(id).getName());
    }

    public List<User> findAll() {
        ArrayList<User> listik = new ArrayList();
        for (User us : users.values()) {
            listik.add(User.of(us.getName()));
        }
        return listik;
    }
}
