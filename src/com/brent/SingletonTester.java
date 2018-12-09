package com.brent;

import sun.jvm.hotspot.utilities.Assert;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SingletonTester {
    public static void test(SingletonFactory factory) throws InterruptedException {
        final int NUM_THREADS = 10;
        Set<Thread> threads = new HashSet<>();
        Set<Object> singletons = ConcurrentHashMap.newKeySet();
        for (int i = 0; i < NUM_THREADS; i++) {
            Thread thread = new Thread(() -> {
                Object singleton = factory.getSingleton(Sunscreen.class);
                singletons.add(singleton);
            });
            threads.add(thread);
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }

        for (Object instance : singletons) {
            System.out.println(instance);
        }

        Assert.that(singletons.size() == 1, "singleton factory not threadsafe");
    }
}
