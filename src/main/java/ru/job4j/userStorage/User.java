package ru.job4j.userStorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

@ThreadSafe
public class User {
    private final int id;
    @GuardedBy("this")
    private int amount;

    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    public int getId() {
        return this.id;
    }

    public synchronized int getAmount() {
        return this.amount;
    }

    public synchronized void plusAmount(int money) {
        this.amount += money;
    }

    public synchronized boolean minusAmount(int money) {
        boolean result = false;
        if (this.amount > money) {
            this.amount -= money;
            result = true;
        }
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", amount=" + amount +
                '}';
    }
}
