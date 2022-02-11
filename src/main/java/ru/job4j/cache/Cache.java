package ru.job4j.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    /*
     * Метод putIfAbsent выполняет методы сравнения и вставки, только делает это атомарно.
     */
    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        Base stored = memory.get(model.getId());
        if (stored.getVersion() != model.getVersion()) {
            throw new OptimisticException("Versions are not equal");
        }
        memory.computeIfPresent(model.getId(), (key, value) -> {
            Base temp = new Base(model.getId(), model.getVersion() + 1);
            temp.setName(model.getName());
            return temp;
        });
        return true;
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }

}
