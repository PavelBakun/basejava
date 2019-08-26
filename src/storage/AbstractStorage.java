package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

public abstract class AbstractStorage implements Storage {

    @Override
    public Resume get(String uuid) {
        Object searchKey = checkNotContain(uuid);
        return doGet(searchKey);
    }

    @Override
    public void update(Resume r) {
        Object searchKey = checkNotContain(r.getUuid());
        doUpdate(searchKey, r);
    }

    @Override
    public void save(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (isContainSearchKey(searchKey)) {
            throw new ExistStorageException(r.getUuid());
        }
        doSave(searchKey, r);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = checkNotContain(uuid);
        doDelete(searchKey, uuid);
    }

    protected Object checkNotContain(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isContainSearchKey(searchKey)) {
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    protected abstract Resume doGet(Object searchKey);

    protected abstract void doUpdate(Object searchKey, Resume r);

    protected abstract void doSave(Object searchKey, Resume r);

    protected abstract void doDelete(Object searchKey, String uuid);

    protected abstract Object getSearchKey(String uuid);

    protected abstract boolean isContainSearchKey(Object searchKey);
}
