package storage;

import model.Resume;

import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public abstract class AbstractArrayStorage implements Storage {
    protected static final int STORAGE_LIMIT = 10000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size >= STORAGE_LIMIT) {
            System.out.println("Storage overflow");
        } else {
            int index = getIndex(r.getUuid());
            if (index >= 0) {
                System.out.println("Resume " + r.getUuid() + " already exist");
            } else {
                insertElement(r, index);
                size++;
            }
        }
    }

    public int size() {
        return size;
    }

    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            System.out.println("Resume " + r.getUuid() + " not exist");
        } else {
            storage[index] = r;
        }
    }

    public Resume get(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
            return null;
        }
        return storage[index];
    }

    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            System.out.println("Resume " + uuid + " not exist");
        } else {
            trunsposeIndex(index);
            storage[size - 1] = null;
            size--;
        }
    }

    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    protected abstract void insertElement(Resume r, int index);

    protected abstract void trunsposeIndex(int index);

    protected abstract int getIndex(String uuid);
}
