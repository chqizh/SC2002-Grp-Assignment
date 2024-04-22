package Database;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.List;

import Accounts.*;

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
                String branchID = (data.length > 5 && !data[5].trim().isEmpty()) ? data[5].trim() : null;

                switch (role) {
                    case "S":
                        Staff staff = new Staff(name, staffID, gender, age, branchID, db);
                        db.addStaff(staff);
                        staffCount++;
                        break;
                    case "M":
                        BranchManager manager = new BranchManager(name, staffID, gender, age, branchID, db);
                        db.addBranchManager(manager);
                        managerCount++;
                        break;
                    case "A":
                        Admin admin = new Admin(name, staffID, gender, age, db);
                        db.addAdmin(admin);
                        adminCount++;
                        break;
                    default:
                        System.out.println("Unknown role: " + role);
                        break;
                }

                Account account = new Account(staffID);
                db.addAccount(account);
            }

            System.out.println("Added " + staffCount + " Staff members, " + managerCount + " Managers, and " + adminCount + " Admins.");
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
