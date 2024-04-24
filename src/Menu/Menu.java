package Menu;

import java.io.*;
import java.util.*;

public class Menu implements Serializable{
	private ArrayList<MenuItem> menu;
	private MenuEditor editMenu;
	private String branchName;
	private transient Scanner sc;
	
	public Menu(String branchName) {
		this.menu = new ArrayList<MenuItem>();
		this.editMenu = new MenuEditor(this.menu);
	}
	
	//TODO REFACTOR MENU, editMenu already has the a reference to menu. No need to return values back and forth. NEED TO ADD addITEMS TO UML ALSO.
	public boolean addItems() throws IOException {		
		sc = new Scanner(System.in);
		System.out.print("Enter the number of items you would like to add: ");
	    int numberToAdd = sc.nextInt();
		sc.nextLine();

		for (int i = 0; i < numberToAdd; i++) {
			(this.editMenu).addItems(this.branchName);
		}
		return true;
	}

	public boolean addItems(MenuItem menuItem){		
		// Checks if any current menu items have the same itemID or same name (case insensitive).
		for (MenuItem currentItem : this.menu){
			if (currentItem.getItemID() == menuItem.getItemID() || currentItem.getItemName().equalsIgnoreCase(menuItem.getItemName())){
				System.out.println("Duplicate item found in menu.");
				return false;
			}
		}
		// Debug
		/* if (currentItem.getItemID() == menuItem.getItemID()) {System.out.println("fail1");return false;}
		if (currentItem.getItemName().equals(menuItem.getItemName())) { System.out.println("fail2");return false;}*/
		return this.menu.add(menuItem);
	}
	
	public void deleteItems() throws IOException {
		sc = new Scanner(System.in);
		System.out.println("How many menu items would you like to delete?");
	    int numberToDelete = sc.nextInt();
		sc.nextLine();
		
		for (int i=0; i < numberToDelete; i++) {
			this.editMenu.deleteItems();
		}
	}

	public void updateName() throws IOException{
		sc = new Scanner(System.in);
		System.out.print("Enter ID of item: ");
		int itemId = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter new Name: ");
		String newName = sc.nextLine();

		editMenu.updateName(itemId, newName, menu);
	}

	public void updatePrice() throws IOException{
		sc = new Scanner(System.in);
		System.out.print("Enter ID of Item: ");
		int itemId = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter new Price: ");
		float newprice = sc.nextFloat();
		sc.nextLine();

		editMenu.updatePrice(itemId, newprice, menu);
	}

	public ArrayList<MenuItem> getMenuItemsList() {
        return menu;
    }

	public void displayMenu(){
		MenuDisplay display = new MenuDisplay();
		display.displayMenu();
	}

	public void setBranchName(String branchName){
		this.branchName = branchName;
	}

	public String getBranchName(){
		return this.branchName;
	}
}
