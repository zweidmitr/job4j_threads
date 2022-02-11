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
        Base newTemp = memory.computeIfPresent(model.getId(), (key, value) -> {
            if (value.getVersion() != model.getVersion()) {
                throw new OptimisticException("Versions are not equal");
            }
            Base temp = new Base(key, model.getVersion() + 1);
            temp.setName(model.getName());
            return temp;
        });
        return newTemp != null;
    }

    public void delete(Base model) {
        memory.remove(model.getId(), model);
    }

}
