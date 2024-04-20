package Accounts;

public enum UserType {
    STAFF('S'),
    BRANCH_MANAGER('M'),
    ADMIN('A');

    private final char code;

    UserType(char code) {
        this.code = code;
    }

    public char getCode() {
        return this.code;
    }

    public static UserType fromCode(char code) throws IllegalArgumentException{
        for (UserType userType : UserType.values()) {
            if (userType.getCode() == code) {
                return userType;
            }
        }
        throw new IllegalArgumentException("No matching UserType for code: " + code);
    }

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