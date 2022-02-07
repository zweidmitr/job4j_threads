package ru.job4j.io;

import org.junit.Test;

import static org.hamcrest.Matchers.is;

import static org.junit.Assert.*;

public class CountTest {

    private class ThreadCount extends Thread {
        private final Count count;

        private ThreadCount(final Count count) {
            this.count = count;
        }

        @Override
        public void run() {
            this.count.increment();
        }
    }

    @Test
    public void whenExecuteTwoThreadThen2() throws InterruptedException {
        /* Create count*/
        final Count count = new Count();
        /* Create Thread */
        Thread first = new ThreadCount(count);
        Thread second = new ThreadCount(count);
        /* Start Thread */
        first.start();
        second.start();
        /* main waiting */
        first.join();
        second.join();
        /* check result */
        assertThat(count.getValue(), is(2));
    }
}