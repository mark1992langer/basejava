/**
 * Array based storage for Resumes
 */


public class ArrayStorage {
    Resume[] storage = new Resume[10000];

    int size;

    void clear() {
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] != null) {
                storage[i] = null;
            }
        }
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size++;
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
        for (int i = 0; i < size; i++) {
            if (storage[i].uuid.equals(uuid)) {
                storage[i] = storage[size - 1];
                storage[size - 1] = null;
                size--;
                break;
            }
        }
    }


    /**
     * @return array, contains only Resumes in storage (without null)
     */

    Resume[] getAll() {
        Resume[] allResume = new Resume[size];
        System.arraycopy(storage, 0, allResume, 0, size);
        return allResume;
    }

    int size() {
        return size;
    }
}
