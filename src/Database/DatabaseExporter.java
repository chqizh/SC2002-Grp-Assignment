package Database;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;

import Accounts.*;

/**
 * Class responsible for exporting database data to CSV format.
 */
public class DatabaseExporter {
    private InMemoryDatabase db;

    /**
     * Constructs a DatabaseExporter with the specified InMemoryDatabase.
     *
     * @param db The InMemoryDatabase to export data from.
     */
    public DatabaseExporter(InMemoryDatabase db) {
        this.db = db;
    }

    /**
     * Exports database data to a CSV file.
     *
     * @param filePath The path to the CSV file to export data to.
     * @throws IOException If an I/O error occurs.
     */
    public void exportDataToCSV(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        
        // Headers
        sb.append("Name,Staff Login ID,Role,Gender,Age,Branch\n");

        // Export Staff
        for (Staff staff : db.getStaffMap().values()) {
            sb.append(staff.getName()).append(',')
              .append(staff.getStaffID()).append(',')
              .append("S").append(',')  // S for Staff
              .append(staff.getGender()).append(',')
              .append(staff.getAge()).append(',')
              .append(staff.getBranchName()).append('\n');
        }

        // Export Managers
        for (BranchManager manager : db.getBranchManagerMap().values()) {
            sb.append(manager.getName()).append(',')
              .append(manager.getStaffID()).append(',')
              .append("M").append(',')  // M for Manager
              .append(manager.getGender()).append(',')
              .append(manager.getAge()).append(',')
              .append(manager.getBranchName()).append('\n');
        }

        // Export Admins
        for (Admin admin : db.getAdminMap().values()) {
            sb.append(admin.getName()).append(',')
              .append(admin.getStaffID()).append(',')
              .append("A").append(',')  // A for Admin
              .append(admin.getGender()).append(',')
              .append(admin.getAge()).append(',')
              .append("")  // Admins might not have a branch
              .append('\n');
        }

        lines.add(sb.toString());
        Files.write(Paths.get(filePath), lines);
    }
}
