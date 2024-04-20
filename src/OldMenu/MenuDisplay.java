package OldMenu;

import java.io.*;
import java.util.*;

//We will use exception handling when customer makes orders
//If they select from different branches, we say it is an invalid order
//"Invalid order. If you would like to order from multiple branches, please make a separate order for each branch."
//We show all menu from all branches at the start so that customers have more options to choose from
public class MenuDisplay {
	
	public MenuDisplay() {}
		
	public void displayFullMenu(Menu menu) {
		System.out.println("This is the current menu:");
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
		System.out.println("End of menu");
	} 
	
	//We can also give customers the option to view just a specific branch's menu if they wish to do so
	public void displayBranchMenu(Menu menu, String branch) {
		System.out.println("This is the menu for your branch:");
		menu.initialiseMenuFromFile();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(menu.menuFile));
			String line;
			int lineCount = 0;
			while ((line = reader.readLine()) != null)  {
				if (menu.menu[lineCount].branch.equals("b")) {
					System.out.println(line);
				}
				lineCount++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("End of menu");
	} 

}
