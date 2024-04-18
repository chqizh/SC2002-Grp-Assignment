package serializationtest;

import java.lang.System;
import java.util.Scanner;
import java.io.*;


public class FOMSApp implements Serializable{
    private static final String DATA_STORE = "data_store.ser";
    private Scanner sc;
    private InMemoryDatabase db;

    public FOMSApp() {
        // Initialize scanner here to prevent resource leak
        sc = new Scanner(System.in);
        // Deserialization
        try {
            db = (InMemoryDatabase) SerializationUtil.deserialize(DATA_STORE);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace(); 
            db = new InMemoryDatabase(); // Create a new one if no data is found
        }
        
    }

    public InMemoryDatabase getDB(){
        return db;
    }

    public static void main(String[] args) {
        FOMSApp app = new FOMSApp();
        app.run();
        app.sc.close();
        System.out.printf("%d", app.getDB().getTestTable());
    }

    public void run() {
        // Serialization
        try {
            SerializationUtil.serialize(db, DATA_STORE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
