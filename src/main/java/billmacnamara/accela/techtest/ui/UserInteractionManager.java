package billmacnamara.accela.techtest.ui;

import billmacnamara.accela.techtest.model.Person;
import billmacnamara.accela.techtest.service.PersonService;
import billmacnamara.accela.techtest.service.PersonServiceImpl;
import billmacnamara.accela.techtest.util.AccelaLogger;
import billmacnamara.accela.techtest.util.FileLoader;
import billmacnamara.accela.techtest.util.UserOptions;

import java.io.IOException;
import java.util.EnumSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserInteractionManager {

    public static final String INVALID_INPUT = "That is not a valid option!";

    private EnumSet<UserOptions> validOptions = EnumSet.allOf(UserOptions.class);
    private PersonService personService = new PersonServiceImpl();

    private static Scanner input;

    public void run() {
        this.printGreeting();
        getInput();
        UserOptions option = UserOptions.EDIT_PERSON;
        while (option != UserOptions.EXIT) {
            option = this.getUserOption();
            this.executeUserOption(option);
        }
    }

    private void printGreeting() {
        FileLoader fileLoader = new FileLoader();

        try {
            System.out.println(fileLoader.loadFileAsResource("header.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Hello there!\n");
    }

    private UserOptions getUserOption() {
        System.out.println("Choose an option from the list below by entering its corresponding number");
        System.out.println(this.generateUserOptions());

        try {

            int optionInput = input.nextInt();
            String optionId = Integer.toString(optionInput);

            if (!validateInput(optionId)) {
                AccelaLogger.printWarn(INVALID_INPUT);
                this.getUserOption();
            }

            AccelaLogger.printInfo("You have selected option " + optionId + ": " +
                    UserOptions.getOptionFromOptionId(optionId).getOptionDescription());
            input.nextLine();
            return UserOptions.getOptionFromOptionId(optionId);

        } catch (InputMismatchException exception) {
            AccelaLogger.printWarn(INVALID_INPUT);
            input.next();
            return this.getUserOption();
        }

    }

    private String generateUserOptions() {
        StringBuilder sb = new StringBuilder();
        for (UserOptions option : this.validOptions) {
            sb.append(String.format("%1$s) %2$s\n", option.getOptionId(), option.getOptionDescription()));
        }
        return sb.toString();
    }

    private boolean validateInput(String input) {
        for (UserOptions option : this.validOptions) {
            if (input.equals(option.getOptionId())) {
                return true;
            }
        }
        return false;
    }

    private void executeUserOption(UserOptions option) {
        switch (option) {
            case ADD_PERSON:
                this.personService.addPerson();
                break;
            case EDIT_PERSON:
                this.personService.editPerson();
                break;
            case REMOVE_PERSON:
                this.personService.removePerson();
                break;
            case USER_LIST:
                List<Person> personList = this.personService.getPersons();
                AccelaLogger.printInfo("List of Users: \n");
                System.out.println("##########################################");
                for (Person person : personList) {
                    System.out.println("# " + person.toString());
                }
                System.out.println("##########################################\n");
                break;
            case USER_COUNT:
                int userCount = this.personService.getPersonCount();
                AccelaLogger.printInfo(String.format("There are currently %d known persons\n", userCount));
                break;
        }
    }

    public static Scanner getInput() {
        if (input == null) {
            input = new Scanner(System.in);
        }
        return input;
    }
}
