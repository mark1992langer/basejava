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
        size();
    }

    void save(Resume r) {
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] == null) {
                storage[i] = r;
                break;
            }
        }
    }

    Resume get(String uuid) {
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] != null && storage[i].uuid.equals(uuid)) {
                return storage[i];
            }
        }
        return null;
    }

    void delete(String uuid) {
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] != null && storage[i].uuid.equals(uuid)) {
                for (int j = i + 1; j < storage.length - 1; j++) {
                    storage[j - 1] = storage[j];
                }
            }
        }
        size();
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */

    Resume[] getAll() {
        size = size();
        Resume[] allResume = new Resume[size];
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] != null) {
                allResume[i] = storage[i];
                size++;
            }
        }
        return allResume;
    }

    int size() {
        size = 0;
        for (int i = 0; i < storage.length - 1; i++) {
            if (storage[i] != null) size++;
        }
        return size;
    }
}
