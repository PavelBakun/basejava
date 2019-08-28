package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class AbstractStorageTest {
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String FAIL_UUID = "fail";

    private static final Resume RESUME_1 = new Resume(UUID_1);
    private static final Resume RESUME_2 = new Resume(UUID_2);
    private static final Resume RESUME_3 = new Resume(UUID_3);

    protected AbstractStorageTest(Storage storage) {
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
        storage.save(RESUME_2);
    }

    @Test
    public void save() throws Exception {
        Resume expected = new Resume(FAIL_UUID);
        storage.save(expected);
        Assert.assertEquals(4, storage.size());
        Resume actual = storage.get(FAIL_UUID);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void get() throws Exception {
        Assert.assertEquals(RESUME_2, storage.get(UUID_2));
    }

    @Test(expected = NotExistStorageException.class)
    public void getNotExist() throws Exception {
        storage.get(FAIL_UUID);
    }

    @Test
    public void getAll() throws Exception {
        Resume[] expectedArray = new Resume[]{RESUME_1, RESUME_2, RESUME_3};
        Resume[] actualArray = storage.getAll();
        Assert.assertEquals(3, actualArray.length);
        for (Resume r : actualArray) {
            Assert.assertTrue(r.equals(expectedArray[0]) || r.equals(expectedArray[1]) || r.equals(expectedArray[2]));
            Assert.assertFalse(r.equals(expectedArray[0]) && r.equals(expectedArray[1]) && r.equals(expectedArray[2]));
        }
        //Assert.assertArrayEquals((new Resume[]{RESUME_1, RESUME_2, RESUME_3}), storage.getAll());
    }

    @Test(expected = NotExistStorageException.class)
    public void delete() throws Exception {
        storage.delete(UUID_2);
        Assert.assertEquals(2, storage.size());
        storage.get(UUID_2);
    }

    @Test(expected = NotExistStorageException.class)
    public void deleteNotExist() throws Exception {
        storage.delete(FAIL_UUID);
    }

    @Test
    public void update() throws Exception {
        Resume expected = new Resume(UUID_2);
        storage.update(expected);
        Resume actual = storage.get(UUID_2);
        Assert.assertSame(expected, actual);
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
