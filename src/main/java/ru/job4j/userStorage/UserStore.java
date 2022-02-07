package ru.job4j.userStorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStore {
    @GuardedBy("this")
    private Map<Integer, User> map = new HashMap();

    public synchronized void add(User user) {
        map.put(user.getId(), user);
    }

    public synchronized void delete(User user) {
        map.remove(user.getId());
    }

    public synchronized void transfer(int fromId, int toId, int amount) {
        User minus = map.get(fromId);
        User plus = map.get(toId);
        minus.minusAmount(amount);
        plus.plusAmount(amount);
    }
}
