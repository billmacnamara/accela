package billmacnamara.accela.techtest.util;

import java.util.HashMap;
import java.util.Map;

public enum  UserOptions {
    ADD_PERSON("1", "Add Person"),
    EDIT_PERSON("2", "Edit Person"),
    REMOVE_PERSON("3", "Remove Person"),
    USER_COUNT("4", "Count Number of Persons"),
    USER_LIST("5", "List Persons"),
    EXIT("6", "Exit Program");

    private static final Map<String, UserOptions > lookup = new HashMap<String, UserOptions>();

    static {
        for (UserOptions entry : UserOptions.values()) {
            lookup.put(entry.optionId, entry);
        }
    }

    private String optionId;
    private String optionDescription;

    UserOptions(String optionId, String optionDescription) {
        this.optionId = optionId;
        this.optionDescription = optionDescription;
    }

    public String getOptionId() {return this.optionId;}
    public String getOptionDescription() {return this.optionDescription;}

    public static UserOptions getOptionFromOptionId(String optionId) {
        return lookup.get(optionId);
    }
}
