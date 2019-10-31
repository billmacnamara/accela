package billmacnamara.accela.techtest.service;

import billmacnamara.accela.techtest.dao.PersonDao;
import billmacnamara.accela.techtest.model.Person;
import billmacnamara.accela.techtest.ui.UserInteractionManager;
import billmacnamara.accela.techtest.util.AccelaLogger;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PersonServiceImpl implements PersonService {

    private Scanner input = UserInteractionManager.getInput();
    private PersonDao personDao = new PersonDao();

    public void addPerson() {
        AccelaLogger.printInfo("ADD PERSON");

        /*  TODO allow the user to update the DB via XML
        System.out.println("Choose an option from the list below by entering its corresponding number");
        System.out.println("1) Manually enter person details");
        System.out.println("2) Upload XML file");
        System.out.println("3) Go Back");

        int input = this.input.nextInt();
        switch (input) {
            case 1:
                this.addPersonManually();
                break;
            case 2:
                this.addPersonThroughXml();
                break;
            case 3:
                break;
            default:
                AccelaLogger.printWarn("That is not a valid option");
                this.addPerson();
        }*/
        this.addPersonManually();
    }

    private void addPersonManually() {
        Long id = null;
        try {
            System.out.println("Enter the new person's ID");
            id = input.nextLong();
        } catch (InputMismatchException e) {
            AccelaLogger.printWarn(UserInteractionManager.INVALID_INPUT);
            input.next();
            addPersonManually();
            return;
        }

        System.out.println("Enter the new person's first name");
        input.nextLine();
        String firstName = input.nextLine();

        System.out.println("Enter the new person's last name");
        String lastName = input.nextLine();

        System.out.println(String.format("ID: %1$s; First Name: %2$s; Last Name: %3$s", id, firstName, lastName));
        if (!getConfirmation()) {
            this.addPersonManually();
            return;
        }

        if (this.isIdPresentInDb(id)) {
            AccelaLogger.printWarn("ID must be unique");
            this.addPersonManually();
        }

        Person person = new Person(id, firstName, lastName);
        this.personDao.addNewPerson(person);
    }

    private void addPersonThroughXml() {
        // TODO
    }


    public void editPerson() {
        AccelaLogger.printInfo("EDIT PERSON");

        System.out.println("Enter the person you wish to edit's first name");
        String firstName = input.nextLine();

        System.out.println("Enter the person you wish to edit's last name");
        String lastName = input.nextLine();

        Person existingPerson = this.isPersonPresentInDb(firstName, lastName);
        if (existingPerson == null) {
            AccelaLogger.printWarn(String.format("We could not find a %1$s %2$s in our database.", firstName, lastName));
            System.out.println("Would you like to create a new user instead? (y/n)");
            if (!getConfirmation()) {
                input.nextLine();
                this.editPerson();
                return;
            } else {
                this.addPersonManually();
                return;
            }
        }

        System.out.println("Enter a new first name for the person you wish to edit (Just press Enter if unchanged)");
        String newFirstName = input.nextLine();

        System.out.println("Enter a new last name for the person you wish to edit (Just press Enter if unchanged)");
        String newLastName = input.nextLine();

        if (newFirstName.equals("")) {
            newFirstName = existingPerson.getFirstName();
        }
        if (newLastName.equals("")) {
            newLastName = existingPerson.getLastName();
        }

        this.personDao.editExistingPerson(existingPerson, newFirstName, newLastName);
    }

    public void removePerson() {
        AccelaLogger.printInfo("REMOVE PERSON");

        System.out.println("Enter the ID of the person you wish to remove from the database");
        Long id = input.nextLong();

        if (this.isIdPresentInDb(id)) {
            this.personDao.removePerson(id);
        } else {
            AccelaLogger.printWarn("We couldn't find a user with that ID");
        }
    }

    public List<Person> getPersons() {
        return this.personDao.getPersons();
    }

    public int getPersonCount() {
        return this.getPersons().size();
    }

    private Person isPersonPresentInDb(String firstName, String lastName) {
        List<Person> personList = this.getPersons();
        for (Person person : personList) {
            if (person.getFirstName().toLowerCase().equals(firstName.toLowerCase()) &&
                    person.getLastName().toLowerCase().equals(lastName.toLowerCase())) {
                return person;
            }
        }
        return null;
    }

    private boolean isIdPresentInDb(Long id) {
        AccelaLogger.printInfo("Checking if any person with id " + id + " is present in DB");
        return personDao.findPersonById(id) != null;
    }

    private boolean getConfirmation() {
        System.out.println("Is this correct? (y/n)");
        switch (input.next().toLowerCase()) {
            case "y":
                return true;
            case "n":
                return false;
            default:
                AccelaLogger.printWarn("Please enter either 'y' or 'n' to answer");
                return getConfirmation();
        }
    }

}
