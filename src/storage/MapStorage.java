package storage;

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
    protected Resume doGet(Object searchKey) {
        String uuid = (String) searchKey;
        return mapStorage.get(uuid);
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        mapStorage.put((String) searchKey, r);
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        doUpdate(searchKey, r);
    }

    @Override
    protected void doDelete(Object searchKey, String uuid) {
        mapStorage.remove(uuid);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isContainSearchKey(Object searchKey) {
        String uuid = (String) searchKey;
        return mapStorage.containsKey(uuid);
    }
}
