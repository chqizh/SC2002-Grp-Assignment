import java.lang.System;

public class FOMSApp{
    // Declaring ANSI_RESET so that we can reset the color 
    public static final String ANSI_RESET = "\u001B[0m"; 
    // Declaring the color 
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {
        System.out.println(ANSI_CYAN + "Menu:" + ANSI_RESET);

        return;
    }
}