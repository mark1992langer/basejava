package com.urise.webapp.storage;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.model.Resume;
import org.junit.Assert;
import org.junit.Test;

public abstract class AbstractArrayStorageTest extends AbstractStorageTest {

    protected AbstractArrayStorageTest(Storage storage) {
        super(storage);
    }


    @Test(expected = StorageException.class)
    public void storageOverflow() {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume("name" + i));
            }
        } catch (StorageException e) {
            Assert.fail();
        }
        storage.save(new Resume("full"));
    }
}