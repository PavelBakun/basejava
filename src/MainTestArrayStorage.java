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
        Resume r1 = new Resume("uuid1", "Ivan");
        Resume r2 = new Resume("uuid2", "Pavel");
        Resume r3 = new Resume("uuid3", "Alena");
        Resume r0 = new Resume("uuid0", "Ira");

        ARRAY_STORAGE.save(r1);
        ARRAY_STORAGE.save(r2);
        ARRAY_STORAGE.save(r3);
        ARRAY_STORAGE.save(r0);
        SORTED_ARRAY_STORAGE.save(r1);
        SORTED_ARRAY_STORAGE.save(r2);
        SORTED_ARRAY_STORAGE.save(r3);
        SORTED_ARRAY_STORAGE.save(r0);
        MAP_STORAGE.save(r1);
        MAP_STORAGE.save(r2);
        MAP_STORAGE.save(r3);
        MAP_STORAGE.save(r0);

        System.out.println("Get r1 from ArrayStorage: " + ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Get r1 from SortedArrayStorage: " + SORTED_ARRAY_STORAGE.get(r1.getUuid()));
        System.out.println("Get r1 from MapStorage: " + MAP_STORAGE.get(r1.getUuid()));
        System.out.println("Size of ArrayStorage: " + ARRAY_STORAGE.size());
        System.out.println("Size of SortedArrayStorage: " + SORTED_ARRAY_STORAGE.size());
        System.out.println("Size of MapStorage: " + MAP_STORAGE.size());

        System.out.println("Get from ArrayStorage: " + ARRAY_STORAGE.get("dummy"));
        System.out.println("Get from SortedArrayStorage: " + SORTED_ARRAY_STORAGE.get("dummy"));

        printAll(ARRAY_STORAGE);
        printAll(SORTED_ARRAY_STORAGE);

        ARRAY_STORAGE.delete(r1.getUuid());
        printAll(ARRAY_STORAGE);
        SORTED_ARRAY_STORAGE.delete(r1.getUuid());
        printAll(SORTED_ARRAY_STORAGE);


        ARRAY_STORAGE.update(r2);
        SORTED_ARRAY_STORAGE.update(r2);
        System.out.println("Update r2 from ArrayStorage: " + ARRAY_STORAGE.get(r2.getUuid()));
        System.out.println("Update r2 from SortedArrayStorage: " + SORTED_ARRAY_STORAGE.get(r2.getUuid()));

        System.out.println("Update r4: ");
        Resume r4 = new Resume("uuid4", "failName");
        ARRAY_STORAGE.update(r4);
        SORTED_ARRAY_STORAGE.update(r4);

        ARRAY_STORAGE.clear();
        printAll(ARRAY_STORAGE);
        SORTED_ARRAY_STORAGE.clear();
        printAll(SORTED_ARRAY_STORAGE);

        System.out.println("Size of ArrayStorage: " + ARRAY_STORAGE.size());
        System.out.println("Size of SortedArrayStorage: " + SORTED_ARRAY_STORAGE.size());
    }

    static void printAll(Storage storage) {
        System.out.println("\nGet All from " + storage);
        for (Resume r : storage.getAll()) {
            System.out.println(r);
        }
    }
}