package billmacnamara.accela.techtest.dao;

import billmacnamara.accela.techtest.model.Person;
import org.junit.*;

import java.util.List;

/**
 * I didn't get a chance to write a full test suite, so only basic test for the happy path are written here
 * I don't like that the tests are calling the actual DB, would mock it in a real setup but don't have time to implement here
 */
public class PersonDaoTest {

    private PersonDao personDao = new PersonDao();
    private Person testPerson;

    @Before
    public void setup() {
        testPerson = new Person(12945068L, "Bill", "MacNamara");

    }

    @Test
    public void testAddPerson() {
        int initialRecordCount = getDbRecordCount();

        personDao.addNewPerson(testPerson);

        List<Person> personList = personDao.getPersons();
        Assert.assertTrue(personList.contains(testPerson));
        Assert.assertEquals(initialRecordCount + 1, getDbRecordCount());
    }

    @Test
    public void testEditExistingPerson() {
        String newFirstName = "William";

        personDao.editExistingPerson(testPerson, newFirstName, "MacNamara");

        Person updatedPerson = personDao.findPersonById(testPerson.getId());
        Assert.assertEquals(newFirstName, updatedPerson.getFirstName());
        Assert.assertNotEquals(testPerson.getFirstName(), updatedPerson.getFirstName());
    }

    @Test
    public void testRemovePerson() {
        personDao.addNewPerson(testPerson);
        int initialRecordCount = getDbRecordCount();

        personDao.removePerson(testPerson.getId());

        List<Person> personList = personDao.getPersons();
        Assert.assertFalse(personList.contains(testPerson));
        Assert.assertEquals(initialRecordCount - 1, getDbRecordCount());
    }

    private int getDbRecordCount() {
        return personDao.getPersons().size();
    }

    private void removeTestRecord(Long id) {
        personDao.removePerson(id);
    }

    @After
    public void tearDown() {
        removeTestRecord(testPerson.getId());
    }
}
