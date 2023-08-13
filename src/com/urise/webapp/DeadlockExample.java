package com.urise.webapp;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static java.lang.Thread.sleep;

public class DeadlockExample {
    private final Lock rightLock = new ReentrantLock();
    private final Lock leftLock = new ReentrantLock();

    public static void main(String[] args) {
        DeadlockExample de = new DeadlockExample();
        new Thread(de::operationRight, "rightThread").start();
        new Thread(de::operationLeft, "leftThread").start();
    }

    public void operationRight(){
        rightLock.lock();
        System.out.println("rightLock acquired, waiting to acquire leftLock");
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        leftLock.lock();
        System.out.println("leftLock acquired");
        leftLock.unlock();
        rightLock.unlock();

    }

    public void operationLeft(){
        leftLock.lock();
        System.out.println("leftLock acquired, waiting to acquire rightLock");
        try {
            sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        rightLock.lock();
        System.out.println("rightLock acquired");
        rightLock.unlock();
        leftLock.unlock();
    }
}
