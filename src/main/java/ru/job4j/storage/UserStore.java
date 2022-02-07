package ru.job4j.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

@ThreadSafe
public class UserStore {
    @GuardedBy("this")
    private Map<Integer, User> map = new HashMap();

    public synchronized boolean add(User user) {
        return map.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
        return map.replace(user.getId(), user) != null;
    }

    public synchronized boolean delete(User user) {
        return map.remove(user.getId(), user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        User minus = map.get(fromId);
        User plus = map.get(toId);
        if (minus != null && plus != null && minus.getAmount() > amount) {
            minus.setAmount(minus.getAmount() - amount);
            plus.setAmount(plus.getAmount() + amount);
            result = true;
        }
        return result;
    }
}
