package Accounts;

public enum UserType {
    /**
     * Represents a staff member with code 'S'.
     */
    STAFF('S'),

    /**
     * Represents a branch manager with code 'M'.
     */
    BRANCH_MANAGER('M'),

    /**
     * Represents an administrator with code 'A'.
     */
    ADMIN('A');

    // The character code associated with the UserType.
    private final char code;

    /**
     * Constructs a UserType with the specified code.
     *
     * @param code The character code that represents the user type.
     */
    UserType(char code) {
        this.code = code;
    }

    /**
     * Returns the code associated with this UserType.
     *
     * @return The character code of this UserType.
     */
    public char getCode() {
        return this.code;
    }

    /**
     * Returns the UserType associated with the specified code.
     *
     * @param code The character code to be matched with a UserType.
     * @return The UserType corresponding to the given code.
     * @throws IllegalArgumentException if the given code does not match any UserType.
     */
    public static UserType fromCode(char code) throws IllegalArgumentException{
        for (UserType userType : UserType.values()) {
            if (userType.getCode() == code) {
                return userType;
            }
        }
        throw new IllegalArgumentException("No matching UserType for code: " + code);
    }

    /**
     * Returns a string representation of this UserType.
     * <p>
     * The method translates the user type code to a descriptive string such as "Staff",
     * "Branch Manager", or "Admin". If the code does not match any known user type, it returns
     * "Role not found.".
     *
     * @return A string description of this UserType.
     */
    public String stringFromUserType(){
        switch (this.code) {
            case 'S':
                return "Staff";
            case 'M':
                return "Branch Manager";
            case 'A':
                return "Admin";
            default:
                return "Role not found.";
        }
    }
}