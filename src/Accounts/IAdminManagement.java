package Accounts;

import Database.InMemoryDatabase;

public interface IAdminManagement {
	public void addStaff(String name, String staffID, char gender, int age, String branchID,InMemoryDatabase db);

	public void removeStaff(String staffID);
	
	public void editStaff(String staffID);

	public void assignManager(String staffID, String branchName);

	public void transferEmployee(String staffID, String branchName);

	public void addPaymentMethod(String branchName);

	public void removePaymentMethod(String branchName);

	public void addBranch();

	public void removeBranch(String branchName);

}