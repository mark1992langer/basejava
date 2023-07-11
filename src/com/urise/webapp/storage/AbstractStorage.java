package com.urise.webapp.storage;

import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;

public abstract class AbstractStorage implements Storage {
    protected Object searchKey;


    @Override
    public void update(Resume r) {
        getExistingSearchKey(r.getUuid());
        doUpdate(r, searchKey);
    }

    @Override
    public void save(Resume r) {
        getNotExistingSearchKey(r.getUuid());
        doSave(r, searchKey);
    }

    @Override
    public void delete(String uuid) {
        getExistingSearchKey(uuid);
        doDelete(searchKey);
    }

    @Override
    public Resume get(String uuid) {
        getExistingSearchKey(uuid);
        return doGet(searchKey);
    }


    public abstract void clear();
    public abstract Resume[] getAll();
    public abstract int size();
    protected abstract void doUpdate(Resume r, Object searchKey);
    protected abstract void doSave(Resume r, Object searchKey);
    protected abstract void doDelete(Object searchKey);
    protected abstract Resume doGet(Object searchKey);

    protected abstract Object getSearchKey(String uuid);
    protected abstract boolean isExist(Object obj);

    protected Object getExistingSearchKey(String uuid) {
        getSearchKey(uuid);
        if (isExist(searchKey)) {
            return searchKey;
        }
        throw new NotExistStorageException(uuid);
    }

    protected Object getNotExistingSearchKey(String uuid) {
        getSearchKey(uuid);
        if (!isExist(searchKey)) {
            return searchKey;
        }
        throw new ExistStorageException(uuid);
    }
}
