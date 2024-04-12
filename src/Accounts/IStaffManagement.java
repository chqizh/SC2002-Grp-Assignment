package Accounts;

public interface IStaffManagement {

	/**
	 * 
	 * @param branch
	 * @param role
	 * @param gender
	 * @param age
	 */
	//void displayStaffList(String branch, char role, char gender, int age);

	void displayStaffList(Staff staffList[]);

	void displayStaffListAdmin (Staff staffList[]);

}