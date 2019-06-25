import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10_000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size < storage.length) {
            if (findIndex(r.getUuid()) == -1) {
                storage[size] = r;
                size++;
            } else {
                System.out.println("Error, this resume is already present in the storage");
            }
        } else {
            System.out.println("Error, this storage is already complete");
        }
    }

    public void update(Resume r) {
        int index = findIndex(r.getUuid());
        if (index != -1) {
            storage[index] = r;
        } else {
            System.out.println("Error, this resume is not already present");
        }
    }

    public Resume get(String uuid) {
        int index = findIndex(uuid);
        if (index != -1) {
            return storage[index];
        }
        System.out.println("Error, this resume is not already present");
        return null;
    }

    public void delete(String uuid) {
        if (findIndex(uuid) != -1) {
            storage[findIndex(uuid)] = storage[size - 1];
            storage[size - 1] = null;
            size--;
        } else {
            System.out.println("Error, this resume is not already present");
        }
    }

    /**
     * @return array, contains only Resumes in storage (without null)
     */
    public Resume[] getAll() {
        return Arrays.copyOf(storage, size);
    }

    public int size() {
        return size;
    }

    private int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
