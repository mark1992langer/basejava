package com.urise.webapp.storage;

import com.urise.webapp.model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    List<Resume> list = new ArrayList<>();

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Resume[] getAll() {
        return list.toArray(new Resume[size()]);
    }

    @Override
    public int size() {
        return list.size();
    }


    @Override
    protected void  doUpdate(Resume r){
        list.set((Integer)searchKey, r);
    }

    @Override
    protected void doSave(Resume r, Object searchKey){
        list.add(r);
    }

    @Override
    protected  void doDelete(Object searchKey){
        int i = (Integer)searchKey;
        list.remove(i);
    }

    @Override
    protected Resume doGet(Object searchKey){
        return list.get((Integer)searchKey);
    }


    @Override
    protected void getSearchKey(String uuid){
        Resume Key = new Resume(uuid);
        searchKey = list.lastIndexOf(Key);
    }

    @Override
    protected boolean isExist(Object obj) {
        return (Integer) obj >= 0;
    }
}
