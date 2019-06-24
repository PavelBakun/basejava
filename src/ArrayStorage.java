import java.util.Arrays;

/**
 * Array based storage for Resumes
 */
public class ArrayStorage {
    private Resume[] storage = new Resume[10000];
    private int size = 0;

    public void clear() {
        Arrays.fill(storage, 0, size - 1, null);
        size = 0;
    }

    public void save(Resume r) {
        if (size < 10000) {
            if (!isPresent(r.getUuid())) {
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
        if (isPresent(r.getUuid())) {
            storage[findIndex(r.getUuid())] = r;
        } else {
            System.out.println("Error, this resume is not already present");
        }
    }

    public Resume get(String uuid) {
        return ((isPresent(uuid)) ? storage[findIndex(uuid)] : null);
    }

    public void delete(String uuid) {
        if (isPresent(uuid)) {
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

    private boolean isPresent(String uuid) {
        return (findIndex(uuid) != -1);
    }

    public int findIndex(String uuid) {
        for (int i = 0; i < size; i++) {
            if (uuid.equals(storage[i].getUuid())) {
                return i;
            }
        }
        return -1;
    }
}
