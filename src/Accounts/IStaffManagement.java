package Accounts;

import Database.InMemoryDatabase;

public interface IStaffManagement {

	/**
	 * 
	 * @param branch
	 * @param role
	 * @param gender
	 * @param age
	 */
	//void displayStaffList(String branch, char role, char gender, int age, InMemoryDatabase db);
	public void displayStaffListAdmin();
}