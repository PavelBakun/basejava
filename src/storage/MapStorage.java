package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;

import java.util.HashMap;
import java.util.Map;

public class MapStorage extends AbstractStorage {

    private Map<String, Resume> mapStorage = new HashMap<>();

    @Override
    public void clear() {
        mapStorage.clear();
    }

    @Override
    public Resume[] getAll() {
        return mapStorage.values().toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return mapStorage.size();
    }

    @Override
    protected int checkNotContain(String uuid) {
        if (!mapStorage.containsKey(uuid)) {
            throw new NotExistStorageException(uuid);
        }
        return 0;
    }

    @Override
    protected int checkContain(String uuid) {
        if (mapStorage.containsKey(uuid)) {
            throw new ExistStorageException(uuid);
        }
        return 0;
    }

    @Override
    protected Resume doGet(int index, String uuid) {
        return mapStorage.get(uuid);
    }

    @Override
    protected void doUpdate(int index, Resume r) {
        doSave(r, index);
    }

    @Override
    protected void doSave(Resume r, int index) {
        mapStorage.put(r.getUuid(), r);
    }

    @Override
    protected void doDelete(int index, String uuid) {
        mapStorage.remove(uuid);
    }
}
