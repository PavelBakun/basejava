package storage;

import exception.ExistStorageException;
import exception.NotExistStorageException;
import model.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStorageTest {
    protected Storage storage;

    private static final String UUID_1 = "uuid1";
    private static final String UUID_2 = "uuid2";
    private static final String UUID_3 = "uuid3";
    private static final String FAIL_UUID = "fail";

    private static final Resume RESUME_1 = new Resume(UUID_1, "Ivan");
    private static final Resume RESUME_2 = new Resume(UUID_2, "Pavel");
    private static final Resume RESUME_3 = new Resume(UUID_3, "Alena");

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
        Resume expected = new Resume(FAIL_UUID, "failName");
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
    public void getAllSorted() throws Exception {
        List<Resume> expectedList = new ArrayList<>();
        expectedList.add(RESUME_3); //Alena
        expectedList.add(RESUME_1); //Ivan
        expectedList.add(RESUME_2); //Pavel
        List<Resume> actualList = storage.getAllSorted();
        Assert.assertEquals(expectedList, actualList);
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
        Resume expected = new Resume(UUID_2, "Ira");
        storage.update(expected);
        Resume actual = storage.get(UUID_2);
        Assert.assertSame(expected, actual);
    }

    @Test(expected = NotExistStorageException.class)
    public void updateNotExist() throws Exception {
        storage.update(new Resume(FAIL_UUID, "failName"));
    }

    @Test
    public void clear() throws Exception {
        storage.clear();
        Assert.assertEquals(0, storage.size());
    }
}
