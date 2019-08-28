package storage;

import exception.StorageException;
import model.Resume;

import java.util.Arrays;

public abstract class AbstractArrayStorage extends AbstractStorage {

    protected static final int STORAGE_LIMIT = 10_000;

    protected Resume[] storage = new Resume[STORAGE_LIMIT];
    protected int size = 0;

    @Override
    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    @Override
    public Resume[] getAll() {
        return Arrays.copyOfRange(storage, 0, size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected Resume doGet(Object searchKey) {
        int index = (int) searchKey;
        return storage[index];
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        int index = (int) searchKey;
        storage[index] = r;
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        if (size == STORAGE_LIMIT) {
            throw new StorageException("Storage overflow", r.getUuid());
        } else {
            int index = (int) searchKey;
            insertElement(r, index);
            size++;
        }
    }

    @Override
    protected void doDelete(Object searchKey) {
        int index = (int) searchKey;
        fillDeletedElement(index);
        storage[size - 1] = null;
        size--;
    }

    @Override
    protected boolean isContainSearchKey(Object searchKey) {
        int index = (int) searchKey;
        return (index >= 0);
    }

    protected abstract void fillDeletedElement(int index);

    protected abstract void insertElement(Resume r, int index);
}