package Accounts;

public class BranchManager extends Employee implements IOrderProcess, IMenuManagement, IStaffManagement {
    private String branchID;

	public void setBranchID(String branchID) {
        this.branchID = branchID;
        throw new UnsupportedOperationException();
	}

	public String getBranchID() {
		return this.branchID;
	}

}
