package ru.job4j.cache;

import org.checkerframework.checker.units.qual.C;
import org.junit.Test;

import static org.junit.Assert.*;

public class CacheTest {
    @Test
    public void whenAddThenTrue() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        assertTrue(cache.add(base));
    }

    @Test
    public void whenAddEqualBaseThenFalse() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        Base baseTwo = new Base(1, 1);
        assertTrue(cache.add(base));
        assertFalse(cache.add(baseTwo));
    }

    @Test
    public void whenUpdateBaseThenTrue() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        cache.add(base);
        base.setName("newName");
        assertTrue(cache.update(base));
    }

    @Test(expected = OptimisticException.class)
    public void whenUpdateBaseThenException() {
        Cache cache = new Cache();
        Base base = new Base(1, 1);
        Base baseTwo = new Base(2, 1);
        cache.add(base);
        cache.update(base);
        base.setName("newName");
        cache.update(base);
    }
}