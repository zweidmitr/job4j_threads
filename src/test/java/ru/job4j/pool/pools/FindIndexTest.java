package ru.job4j.pool.pools;

import org.junit.Test;

import java.util.Random;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FindIndexTest {
    @Test
    public void whenFind() {
        Integer[] array = new Integer[777];
        for (int i = 0; i < array.length; i++) {
            array[i] = i - 100;
        }
        int index = FindIndex.find(array, 566);
        assertThat(index, is(666));
    }

    @Test
    public void whenNotFind() {
        Integer[] array = new Integer[777];
        for (int i = 0; i < array.length; i++) {
            array[i] = i - 100;
        }
        int index = FindIndex.find(array, 999);
        assertThat(index, is(-1));
    }

    @Test
    public void whenFindString() {
        String[] array = new String[777];
        for (int i = 0; i < array.length; i++) {
            array[i] = "strings " + i;
        }
        int index = FindIndex.find(array, "strings 666");
        assertThat(index, is(666));
    }

}