package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.Arrays;

public class SortedArrayStorage extends AbstractArrayStorage {

    @Override
    protected void saveResume(Resume r) {
        index = Math.abs(index) - 1;
        System.arraycopy(storage, index, storage, index + 1, size - index);
        storage[index] = r;

    }

    @Override
    protected void deleteResume(String uuid) {
        System.arraycopy(storage, index + 1, storage, index, size - index);
    }

    @Override
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume();
        searchKey.setUuid(uuid);
        return Arrays.binarySearch(storage, 0, size, searchKey);
    }
}
