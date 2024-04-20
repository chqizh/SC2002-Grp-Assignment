package Accounts;

import Database.InMemoryDatabase;

public interface IAdminManagement {
	public void addStaff(String name, String staffID, char gender, int age, String branchID, InMemoryDatabase db);

	public void removeStaff(String staffID, InMemoryDatabase db);
	
	public void editStaff(String staffID, InMemoryDatabase db);

	public void assignManager(String staffID, String branchName, InMemoryDatabase db);

	public void transferEmployee(String staffID, String branchName, InMemoryDatabase db);

	public void addPaymentMethod(String branchName, InMemoryDatabase db);

	public void removePaymentMethod(String branchName, InMemoryDatabase db);

	public void addBranch(String branchName, String branchLocation, int staffQuota, InMemoryDatabase db);

	public void removeBranch(String branchName, InMemoryDatabase db);

}