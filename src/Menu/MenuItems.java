package Menu;

import java.io.*;
import java.util.*;

import DataPersistence.SerializationUtil;

public class MenuItems implements Serializable{
	//private static final long serialVersionUID = 1L;
	ArrayList<MenuItem> menu;
	EditMenu editmenu = new EditMenu(menu);
	
	public MenuItems() {
		menu = new ArrayList<>();
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
	
	public void addItems() throws IOException {		
		Scanner sc = new Scanner(System.in);
		System.out.println("How many menu items would you like to add?");
	    int numberToAdd = this.getIntegerFromUser();

		for (int i=0; i<numberToAdd; i++) {
			MenuItem newItem = editmenu.addItems();
			menu.add(newItem);
		}
		//SerializationUtil.serialize(menu, "menu.ser");
	}

	
	public void deleteItems() throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("How many menu items would you like to delete?");
	    int numberToDelete = this.getIntegerFromUser();
		
		for (int i=0; i<numberToDelete; i++) {
			menu = editmenu.deleteItems(menu);
		}
		//SerializationUtil.serialize(menu, "menu.ser");	
	}

	public void updateName() throws IOException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter ID of item:");
		int itemId = this.getIntegerFromUser();
		sc.nextLine();
		System.out.println("Enter new Name:");
		String newName = sc.nextLine();

		editmenu.updateName(itemId, newName, menu);
		//SerializationUtil.serialize(menu, "menu.ser");
	}

	public void updatePrice() throws IOException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter ID of Item:");
		int itemId = this.getIntegerFromUser();
		sc.nextLine();
		System.out.println("Enter new Price:");
		float newprice = sc.nextFloat();
		sc.nextLine();

		editmenu.updatePrice(itemId, newprice, menu);
		//SerializationUtil.serialize(menu, "menu.ser");
	}

	public ArrayList<MenuItem> getMenuItems() {
        return menu;
    }

	public void displayMenu(){
		MenuDisplay display = new MenuDisplay();
		display.displayMenu();
	}
}
