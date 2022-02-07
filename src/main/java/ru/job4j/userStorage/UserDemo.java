package ru.job4j.userStorage;

public class UserDemo {
    public static void main(String[] args) {
        UserStore stoge = new UserStore();
        User small = new User(1, 100);
        User big = new User(2, 200);
        stoge.add(small);
        stoge.add(big);
        stoge.transfer(1, 2, 50);
        System.out.println(small);
        System.out.println(big);
    }
}
