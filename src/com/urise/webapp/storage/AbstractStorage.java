package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {

    protected static final int STORAGE_LIMIT = 10000;
    protected int index;
    protected int size;

    @Override
    public void clear() {

    }

    @Override
    public void update(Resume r) {
        index = getIndex(r.getUuid());
        if (index >= 0) {
            updateResume(r, index);
        } else {
            throw new NotExistStorageException(r.getUuid());
        }
    }

    @Override
    public void save(Resume r) {
        index = getIndex(r.getUuid());
        if (index >= 0) {
            throw new ExistStorageException(r.getUuid());
        } else {
            addResume(r, index);
        }
    }

    @Override
    public void delete(String uuid) {
        index = getIndex(uuid);
        if (index >= 0) {
            removeResume(index);
        } else {
            throw new NotExistStorageException(uuid);
        }
    }

    @Override
    public Resume get(String uuid) {
        index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return get(index);
    }

    @Override
    public Resume[] getAll() {
        return new Resume[0];
    }

    @Override
    public int size() {
        return 0;
    }

    protected abstract int getIndex(String uuid);

    protected abstract void updateResume(Resume r, int index);

    protected abstract void addResume(Resume r, int index);

    protected abstract void removeResume(int index);

    protected abstract Resume get(int index);

}
