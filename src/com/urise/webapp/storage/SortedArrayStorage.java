package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    public void save(Resume r) {
        index = getIndex(r.getUuid());
        if (size == STORAGE_LIMIT) {
            System.out.println("storage overflow!");
        } else if (index > -1) {
            System.out.println("resume:" + r.getUuid() + " has already!");
        } else {
            index = Math.abs(index) - 1;
            System.arraycopy(storage, index, storage, index + 1, size - index);
            storage[index] = r;
            size++;
        }
    }

    @Override
    public void delete(String uuid) {
        index = getIndex(uuid);
        if (index > -1) {
            System.arraycopy(storage, index + 1, storage, index, size - index);
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("resume:" + uuid + " not found!");
        }
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
