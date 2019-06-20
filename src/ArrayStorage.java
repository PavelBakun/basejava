/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    private int size = 0;

    void clear() {
        for (int i = 0; i < size; i++) {
            storage[i] = null;
        }
        size = 0;
    }

    void save(Resume r) {
        storage[size] = r;
        size += 1;
    }

    Resume get(String uuid) {
        for (int i = 0; i < size; i++) {
            if (equal(uuid, i)) {
                return storage[i];
            }
        }
        return null;
    }

    private boolean equal(String uuid, int index) {
        return (uuid.equals(storage[index].uuid));
    }

    void delete(String uuid) {
        int index = 0;
        for (int i = 0; i < size; i++) {
            if (equal(uuid, i)) {
                storage[i] = null;
                index = i;
            }
        }
        for (int i = index; i < size; i++) {
            storage[i] = storage[i + 1];
        }
        size -= 1;
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] resumes = new Resume[size()];
        for (int i = 0; i < size(); i++) {
            resumes[i] = storage[i];
        }
        return resumes;
    }

    int size() {
        return size;
    }
}
