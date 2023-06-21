package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size;
    private String resumeString;

    public void clear() {
        Arrays.fill(storage,0, size-1, null);
        size = 0;
    }

    public void update(Resume r) {
        resumeString = r.getUuid();
        if (isResume(resumeString)) {
            r.setUuid("uuid10");
        } else error(0, resumeString);
    }

    public void save(Resume r) {
        resumeString = r.getUuid();
        if (!isResume(resumeString)) {
            if (size < storage.length) {
                storage[size] = r;
                size++;
            } else error(2, resumeString);
        } else error(1, resumeString);

    }

    public Resume get(String uuid) {
        if (isResume(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    return storage[i];
                }
            }
        } else error(0, uuid);
        return null;
    }

    public void delete(String uuid) {
        if (isResume(uuid)) {
            for (int i = 0; i < size; i++) {
                if (storage[i].getUuid().equals(uuid)) {
                    storage[i] = storage[size - 1];
                    storage[size - 1] = null;
                    size--;
                    break;
                }
            }
        } else error(0, uuid);
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage,0 ,size);
    }

    public int size() {
        return size;
    }

    public boolean isResume(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) return true;
        }
        return false;
    }

    public void error(int a, String b) {
        switch (a) {
            case 0 -> System.out.println("resume:" + b + " not found!");
            case 1 -> System.out.println("resume:" + b + " has already!");
            case 2 -> System.out.println("the base is full!");
        }
    }
}
