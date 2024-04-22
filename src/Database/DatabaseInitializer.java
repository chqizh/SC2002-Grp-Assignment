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
    try {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        // Skip the first line if it contains headers, by starting from index 1
        for (int i = 1; i < lines.size(); i++) {
            String line = lines.get(i);
            String[] data = line.split(","); // Assuming CSV file
            String name = data[0];
            String staffID = data[1];
            String role = data[2]; 
            char gender = data[3].charAt(0);
            int age = Integer.parseInt(data[4]);
            String branchID = data[5];

            switch (role) {
                case "S":
                    Staff staff = new Staff(name, staffID, gender, age, branchID,db);
                    db.addStaff(staff);
                    System.out.println("added Staff");
                    break;
                case "M":
                    BranchManager manager = new BranchManager(name, staffID, gender, age, branchID,db);
                    db.addBranchManager(manager);
                    System.out.println("added Manager");
                    break;
                case "A":
                    Admin admin = new Admin(name, staffID, gender, age,db);
                    db.addAdmin(admin);
                    System.out.println("added Admin");

                    break;
                default:
                    System.out.println("Unknown role: " + role);
                    break;
            }

            Account account = new Account(staffID);
            db.addAccount(account);
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}
}