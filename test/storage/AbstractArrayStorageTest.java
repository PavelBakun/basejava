package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import exception.StorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;

public abstract class AbstractArrayStorageTest {
    private Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String FAIL_UUID = "fail";

    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);

    AbstractArrayStorageTest(Storage storage) {
        this.storage = storage;
    }

    @Before
    public void setUp() throws Exception {
        storage.clear();
        storage.save(RESUME_1);
        storage.save(RESUME_2);
        storage.save(RESUME_3);
    }

    @Test
    public void size() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

    @Test(expected = ExistStorageException.class)
    public void saveExist() throws Exception {
        storage.save(new Resume(UUID_2));
    }

    @Test(expected = StorageException.class)
    public void saveOverflow() throws Exception {
        storage.clear();
        try {
            for (int i = 0; i < AbstractArrayStorage.STORAGE_LIMIT; i++) {
                storage.save(new Resume());
            }
        } catch (StorageException e) {
            fail("test saveOverflow() failed!");
        }
        storage.save(new Resume());
    }

    @Test
    public void save() throws Exception {
        Resume actual = new Resume(FAIL_UUID);
        storage.save(actual);
        Resume expected = storage.get(FAIL_UUID);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void get() throws Exception {
        Resume actual = RESUME_2;
        Resume expected = storage.get(UUID_2);
        Assert.assertEquals(expected, actual);
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(FAIL_UUID);
    }

    @Test
    public void getAll() throws Exception {
        Resume[] resumes = storage.getAll();
        Resume[] array = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
        boolean bool = true;
        for (int i = 0; i < array.length; i++) {
            if (!resumes[i].equals(array[i])) {
                bool = false;
            }
        }
        Assert.assertEquals(true, bool);
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_2);
        if (storage.size() > 2) {
            fail("Size didn't decrease after delete()");
        }
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(FAIL_UUID);
    }

    @Test
    public void update() throws Exception {
        Resume actual = storage.get(UUID_2);
        storage.update(new Resume(UUID_2));
        Resume expected = storage.get(UUID_2);
        if (actual == expected) {
            fail("Update() failed!");
        }
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume(FAIL_UUID));
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }
}

