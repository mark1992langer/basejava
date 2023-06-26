package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage{

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void update(Resume r) {
        index = getIndex(r.getUuid());
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("resume:" + r.getUuid() + " not found!");
        }
    }

    public void save(Resume r) {
        index = getIndex(r.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("storage overflow!");
        } else if (index != -1) {
            System.out.println("resume:" + r.getUuid() + " has already!");
        } else {
            storage[size] = r;
            size++;
        }
    }

    public Resume get(String uuid) {
        index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("resume:" + uuid + " not found!");
        }
        return null;
    }

    public void delete(String uuid) {
        index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
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

    private int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
