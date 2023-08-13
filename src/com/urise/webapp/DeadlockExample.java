package com.urise.webapp;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class DeadlockExample {
    private static final Lock rightLock = new ReentrantLock();
    private static final Lock leftLock = new ReentrantLock();

    public static void main(String[] args) {
        DeadlockExample de = new DeadlockExample();
        new Thread(() -> de.operation(leftLock, rightLock)).start();
        new Thread(() -> de.operation(rightLock, leftLock)).start();
    }

    private void operation(Lock lock1, Lock lock2) {
        lock1.lock();
        System.out.println("Lock acquired, waiting to acquire second lock");
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lock2.lock();
        System.out.println("acquired");
        lock2.unlock();
        lock1.unlock();
    }
}
