package billmacnamara.accela.techtest.dao;

import billmacnamara.accela.techtest.configuration.HibernateConfiguration;
import billmacnamara.accela.techtest.model.Person;
import billmacnamara.accela.techtest.util.AccelaLogger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class PersonDao {

    public void addNewPerson(Person person) {
        try (Session session = HibernateConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(person);
            transaction.commit();
            AccelaLogger.printInfo("Added person " + person.toString() + " to the DB");
        } catch (Exception e) {
            AccelaLogger.printError("Failed to add person " + person.toString() + " to the DB");
            AccelaLogger.printError(e.getMessage());
        }
    }

    public void editExistingPerson(Person existingPerson, String newFirstName, String newLastName) {
        Person updatedDetails = new Person(existingPerson.getId(), newFirstName, newLastName);

        try (Session session = HibernateConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.saveOrUpdate(updatedDetails);
            transaction.commit();
            AccelaLogger.printInfo("Updated person " + updatedDetails.toString() + " in the DB");
        } catch (Exception e) {
            AccelaLogger.printError("Failed to update person " + existingPerson.toString() + " in the DB");
            AccelaLogger.printError(e.getMessage());
        }
    }

    public void removePerson(Long id) {
        try (Session session = HibernateConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("delete from Person p where p.id =:id");
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
            AccelaLogger.printInfo("Removed person  with ID" + id + " from the DB");
        } catch (Exception e) {
            AccelaLogger.printError("Failed to remove person  with ID" + id + " from the DB");
            AccelaLogger.printError(e.getMessage());
        }
    }

    public List<Person> getPersons() {
        try (Session session = HibernateConfiguration.getInstance().getSession()) {
            return session.createQuery("select p from Person p", Person.class).getResultList();
        } catch (Exception e) {
            AccelaLogger.printError("Failed to retrieve persons from the DB");
            AccelaLogger.printError(e.getMessage());
        }
        return new ArrayList<>();
    }

    public Person findPersonById(Long id) {
        try (Session session = HibernateConfiguration.getInstance().getSession()) {
            Query query = session.createQuery("from Person p where p.id =:id");
            query.setParameter("id", id);

            List queryList = query.list();
            if (queryList != null && queryList.isEmpty()) {
                AccelaLogger.printWarn("No user with ID " + id + " was found");
                return null;
            } else {
                return (Person) queryList.get(0);
            }
        } catch (Exception e) {
            AccelaLogger.printError("Failed to retrieve persons from the DB");
            AccelaLogger.printError(e.getMessage());
        }
        AccelaLogger.printWarn("No user with ID " + id + " was found");
        return null;
    }
}
