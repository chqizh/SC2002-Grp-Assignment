package Branch;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import DataPersistence.SerializationUtil;

public class Branches implements Serializable{
    // Create a HashMap with keys and values (branchID, branch)
    // Basically like a dictionary in python.
    private static HashMap<Integer, Branch> branchesList = new HashMap<Integer, Branch>();

    public Branches() throws IOException{
        try{
            branchesList=(HashMap<Integer, Branch>) SerializationUtil.deserialize("Branches.ser");
            //System.out.println("Menu deserialized successfully.");
        }
        catch( IOException | ClassNotFoundException e ){
            //System.err.println("Deserialization failed: " + e.getMessage());
            branchesList = new HashMap<Integer, Branch>();
        }
    }

    public void setBranchesList(HashMap<Integer, Branch> branchesList) {
        Branches.branchesList = branchesList;
    }

    public HashMap<Integer, Branch> getBranchesList() {
        return branchesList;
    }

    public Branch getSpecificBranch(int branchID){
        return branchesList.get(branchID);
    }

    public boolean insertBranch(int branchID, Branch branch) throws IOException{
        boolean flag = false;
        if (branchesList.put(branch.getbranchID(), branch) == null){
            flag = true;
        }
        SerializationUtil.serialize(branchesList, "Branches.ser");
        return flag;
    }

    public boolean closeBranch (int branchID) throws IOException{
        boolean flag = true;
        if (branchesList.remove(branchID)==null){
            flag = false;
        }
        SerializationUtil.serialize(branchesList, "Branches.ser");
        return flag;
    }
}
