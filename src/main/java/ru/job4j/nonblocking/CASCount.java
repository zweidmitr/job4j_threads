package ru.job4j.nonblocking;

import net.jcip.annotations.ThreadSafe;

import java.util.concurrent.atomic.AtomicReference;

@ThreadSafe
public class CASCount {
    private final AtomicReference<Integer> count = new AtomicReference<>();

    /**
     * "value" - считываем то, что будем сравнивать (вдруг изменится другой нитью)
     * "value +1" - то что запишем, если ничего не поменялось, повторить шаг выше.
     * <p>
     * Это значит если две нити прочитали одно и тоже значение value,
     * то первый вызов compareAndSet даст true, а второй вызов вернет false.
     * Вторая нить будет заново читать value и выполнять операцию compareAndSet.
     * Обе нити не блокируются, а выполняются параллельно.
     */
    public void increment() {
        int value;
        do {
            value = count.get();
        } while (!count.compareAndSet(value, value + 1));
    }

    public int get() {
        return count.get();
    }
}
