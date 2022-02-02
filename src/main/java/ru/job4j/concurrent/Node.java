package ru.job4j.concurrent;

/**
 * Immutable class должен содержать:
 * 1. Все поля отмечены final.
 * <p>
 * 2. Состояние объекта не изменяется после создания объекта.
 * Т.е отсутствуют сеттеры, меняющие состояние объекта после создания.
 * Инициализация final полей происходит сразу, а лучше в конструкторе класса.
 * <p>
 * 3.  ключевое слово final перед названием класса
 */
public final class Node<T> {
    private final Node<T> next;
    private final T value;

    public Node(Node<T> next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public T getValue() {
        return this.value;
    }
}
