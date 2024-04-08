package Branch;

import java.util.HashMap;

public class Branches{
    // Create a HashMap with keys and values (branchID, branch)
    // Basically like a dictionary in python.
    private static HashMap<Integer, Branch> branchesList = new HashMap<Integer, Branch>();

    public void setBranchesList(HashMap<Integer, Branch> branchesList) {
        Branches.branchesList = branchesList;
    }

    public HashMap<Integer, Branch> getBranchesList() {
        return branchesList;
    }

    public boolean insertBranch(int branchID, Branch branch){
        if (branchesList.put(branchID, branch) == null) return true;
        else return false;
    }

    public Branch getSpecificBranch(int branchID){
        return branchesList.get(branchID);
    }
}
