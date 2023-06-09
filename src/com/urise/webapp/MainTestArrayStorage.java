package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.*;

/**
 * Test for your com.urise.webapp.storage.ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private static final Storage ARRAY_STORAGE2 = new ArrayStorage();
    private static final Storage ARRAY_STORAGE1 = new SortedArrayStorage();
    private static final Storage ARRAY_STORAGE0 = new ListStorage();
    private static final Storage ARRAY_STORAGE = new MapStorage();
    public static void main(String[] args) {
        final Resume r1 = new Resume("uuid1");
        //r1.setUuid("uuid1");
        final Resume r2 = new Resume("uuid2");
        //r2.setUuid("uuid3");
        final Resume r3 = new Resume("uuid3");
        //r3.setUuid("uuid2");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);



        System.out.println("Get r1: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Size: " + ARRAY_STORAGE.size());

        System.out.println("Get uuid2: " + ARRAY_STORAGE.get("uuid2"));

        printAll();
        ARRAY_STORAGE.update(r2);
        printAll();
        ARRAY_STORAGE.delete(r2.getUuid());
        printAll();
        ARRAY_STORAGE.clear();
        printAll();

        System.out.println("Size: " + ARRAY_STORAGE.size());
    }

    static void printAll() {
        System.out.println("\nGet All");
        for (Resume r : ARRAY_STORAGE.getAll()) {
            System.out.println(r);
        }
    }
}
