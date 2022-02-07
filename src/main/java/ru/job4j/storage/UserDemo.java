package ru.job4j.storage;

public class UserDemo {
    public static void main(String[] args) {
        UserStore stoge = new UserStore();
        User small = new User(1, 100);
        User big = new User(2, 200);
        System.out.println("user add? - " + stoge.add(small));
        stoge.add(big);
        System.out.println("transfer is ok? - "
                + stoge.transfer(1, 2, 50));
        System.out.println(small);
        System.out.println(big);
        System.out.println("is user updated? - " + stoge.update(small));
        System.out.println("is user deleted? - "
                + stoge.delete(small));
    }
}
