package Database;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import Accounts.*;
import Branch.*;
import Menu.*;


public class DatabaseInitializer {
    private InMemoryDatabase db;

    public DatabaseInitializer(InMemoryDatabase db) {
        this.db = db;
    }

    public void initializeStaffList(String filePath) {
        int staffCount = 0;
        int managerCount = 0;
        int adminCount = 0;
        
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            ArrayList<BranchManager> branchManagers = new ArrayList<>();
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                if (line.isEmpty()) continue; // Skip empty lines
                String[] data = line.split(",");
                if (data.length < 5) { // Now we check if there are at least 5 elements
                    continue;
                }
                String name = data[0].trim();
                String staffID = data[1].trim();
                String role = data[2].trim();
                char gender = data[3].trim().charAt(0);
                int age = Integer.parseInt(data[4].trim());
                String branchName = (data.length > 5 && !data[5].trim().isEmpty()) ? data[5].trim() : null;

                switch (role) {
                    case "S":
                        Staff staff = new Staff(name, staffID, gender, age, branchName, db);
                        if (db.addStaff(staff)) { 
                            staffCount++;
                            Account account = new Account(staffID);
                            db.addAccount(account);
                        }
                        break;
                    case "M":
                        BranchManager manager = new BranchManager(name, staffID, gender, age, branchName, db);
                        branchManagers.add(manager);
                        break;
                    case "A":
                        Admin admin = new Admin(name, staffID, gender, age, db);
                        if (db.addAdmin(admin)){
                            adminCount++;
                            Account account = new Account(staffID);
                            db.addAccount(account);
                        }
                        break;
                    default:
                        System.out.println("Unknown role: " + role);
                        break;
                }
            }

            for (BranchManager manager : branchManagers){
                if (db.addBranchManager(manager)){
                    managerCount++;
                    Account account = new Account(manager.getStaffID());
                    db.addAccount(account);
                }
            }

            System.out.println("Added " + staffCount + " Staff members, " + managerCount + " Managers, and " + adminCount + " Admins.");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // Function to initialize BranchList
    public void initializeBranchList(String filePath) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                if (line.isEmpty()) continue;
                String[] data = line.split(",");
                // Assuming your branch CSV has the format: Name, Location, StaffQuota
                String name = data[0].trim();
                String location = data[1].trim();
                int staffQuota = Integer.parseInt(data[2].trim());
                
                Branch branchCheck = db.getBranchByBranchName(name);
                if (branchCheck != null){
                    branchCheck.setBranchLocation(location);
                    branchCheck.setStaffQuota(staffQuota);
                }

                Branch branch = new Branch(name, location, staffQuota);
                db.addBranch(branch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initializeMenuList(String filePath) {
        int numItemsAdded = 0;
        HashSet<String> uniqueBranchesAdded = new HashSet<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            HashMap<String, Integer> branchItemIDs = new HashMap<>();
            for (int i = 1; i < lines.size(); i++) {
                String line = lines.get(i).trim();
                if (line.isEmpty()) continue;
                
                String[] data = line.split(",");
                if (data.length < 4) { // Check if there are at least 5 elements
                    continue;
                }
                //System.out.println(data[2]);
                String name = data[0].trim();
                double price = Double.parseDouble(data[1].trim());
                String branchName = data[2].trim();
                String category = data[3].trim();

                int itemID = branchItemIDs.getOrDefault(branchName, 0) + 1;
                branchItemIDs.put(branchName, itemID);
                
                MenuItem menuItem = new MenuItem(itemID, name, price, category, branchName);
                
                if (db.addMenuItem(branchName, menuItem) == true){
                    numItemsAdded++;
                    uniqueBranchesAdded.add(branchName);
                };
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.printf("Added %d Menu Items to %d Branches.\n", numItemsAdded, uniqueBranchesAdded.size());
    }
}
