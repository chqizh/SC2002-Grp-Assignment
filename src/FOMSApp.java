import java.lang.System;

import Accounts.*;
import Branch.*;
import Customer.*;
import Display.*;
import DataPersistence.*;

public class FOMSApp{
    // Declaring ANSI_RESET so that we can reset the color 
    public static final String ANSI_RESET = "\u001B[0m"; 
    // Declaring the color 
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {
        // Instantiating Static Objects
        Branches branches = new Branches();
        

        System.out.println(ANSI_CYAN + "Menu:" + ANSI_RESET);

        return;
    }
}