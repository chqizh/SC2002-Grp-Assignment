package Menu;

import java.io.*;
import java.util.*;


public class MenuItems implements Serializable{
	private ArrayList<MenuItem> menu;
	private EditMenu editmenu = new EditMenu(menu);
	private transient Scanner sc;
	
	public MenuItems() {
		menu = new ArrayList<MenuItem>();
	}

	public int getIntegerFromUser() {
		sc = new Scanner(System.in);
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
	
	public void addItems() throws IOException {		
		sc = new Scanner(System.in);
		System.out.println("How many menu items would you like to add?");
	    int numberToAdd = this.getIntegerFromUser();

		for (int i=0; i<numberToAdd; i++) {
			MenuItem newItem = editmenu.addItems();
			menu.add(newItem);
		}
	}

	
	public void deleteItems() throws IOException {
		sc = new Scanner(System.in);
		System.out.println("How many menu items would you like to delete?");
	    int numberToDelete = this.getIntegerFromUser();
		
		for (int i=0; i<numberToDelete; i++) {
			menu = editmenu.deleteItems(menu);
		}
	}

	public void updateName() throws IOException{
		sc = new Scanner(System.in);
		System.out.println("Enter ID of item:");
		int itemId = this.getIntegerFromUser();
		sc.nextLine();
		System.out.println("Enter new Name:");
		String newName = sc.nextLine();

		editmenu.updateName(itemId, newName, menu);
	}

	public void updatePrice() throws IOException{
		sc = new Scanner(System.in);
		System.out.println("Enter ID of Item:");
		int itemId = this.getIntegerFromUser();
		sc.nextLine();
		System.out.println("Enter new Price:");
		float newprice = sc.nextFloat();
		sc.nextLine();

		editmenu.updatePrice(itemId, newprice, menu);
	}

	public ArrayList<MenuItem> getMenuItemsList() {
        return menu;
    }

	public void displayMenu(){
		MenuDisplay display = new MenuDisplay();
		display.displayMenu();
	}
}
