package Accounts;
import Branch.*;

public class BranchManager extends Staff implements IMenuManagement, IStaffManagement {
    private String branchID;

	public void setBranchID(String branchID) {
        this.branchID = branchID;
        throw new UnsupportedOperationException();
	}

	public String getBranchID() {
		return this.branchID;
	}

	public void displayStaffList(Staff staffList[]){
		int i=1;
		
		for (Staff staff : staffList){
			System.out.println("Staff "+ i++);
			System.out.println("Name: " + staff.getName());
			System.out.println("Role: " + staff.getUserType());
			System.out.println("Age: "+getAge());
			System.out.println("Gender: "+getGender());
		}
	}


}
