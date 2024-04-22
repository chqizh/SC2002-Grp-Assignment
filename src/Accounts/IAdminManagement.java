package Accounts;

public interface IAdminManagement {
	public boolean addStaff();

	public boolean removeStaff();
	
	public boolean editStaff();

	public void assignManager();

	public boolean promoteStaff();

	public void transferEmployee();

	public void editPaymentMethod();
	
	public void addBranch();

	public void removeBranch();

}