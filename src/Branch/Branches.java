package Branch;

public class Branches extends Branch{
    private Branch branchesList[];

    public void setBranchesList(Branch[] branchesList) {
        this.branchesList = branchesList;
    }

    public Branch[] getBranchesList() {
        return branchesList;
    }
}
