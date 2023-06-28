package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

/**
 * Array based storage for Resumes
 */

public class ArrayStorage extends AbstractArrayStorage{

    @Override
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

    @Override
    protected int getIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (storage[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }
}
