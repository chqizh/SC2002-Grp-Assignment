package Menu;

import java.io.*;
import java.util.*;

public class Menu {
	
	MenuItems menu[] = new MenuItems[99];
	File menuFile = new File("Menu.txt");
	int menuLength;
	int numberOfDeletions;
	
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
				
		this.numberOfDeletions = 0;
	}
	
	public void addItems() {
		System.out.println("You have chosen to add items to the menu.");
		
		MenuDisplay display = new MenuDisplay();
		display.displayFullMenu(this);	
		
		System.out.printf("The menu length is %d\n", this.menuLength);
	
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
		
		//get the maxId of all menu items
		int maxId = 0;
		for (int i = 0; i<this.menuLength; i++) {
			if (Integer.valueOf(this.menu[i].id) > maxId) {
				maxId = Integer.valueOf(this.menu[i].id);
			}
		}
		
		Scanner sc = new Scanner(System.in);
		System.out.println("How many menu items would you like to add?");
	    	int numberToAdd = sc.nextInt();
		
	        //add items to menu array and to menuFile
		for (int i=0; i<numberToAdd; i++) {
			MenuItems item = new MenuItems();
 			this.menu[this.menuLength+i] = item;
 			//prevent id clash
			//int id = maxId + i + 1 + this.numberOfDeletions;
			int id = maxId + i + 1;
			this.menu[this.menuLength+i].id = Integer.toString(id);
			//so the position in array could change after any deletion but the id won't change
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
		
		Scanner sc1 = new Scanner(System.in);
		System.out.println("How many menu items would you like to delete?");
	    	int numberToDelete = sc1.nextInt();
		
	    	//create idArray
		int[] idArray = new int[99];
		//initialise idArray by matching values with menu items
		for (int n=0; n<this.menuLength; n++) {
			idArray[n] = Integer.valueOf(this.menu[n].id); 
		}
		
		for (int i=0; i<numberToDelete; i++) {
						
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter the id of the item you would like to delete");
		    	int id = sc.nextInt();
			
		        //find the index of the item in this.menu
			int match = 0;
			for (int k=0; k<this.menuLength; k++) {
				if (id==idArray[k]) {
					match = k;
					break;
				}
			}
			
			//shifting algorithm for deletion of items
			int shiftNumber = this.menuLength - match;
			for (int p = match; p<=shiftNumber; p++) {
				this.menu[p] = this.menu[p+1];
				idArray[p] = idArray[p+1];
			}
			
			this.menuLength--;
			this.numberOfDeletions++;			
		}
		
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
		
		display.displayFullMenu(this);	
	}
	
	public void updateName() {
		System.out.println("You have chosen to update the name of an item.");
		
		MenuDisplay display = new MenuDisplay();
		display.displayFullMenu(this);	
		
		System.out.printf("The menu length is %d\n", this.menuLength);
	
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
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the id of the item you would like to update the name of");
	    	int id = sc.nextInt();
		
	    	//create idArray
		int[] idArray = new int[99];
		//initialise idArray by matching values with menu items
		for (int n=0; n<this.menuLength; n++) {
			idArray[n] = Integer.valueOf(this.menu[n].id); 
		}
		
		//find the index of the item in this.menu
		int match = 0;
		for (int k=0; k<this.menuLength; k++) {
			if (id==idArray[k]) {
				match = k;
				break;
			}
		}
		
		Scanner sc2 = new Scanner(System.in);
		System.out.printf("Old name is %s, please enter new name:", this.menu[match].name);
	    	String newName = sc2.nextLine();	
		
	    	//update name
		this.menu[match].name = newName;
		
		//update itemInfo
		this.menu[match].updateItemInfo();
		
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
		
		display.displayFullMenu(this);	
	}
	
	public void updatePrice() {
		System.out.println("You have chosen to update the price of an item.");
		
		MenuDisplay display = new MenuDisplay();
		display.displayFullMenu(this);	
		
		System.out.printf("The menu length is %d\n", this.menuLength);
	
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
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the id of the item you would like to update the price of");
	    	int id = sc.nextInt();

	    	//create idArray
		int[] idArray = new int[99];
		//initialise idArray by matching values with menu items
		for (int n=0; n<this.menuLength; n++) {
			idArray[n] = Integer.valueOf(this.menu[n].id); 
		}
		
		//find the index of the item in this.menu
		int match = 0;
		for (int k=0; k<this.menuLength; k++) {
			if (id==idArray[k]) {
				match = k;
				break;
			}
		}
		
		Scanner sc2 = new Scanner(System.in);
		System.out.printf("Old price is %s, please enter new price:", this.menu[match].price);
	    	String newPrice = sc2.nextLine();
		
	    	//update price
		this.menu[match].price = newPrice;
		
		//update itemInfo
		this.menu[match].updateItemInfo();
		
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
		
		display.displayFullMenu(this);		
	}
	
	public void updateBranch() {
		System.out.println("You have chosen to update the branch of an item.");
		
		MenuDisplay display = new MenuDisplay();
		System.out.println("This is the current menu:");
		display.displayFullMenu(this);	
		
		System.out.printf("The menu length is %d\n", this.menuLength);
	
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
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the id of the item you would like to update the branch of");
	    	int id = sc.nextInt();

	    	//create idArray
		int[] idArray = new int[99];
		//initialise idArray by matching values with menu items
		for (int n=0; n<this.menuLength; n++) {
			idArray[n] = Integer.valueOf(this.menu[n].id); 
		}
		
		//find the index of the item in this.menu
		int match = 0;
		for (int k=0; k<this.menuLength; k++) {
			if (id==idArray[k]) {
				match = k;
				break;
			}
		}
		
		Scanner sc2 = new Scanner(System.in);
		System.out.printf("Old branch is %s, please enter new branch:", this.menu[match].branch);
	    	String newBranch = sc2.nextLine();
		
	    	//update branch
		this.menu[match].branch = newBranch;
		
		//update itemInfo
		this.menu[match].updateItemInfo();
		
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
		
		display.displayFullMenu(this);			
	}
	
	public void updateCategory() {
		System.out.println("You have chosen to update the category of an item.");
		
		MenuDisplay display = new MenuDisplay();
		System.out.println("This is the current menu:");
		display.displayFullMenu(this);	
		
		System.out.printf("The menu length is %d\n", this.menuLength);
	
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
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the id of the item you would like to update the category of");
	    	int id = sc.nextInt();

	    	//create idArray
		int[] idArray = new int[99];
		//initialise idArray by matching values with menu items
		for (int n=0; n<this.menuLength; n++) {
			idArray[n] = Integer.valueOf(this.menu[n].id); 
		}
		
		//find the index of the item in this.menu
		int match = 0;
		for (int k=0; k<this.menuLength; k++) {
			if (id==idArray[k]) {
				match = k;
				break;
			}
		}
		
		Scanner sc2 = new Scanner(System.in);
		System.out.printf("Old category is %s, please enter new category:", this.menu[match].category);
	    	String newCategory = sc2.nextLine();
		
	    	//update category
		this.menu[match].category = newCategory;

		//update itemInfo
		this.menu[match].updateItemInfo();
		
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
		
		display.displayFullMenu(this);		
	}
	
	public void updateAvailability() {
		System.out.println("You have chosen to update the availability of an item.");
		
		MenuDisplay display = new MenuDisplay();
		System.out.println("This is the current menu:");
		display.displayFullMenu(this);	
		
		System.out.printf("The menu length is %d\n", this.menuLength);
	
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
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the id of the item you would like to update the availability of");
	    	int id = sc.nextInt();

	    	//create idArray
		int[] idArray = new int[99];
		//initialise idArray by matching values with menu items
		for (int n=0; n<this.menuLength; n++) {
			idArray[n] = Integer.valueOf(this.menu[n].id); 
		}
		
		//find the index of the item in this.menu
		int match = 0;
		for (int k=0; k<this.menuLength; k++) {
			if (id==idArray[k]) {
				match = k;
				break;
			}
		}
		
		Scanner sc2 = new Scanner(System.in);
		System.out.printf("Old availability is %s, please enter new availability:", this.menu[match].availability);
	    	String newAvailability = sc2.nextLine();
		
	    	//update availability
		this.menu[match].availability = newAvailability;
		
		//update itemInfo
		this.menu[match].updateItemInfo();
		
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
		
		display.displayFullMenu(this);	
	}
}
