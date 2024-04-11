package menuitems;

import java.io.*;
import java.util.*;

//We will use exception handling when customer makes orders
//If they select from different branches, we say it is an invalid order
//"Invalid order. If you would like to order from multiple branches, please make a separate order for each branch."
public class MenuDisplay {
	
	public MenuDisplay() {
		
	}
		
	public void displayFullMenu(Menu menu) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(menu.menuFile));
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
