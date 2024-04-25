package Accounts;

/**
 * Interface for managing and displaying a list of staff members.
 */
public interface IStaffManagement {

    /**
     * Displays a list of all staff members, potentially filtered by certain criteria such as branch, role, gender, and age.
     * The specific display logic and filtering capabilities will be implemented by the class that implements this interface.
     * 
     * @param displayAll A true or false value to display all staff without filtering or not.
	 */
    public void displayStaffList(boolean displayAll);
}
