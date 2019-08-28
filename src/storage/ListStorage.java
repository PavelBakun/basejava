package storage;

import model.Resume;

import java.util.ArrayList;
import java.util.List;

public class ListStorage extends AbstractStorage {
    private List<Resume> listStorage = new ArrayList<>();

    @Override
    public void clear() {
        listStorage.clear();
    }

    @Override
    public Resume[] getAll() {
        return listStorage.toArray(new Resume[0]);
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    protected Resume doGet(Object searchKey) {
        int index = (int) searchKey;
        return listStorage.get(index);
    }

    @Override
    protected void doUpdate(Object searchKey, Resume r) {
        int index = (int) searchKey;
        listStorage.set(index, r);
    }

    @Override
    protected void doSave(Object searchKey, Resume r) {
        listStorage.add(r);
    }

    @Override
    public void doDelete(Object searchKey) {
        int index = (int) searchKey;
        listStorage.remove(index);
    }

    @Override
    protected Object getSearchKey(String uuid) {
        int index = 0;
        for (Resume resume : listStorage) {
            if (uuid.equals(resume.getUuid())) {
                return index;
            }
            index++;
        }
        return -1;
    }

    @Override
    protected boolean isContainSearchKey(Object searchKey) {
        int index = (int) searchKey;
        return (index >= 0);
    }
}
