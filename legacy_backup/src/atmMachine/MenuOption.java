package atmMachine;

/**
 * The menu options for the ATM - restricted to 4 choices and removes invalid user input.
 * Each option has a value associated with it which is used to return the corresponding
 * enum value, from user input via scanner, which then calls the corresponding method.
 *
 * @author davidkirkpatrick
 */
public enum MenuOption {

    CHECK_BALANCE(1),
    DEPOSIT(2),
    WITHDRAW(3),
    EXIT(4);

    private final int value;

    MenuOption(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * Returns the enum value corresponding to the user input by iterating through
     * enum values and the assigned value/option
     *
     * @param value - user input from scanner from getValue()
     * @return - enum value corresponding to user input
     */
    public static MenuOption getOption(int value) {
        for (MenuOption option : MenuOption.values()) {
            if (option.getValue() == value) {
                return option;
            }
        }

        return null;
    }

}
