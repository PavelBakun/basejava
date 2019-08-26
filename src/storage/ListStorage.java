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
    protected Resume doGet(int index, String uuid) {
        return listStorage.get(index);
    }

    @Override
    protected void doUpdate(int index, Resume r) {
        listStorage.set(index, r);
    }

    @Override
    protected void doSave(Resume r, int index) {
        listStorage.add(r);
    }

    @Override
    public void doDelete(int index, String uudi) {
        listStorage.remove(listStorage.get(index));
    }

    @Override
    protected int getIndex(String uuid) {
        int index = 0;
        for (Resume resume : listStorage) {
            if (uuid.equals(resume.getUuid())) {
                return index;
            }
            index++;
        }
        return -1;
    }
}
