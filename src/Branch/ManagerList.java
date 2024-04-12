package Branch;
import Accounts.*;
import java.util.ArrayList;

public class ManagerList {
    private ArrayList<BranchManager> managers;

    public ManagerList() {
        this.managers = new ArrayList<>();
    }

    public void addManager(BranchManager manager) {
        managers.add(manager);
    }

    public boolean removeManager(String loginID){
        for (BranchManager manager : managers) {
            if (manager.getLoginID() == loginID) {
                managers.remove(manager);
                return true;  
            }
        }
        return false;
    }
}
