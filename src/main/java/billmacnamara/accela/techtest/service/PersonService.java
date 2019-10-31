package billmacnamara.accela.techtest.service;

import billmacnamara.accela.techtest.model.Person;

import java.util.List;

public interface PersonService {

    void addPerson();
    void editPerson();
    void removePerson();
    List<Person> getPersons();
    int getPersonCount();

}
