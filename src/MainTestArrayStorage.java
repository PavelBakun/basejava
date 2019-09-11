import model.Resume;
import storage.ArrayStorage;
import storage.MapUuidStorage;
import storage.SortedArrayStorage;
import storage.Storage;

/**
 * Test for your ArrayStorage implementation
 */
public class MainTestArrayStorage {
    private static final ArrayStorage ARRAY_STORAGE = new ArrayStorage();
    private static final SortedArrayStorage SORTED_ARRAY_STORAGE = new SortedArrayStorage();
    private static final MapUuidStorage MAP_STORAGE = new MapUuidStorage();

    public static void main(String[] args) {
        Resume resume1 = new Resume("uuid1", "Ivan");
        Resume resume2 = new Resume("uuid2", "Pavel");
        Resume resume3 = new Resume("uuid3", "Alena");
        Resume resume0 = new Resume("uuid0", "Ira");

        ARRAY_STORAGE.save(resume1);
        ARRAY_STORAGE.save(resume2);
        ARRAY_STORAGE.save(resume3);
        ARRAY_STORAGE.save(resume0);
        SORTED_ARRAY_STORAGE.save(resume1);
        SORTED_ARRAY_STORAGE.save(resume2);
        SORTED_ARRAY_STORAGE.save(resume3);
        SORTED_ARRAY_STORAGE.save(resume0);
        MAP_STORAGE.save(resume1);
        MAP_STORAGE.save(resume2);
        MAP_STORAGE.save(resume3);
        MAP_STORAGE.save(resume0);

        System.out.println("Get resume1 from ArrayStorage: " + ARRAY_STORAGE.get(resume1.getUuid()));
        System.out.println("Get resume1 from SortedArrayStorage: " + SORTED_ARRAY_STORAGE.get(resume1.getUuid()));
        System.out.println("Get resume1 from MapStorage: " + MAP_STORAGE.get(resume1.getUuid()));
        System.out.println("Size of ArrayStorage: " + ARRAY_STORAGE.size());
        System.out.println("Size of SortedArrayStorage: " + SORTED_ARRAY_STORAGE.size());
        System.out.println("Size of MapStorage: " + MAP_STORAGE.size());

        System.out.println("Get from ArrayStorage: " + ARRAY_STORAGE.get("dummy"));
        System.out.println("Get from SortedArrayStorage: " + SORTED_ARRAY_STORAGE.get("dummy"));

        printAll(ARRAY_STORAGE);
        printAll(SORTED_ARRAY_STORAGE);

        ARRAY_STORAGE.delete(resume1.getUuid());
        printAll(ARRAY_STORAGE);
        SORTED_ARRAY_STORAGE.delete(resume1.getUuid());
        printAll(SORTED_ARRAY_STORAGE);


        ARRAY_STORAGE.update(resume2);
        SORTED_ARRAY_STORAGE.update(resume2);
        System.out.println("Update resume2 from ArrayStorage: " + ARRAY_STORAGE.get(resume2.getUuid()));
        System.out.println("Update resume2 from SortedArrayStorage: " + SORTED_ARRAY_STORAGE.get(resume2.getUuid()));

        System.out.println("Update resume4: ");
        Resume resume4 = new Resume("uuid4", "failName");
        ARRAY_STORAGE.update(resume4);
        SORTED_ARRAY_STORAGE.update(resume4);

        ARRAY_STORAGE.clear();
        printAll(ARRAY_STORAGE);
        SORTED_ARRAY_STORAGE.clear();
        printAll(SORTED_ARRAY_STORAGE);

        System.out.println("Size of ArrayStorage: " + ARRAY_STORAGE.size());
        System.out.println("Size of SortedArrayStorage: " + SORTED_ARRAY_STORAGE.size());
    }

    static void printAll(Storage storage) {
        System.out.println("\nGet All from " + storage);
        for (Resume resume : storage.getAllSorted()) {
            System.out.println(resume);
        }
    }
}