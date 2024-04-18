package Database;

import Accounts.*;
import Branch.*;
import Customer.*;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class InMemoryDatabase implements Serializable{
    private List<Account> accountTable;
    private List<Branch> branchTable;

    public InMemoryDatabase() {
        this.accountTable = new ArrayList<>();
        this.branchTable = new ArrayList<>();

    }

    public void addAccount(Account account) {
        this.accountTable.add(account);
    }

    public void addBranch(Branch branch) {
        this.branchTable.add(branch);
    }


    // To validate account
    public Account validateLogin(String staffID, String password) {
        for (Account account : accountTable) {
            if (account.validateLogin(staffID, password)) {
                return account; // Login successful
            }
        }
        return null; // Login failed
    }

    public List<Branch> getBranchTable() {
        return branchTable;
    }

}