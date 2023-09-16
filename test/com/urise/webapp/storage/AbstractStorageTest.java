package com.urise.webapp.storage;

import com.urise.webapp.Config;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static com.urise.webapp.TestData.*;
import static org.junit.Assert.*;

public abstract class AbstractStorageTest {
    protected static final File STORAGE_DIR = Config.get().getStorageDir();

    protected Storage storage;

    protected AbstractStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void clear() {
        storage.clear();
        assertStorageSize(0);
        Resume[] resumes = storage.getAllSorted().toArray(new Resume[0]);
        assertArrayEquals(resumes, storage.getAllSorted().toArray(new Resume[0]));
    }

    @Test
    public void update() {
        Resume newResume = new Resume(UUID_1, "uuid1");
        storage.update(newResume);
        assertEquals(newResume, storage.get(UUID_1));
    }

    @Test
    public void save() {
        storage.save(RESUME_4);
        assertStorageSize(4);
        assertGetResume(RESUME_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() {
        storage.delete(UUID_1);
        assertStorageSize(2);
        assertGetResume(RESUME_1);
    }

    @Test
    public void get() {
        assertGetResume(RESUME_1);
        assertGetResume(RESUME_2);
        assertGetResume(RESUME_3);
    }

    @Test
    public void getAllSorted() {
        Resume[] expected = storage.getAllSorted().toArray(new Resume[0]);
        assertArrayEquals(expected, storage.getAllSorted().toArray(new Resume[0]));
        assertStorageSize(3);
    }

    @Test
    public void size() {
        assertStorageSize(3);
        Resume[] resumes = storage.getAllSorted().toArray(new Resume[0]);
        assertArrayEquals(resumes, storage.getAllSorted().toArray(new Resume[0]));
    }


    @Test(expected = NotExistStorageException.class)
    public void getNotExist() {
        storage.get("Nova");
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() {
        storage.update(RESUME_4);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() {
        storage.delete(UUID_4);
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() {
        storage.save(RESUME_1);
    }


    private void assertGetResume(Resume r) {
        assertEquals(r, storage.get(r.getUuid()));
    }

    private void assertStorageSize(int size) {
        assertEquals(size, storage.size());
    }
}