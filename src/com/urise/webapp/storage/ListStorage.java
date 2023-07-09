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
    protected void  updateResume(Resume r, int index){
        list.set(index, r);
    }

    @Override
    protected void addResume(Resume r, int index){
        list.add(r);
    }

    @Override
    protected  void removeResume(int index){
        list.remove(index);
    }

    @Override
    protected Resume get(int index){
        return list.get(index);
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
    protected int getIndex(String uuid) {
        Resume searchKey = new Resume(uuid);
        return list.lastIndexOf(searchKey);
    }


}
