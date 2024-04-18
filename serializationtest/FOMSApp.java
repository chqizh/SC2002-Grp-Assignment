package serializationtest;

import java.lang.System;
import java.util.Scanner;
import java.io.*;


public class FOMSApp{
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
            db = new InMemoryDatabase(); // Create a new one if no data is found
        }
    }

    public static void main(String[] args) {
        FOMSApp app = new FOMSApp();
        app.run();
        app.sc.close();
    }

    public void run() {
        System.out.println("Welcome to the Fastfood Ordering Management System.");
        System.out.println("Are you a customer? (Y/N)");
        boolean isCustomer = (sc.next().charAt(0) == 'Y');
        sc.nextLine(); // Consume the rest of the line
    }
}
