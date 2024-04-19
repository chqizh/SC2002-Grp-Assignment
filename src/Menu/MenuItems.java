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
	
	public void addItems() throws IOException {		
		Scanner sc = new Scanner(System.in);
		System.out.println("How many menu items would you like to add?");
	    int numberToAdd = sc.nextInt();

		for (int i=0; i<numberToAdd; i++) {
			
			MenuItem newItem = editmenu.addItems();
			menu.add(newItem);
		}
		SerializationUtil.serialize(menu, "menu.ser");
	}

	
	public void deleteItems() throws IOException {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("How many menu items would you like to delete?");
	    int numberToDelete = sc.nextInt();
		
		for (int i=0; i<numberToDelete; i++) {
			menu = editmenu.deleteItems(menu);
		}
		SerializationUtil.serialize(menu, "menu.ser");	
	}

	public void updateName() throws IOException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter ID of item:");
		int itemId = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter new Name:");
		String newName = sc.nextLine();

		editmenu.updateName(itemId, newName, menu);
		SerializationUtil.serialize(menu, "menu.ser");
	}

	public void updatePrice() throws IOException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter ID of Item:");
		int itemId = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter new Price:");
		float newprice = sc.nextFloat();
		sc.nextLine();

		editmenu.updatePrice(itemId, newprice, menu);
		SerializationUtil.serialize(menu, "menu.ser");
	}

	public ArrayList<MenuItem> getMenuItems() {
        return menu;
    }

	public void displayMenu(){
		MenuDisplay display = new MenuDisplay();
		display.displayMenu();
	}
}
