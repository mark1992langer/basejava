package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */

public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected  Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size;
    protected int index;

    public final void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public final void update(Resume r) {
        index = getIndex(r.getUuid());
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("resume:" + r.getUuid() + " not found!");
        }
    }

    public abstract void save(Resume r);

    public final void delete(String uuid) {
        index = getIndex(uuid);
        if (index != -1) {
            storage[index] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("resume:" + uuid + " not found!");
        }
    }

    public final Resume get(String uuid) {
        index = getIndex(uuid);
        if (index != -1) {
            return storage[index];
        } else {
            System.out.println("resume:" + uuid + " not found!");
        }
        return null;
    }

    public final Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    public final int size() {
        return size;
    }

    protected abstract int getIndex(String uuid);
}
