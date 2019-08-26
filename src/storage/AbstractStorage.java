package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public Resume get(String uuid) {
        int index = checkNotContain(uuid);
        return doGet(index, uuid);
    }

    @Override
    public void update(Resume r) {
        int index = checkNotContain(r.getUuid());
        doUpdate(index, r);
    }

    @Override
    public void save(Resume r) {
        int index = checkContain(r.getUuid());
        doSave(r, index);
    }

    @Override
    public void delete(String uuid) {
        int index = checkNotContain(uuid);
        doDelete(index, uuid);
    }

    protected int getIndex(String uuid) {
        return 0;
    }

    protected Resume doGet(int index, String uuid) {
        return null;
    }

    protected int checkNotContain(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        return index;
    }

    protected int checkContain(String uuid) {
        int index = getIndex(uuid);
        if (index >= 0) {
            throw new ExistStorageException(uuid);
        }
        return index;
    }

    protected void doUpdate(int index, Resume r) {

    }

    protected void doSave(Resume r, int index) {
    }

    protected void doDelete(int index, String uuid) {
    }
}
