package Branch;
import Accounts.*;
import java.util.ArrayList;

public class ManagerList {
    private ArrayList<Manager> managers;

    public ManagerList() {
        this.managers = new ArrayList<>();
    }

    public void addManager(Manager manager) {
        managers.add(manager);
    }

    public boolean removeManager(String loginID){
        for (Manager manager : managers) {
            if (manager.getLoginID() == loginID) {
                managers.remove(manager);
                return true;  
            }
        }
        return false; 

    }
}
