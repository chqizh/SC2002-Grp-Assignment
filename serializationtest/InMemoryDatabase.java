package serializationtest;

import java.util.ArrayList;
import java.util.List;

public class InMemoryDatabase {
    private List<Integer> TestTable;

    public InMemoryDatabase() {
        this.TestTable = new ArrayList<>();
    }

    public void addTestTable() {
        this.TestTable.add(5);
    }

    public List<Integer> getTestTable() {
        return this.TestTable;
    }
}