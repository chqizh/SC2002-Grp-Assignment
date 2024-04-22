package Accounts;

public interface IAdminManagement {
	public boolean addStaff();

	public boolean removeStaff();
	
	public boolean editStaff();

	public void assignManager();

	public void promoteStaff();

	public void transferEmployee();

	public void editPaymentMethod();

	//public void addPaymentMethod(); public void removePaymentMethod();

	public void addBranch();

	public void removeBranch();

}