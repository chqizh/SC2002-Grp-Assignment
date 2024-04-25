/* package OldMenu;

import java.io.*;
import java.util.*;

public class Menu {
	
	MenuItems menu[] = new MenuItems[99];
	File menuFile = new File("Menu.txt");
	int menuLength;
	int[] idArray = new int[99];
	
	public Menu() {
		//initialise this.menuLength with BufferedReader
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.menuFile));
			this.menuLength = 0;
			while (reader.readLine() != null) {
				this.menuLength++;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	public void initialiseMenuFromFile() {
		//initialise menu[] with BufferedReader 
		try {
			BufferedReader reader = new BufferedReader(new FileReader(this.menuFile));
			String line;
			String[] allLines = new String[this.menuLength];
			int i = 0;
			MenuItems items[] = new MenuItems[this.menuLength];
			while ((line = reader.readLine()) != null) {
				allLines[i] = line;
				i++;
			}
			for (int j = 0; j<this.menuLength; j++) {
				String[] menuItemInfoList = allLines[j].split(",");
				items[j] = new MenuItems(menuItemInfoList);
				this.menu[j] = items[j];
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		//initialise idArray by matching values with menu items
		for (int n=0; n<this.menuLength; n++) {
			idArray[n] = Integer.valueOf(this.menu[n].id); 
		}
	}
	
	public int getIntegerFromUser() {
		Scanner sc = new Scanner(System.in);
		int userInput = 0;
		
		boolean integerUserInput = false;
		while (!integerUserInput) {
		    try {
		    	userInput = Integer.parseInt(sc.nextLine());
		    	integerUserInput = true;
		    } catch (NumberFormatException e) {
		    	System.out.println("Error! Please enter a number. Try again.");
		    }
		}		
		
		return userInput;
	}
	
	public int getIdFromUserAndMatchWithIdFromMenu() {
		Scanner sc = new Scanner(System.in);
	    	//int id = sc.nextInt();
		int id = 0;
		
		//error checking for user input
		boolean validId = false;
		while (!validId) {
			boolean integerId = false;
			while (!integerId) {
			    try {
			    	id = Integer.parseInt(sc.nextLine());
			    	integerId = true;
			    } catch (NumberFormatException e) {
			    	System.out.println("Error! Invalid id. Try again.");
			    }
			}
			
			for (int s = 0; s<this.menuLength; s++) {
				if (id == idArray[s]) {
					validId = true;
				}
			}
			
			if (!validId) {
				System.out.println("Error! Invalid id. Try again.");
			}
		}
		
		//find the index of the item in this.menu
		int match = 0;
		for (int k=0; k<this.menuLength; k++) {
			if (id==idArray[k]) {
				match = k;
				break;
			}
		}
		
		return match;
	}
	
	public void writeToFile() {
		//empty this.menuFile
		try {
			BufferedWriter deleter = new BufferedWriter(new FileWriter(this.menuFile));
			deleter.write("");
			deleter.close();	
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//write to this.menuFile the new menu items
		for (int q = 0; q<this.menuLength; q++) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(this.menuFile,true));
				writer.write(this.menu[q].id);
				writer.write(this.menu[q].itemInfo);
				writer.close();	
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void resetId() {
		System.out.println("You have chosen to reset the id of all menu items");
		this.initialiseMenuFromFile();
		for (int i = 0; i<this.menuLength; i++) {
			this.menu[i].id = String.valueOf(i+1);
		}
		this.writeToFile();
	}
	
	public void addItems() {
		System.out.println("You have chosen to add items to the menu.");
		
		MenuDisplay display = new MenuDisplay();
		display.displayFullMenu(this);	
		
		System.out.printf("The menu length is %d\n", this.menuLength);
		
		this.initialiseMenuFromFile();
		
		//get the maxId of all menu items
		int maxId = 0;
		for (int i = 0; i<this.menuLength; i++) {
			if (Integer.valueOf(this.menu[i].id) > maxId) {
				maxId = Integer.valueOf(this.menu[i].id);
			}
		}
		
		System.out.println("How many menu items would you like to add?");
	    	int numberToAdd = this.getIntegerFromUser();
	      
		for (int i=0; i<numberToAdd; i++) {
			MenuItems item = new MenuItems();
 			this.menu[this.menuLength+i] = item;
 			//prevent id clash
 			//so the position in array could change after any deletion but the id won't change
			int id = maxId + i + 1;
			this.menu[this.menuLength+i].id = Integer.toString(id);
			this.menuLength++;
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(this.menuFile, true));
				writer.write(Integer.toString(id));
				writer.write(item.itemInfo);
				writer.close();	
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
	
	public void deleteItems() {
		System.out.println("You have chosen to delete items from the menu.");
		
		MenuDisplay display = new MenuDisplay();
		display.displayFullMenu(this);	
		
		System.out.printf("The menu length is %d\n", this.menuLength);
		
		//error checking for empty menu
		if (this.menuLength == 0) {
			System.out.println("The menu length is zero. You have to add items first.");
			return;
		}

		this.initialiseMenuFromFile();
		
		System.out.println("How many menu items would you like to delete?");
	    	int numberToDelete = this.getIntegerFromUser();
		
		for (int i=0; i<numberToDelete; i++) {
			
			System.out.println("Please enter the id of the item you would like to delete");
			int match = getIdFromUserAndMatchWithIdFromMenu();			
		    
			//shifting algorithm for deletion of items
			int shiftNumber = this.menuLength - match;
			for (int p = match; p<=shiftNumber; p++) {
				this.menu[p] = this.menu[p+1];
				idArray[p] = idArray[p+1];
			}
			
			this.menuLength--;		
		}
		
		this.writeToFile();
		
		display.displayFullMenu(this);	
	}
	
	public void updateName() {
		System.out.println("You have chosen to update the name of an item.");
		
		MenuDisplay display = new MenuDisplay();
		display.displayFullMenu(this);	
		
		System.out.printf("The menu length is %d\n", this.menuLength);
		
		//error checking for empty menu
		if (this.menuLength == 0) {
			System.out.println("The menu length is zero. You have to add items first.");
			return;
		}

		this.initialiseMenuFromFile();
		
		System.out.println("Please enter the id of the item you would like to update the name of");
		int match = getIdFromUserAndMatchWithIdFromMenu();
		
		Scanner sc2 = new Scanner(System.in);
		System.out.printf("Old name is %s, please enter new name:", this.menu[match].name);
	    	String newName = sc2.nextLine();	
		
	    	//update name
		this.menu[match].name = newName;
		
		//update itemInfo
		this.menu[match].updateItemInfo();
		
		this.writeToFile();
		
		display.displayFullMenu(this);	
	}
	
	public void updatePrice() {
		System.out.println("You have chosen to update the price of an item.");
		
		MenuDisplay display = new MenuDisplay();
		display.displayFullMenu(this);	
		
		System.out.printf("The menu length is %d\n", this.menuLength);

		//error checking for empty menu
		if (this.menuLength == 0) {
			System.out.println("The menu length is zero. You have to add items first.");
			return;
		}

		this.initialiseMenuFromFile();

		System.out.println("Please enter the id of the item you would like to update the price of");
		int match = getIdFromUserAndMatchWithIdFromMenu();
		
		Scanner sc2 = new Scanner(System.in);
		System.out.printf("Old price is %s, please enter new price:", this.menu[match].price);
	    	String newPrice = sc2.nextLine();
		
	    	//update price
		this.menu[match].price = newPrice;
		
		//update itemInfo
		this.menu[match].updateItemInfo();
		
		this.writeToFile();
		
		display.displayFullMenu(this);		
	}
	
	public void updateBranch() {
		System.out.println("You have chosen to update the branch of an item.");
		
		MenuDisplay display = new MenuDisplay();
		display.displayFullMenu(this);	
		
		System.out.printf("The menu length is %d\n", this.menuLength);

		//error checking for empty menu
		if (this.menuLength == 0) {
			System.out.println("The menu length is zero. You have to add items first.");
			return;
		}

		this.initialiseMenuFromFile();
		
		System.out.println("Please enter the id of the item you would like to update the branch of");
		int match = getIdFromUserAndMatchWithIdFromMenu();
		
		Scanner sc2 = new Scanner(System.in);
		System.out.printf("Old branch is %s, please enter new branch:", this.menu[match].branch);
	    	String newBranch = sc2.nextLine();
		
	    	//update branch
		this.menu[match].branch = newBranch;
		
		//update itemInfo
		this.menu[match].updateItemInfo();
		
		this.writeToFile();
		
		display.displayFullMenu(this);			
	}
	
	public void updateCategory() {
		System.out.println("You have chosen to update the category of an item.");
		
		MenuDisplay display = new MenuDisplay();
		display.displayFullMenu(this);	
		
		System.out.printf("The menu length is %d\n", this.menuLength);

		//error checking for empty menu
		if (this.menuLength == 0) {
			System.out.println("The menu length is zero. You have to add items first.");
			return;
		}

		this.initialiseMenuFromFile();

		System.out.println("Please enter the id of the item you would like to update the category of");
		int match = getIdFromUserAndMatchWithIdFromMenu();
		
		Scanner sc2 = new Scanner(System.in);
		System.out.printf("Old category is %s, please enter new category:", this.menu[match].category);
	    	String newCategory = sc2.nextLine();
		
	    	//update category
		this.menu[match].category = newCategory;

		//update itemInfo
		this.menu[match].updateItemInfo();
		
		this.writeToFile();
		
		display.displayFullMenu(this);		
	}
	
	public void updateAvailability() {
		System.out.println("You have chosen to update the availability of an item.");
		
		MenuDisplay display = new MenuDisplay();
		display.displayFullMenu(this);	
		
		System.out.printf("The menu length is %d\n", this.menuLength);

		//error checking for empty menu
		if (this.menuLength == 0) {
			System.out.println("The menu length is zero. You have to add items first.");
			return;
		}

		this.initialiseMenuFromFile();
		
		System.out.println("Please enter the id of the item you would like to update the availability of");
		int match = getIdFromUserAndMatchWithIdFromMenu();
		
		Scanner sc2 = new Scanner(System.in);
		System.out.printf("Old availability is %s, please enter new availability:", this.menu[match].availability);
	    	String newAvailability = sc2.nextLine();
		
	    	//update availability
		this.menu[match].availability = newAvailability;
		
		//update itemInfo
		this.menu[match].updateItemInfo();
		
		this.writeToFile();
		
		display.displayFullMenu(this);	
	}
}
 */