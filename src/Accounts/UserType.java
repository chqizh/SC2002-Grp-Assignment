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

    public static UserType fromCode(char code) {
        for (UserType userType : UserType.values()) {
            if (userType.getCode() == code) {
                return userType;
            }
        }
        throw new IllegalArgumentException("No matching UserType for code: " + code);
    }
}