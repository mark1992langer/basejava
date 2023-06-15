/**
 * Array based storage for Resumes
 */

import java.util.Arrays;

public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    int size = 0;

    void clear() {
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] != null) {
                storage[i] = null;
            }
        }
        size = size();
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                size = size();
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = size - 1; i > 0; i--) {
            if (storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        size = size();
        for (int i = 0; i < storage.length; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size = size();
                break;
            }
        }

    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */

    Resume[] getAll() {
        Resume[] allResume = new Resume[size];
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] != null) {
                allResume[i] = storage[i];
            }
        }
        return allResume;
    }

    int size() {
        int s = 0;
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] == null) {
                s = i;
                break;
            }
        }
        return s;
    }
}
