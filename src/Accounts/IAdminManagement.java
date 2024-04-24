package Accounts;

/**
 * Interface defining administrative management capabilities.
 * Methods in this interface should be implemented to manage staff,
 * branch managers, branches, and payment methods in an administrative context.
 */
public interface IAdminManagement {
    
    /**
     * Adds a new staff member to the system.
     *
     * @return true if the staff member was successfully added, false otherwise.
     */
    public boolean addStaff();

    /**
     * Removes an existing staff member from the system.
     *
     * @return true if the staff member was successfully removed, false otherwise.
     */
    public boolean removeStaff();
    
    /**
     * Edits the details of an existing staff member.
     *
     * @return true if the staff member's details were successfully edited, false otherwise.
     */
    public boolean editStaff();

    /**
     * Assigns a manager to a branch.
     *
     * @return true if the manager was successfully assigned, false otherwise.
     */
    public boolean addManager();

    /**
     * Promotes a staff member to the role of branch manager.
     *
     * @return true if the staff member was successfully promoted, false otherwise.
     */
    public boolean promoteStaff();

    /**
     * Transfers an employee from one branch to another.
     *
     * @return true if the employee was successfully transferred, false otherwise.
     */
    public boolean transferEmployee();

    /**
     * Edits the payment methods available in the system.
     *
     * @return true if the payment method was successfully edited, false otherwise.
     */
    public boolean editPaymentMethod();
    
    /**
     * Adds a new branch to the system.
     *
     * @return true if the branch was successfully added, false otherwise.
     */
    public boolean addBranch();

    /**
     * Removes an existing branch from the system.
     *
     * @return true if the branch was successfully removed, false otherwise.
     */
    public boolean removeBranch();
}
