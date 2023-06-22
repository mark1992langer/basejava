package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage {
    private static final int STORAGE_LIMIT = 10000;
    private final Resume[] storage = new Resume[STORAGE_LIMIT];

    private int size;
    private int resumeIndex;
    private Resume oldResume;
    private Resume updateResume = new Resume();

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        resumeIndex = getIndex(r.getUuid());
        if (resumeIndex != -1) {
            oldResume = r;
            updateResume.setUuid("uuid10");
            storage[resumeIndex] = updateResume;
        } else {
            System.out.println("resume:" + r.getUuid() + " not found!");
        }
    }

    public void save(Resume r) {
        resumeIndex = getIndex(r.getUuid());
        if (size >= storage.length) {
            System.out.println("the base is full!");
        } else if (resumeIndex != -1) {
            System.out.println("resume:" + r.getUuid() + " has already!");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        resumeIndex = getIndex(uuid);
        if (resumeIndex != -1) {
            return storage[resumeIndex];
        } else {
            System.out.println("resume:" + uuid + " not found!");
        }
        return null;
    }

    public void delete(String uuid) {
        resumeIndex = getIndex(uuid);
        if (resumeIndex != -1) {
            storage[resumeIndex] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("resume:" + uuid + " not found!");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public int size() {
        return size;
    }

    public int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
