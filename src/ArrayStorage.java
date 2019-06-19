/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    Resume[] storage = new Resume[10000];
    void clear() {
        int lastResume = size();
        for (int i = 0; i < lastResume; i++) {
            storage[i] = null;
        }
    }

    void save(Resume r) {
        storage[size()] = r;
    }

    Resume get(String uuid) {
        int index = 0;
        while (index < size()) {
            if (equal(uuid, index)) {
                return storage[index];
            }
            index += 1;
        }
        return null;
    }

    private boolean equal (String uuid, int index) {
        return (uuid.equals(storage[index].uuid));
    }

    void delete(String uuid) {
        int index = 0;
        Resume findElement = null;
        int size = size();
        while (index < size() && !(equal(uuid, index))) {
            if (equal(uuid, index)) {
                storage[index] = null;
            }
            index += 1;
        }
        for (; index < size; index++) {
            storage[index] = storage[index+1];
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    Resume[] getAll() {
        Resume[] newStorage = new Resume[size()];
        for(int i = 0; i < size(); i++) {
            newStorage[i] = storage[i];
        }
        return newStorage;
    }

    int size() {
        int index = 0;
        while (storage[index] != null) {
            index += 1;
        }
        return index;
    }
}
