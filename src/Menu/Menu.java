package Menu;

import java.io.*;
import java.util.*;

public class Menu implements Serializable{
	private ArrayList<MenuItem> menu;
	private EditMenu editMenu;
	private String branchName;
	private transient Scanner sc;
	
	public Menu(String branchName) {
		this.menu = new ArrayList<MenuItem>();
		this.editMenu = new EditMenu(this.menu, this.branchName);

	}
	
	public boolean addItems() throws IOException {		
		sc = new Scanner(System.in);
		System.out.print("Enter the number of items you would like to add: ");
	    int numberToAdd = sc.nextInt();
		sc.nextLine();

		for (int i = 0; i < numberToAdd; i++) {
			MenuItem newItem = this.editMenu.addItems();
			if (this.addItems(newItem) == false) System.out.println("Failed to add " + newItem.getItemName());
		}
		return true;
	}

	public boolean addItems(MenuItem menuItem){		
		for (MenuItem currentItem : this.menu){
			if (currentItem.getItemID() == menuItem.getItemID() || currentItem.getItemName().equals(menuItem.getItemName())){
				return false;
			}
		}
		//TODO
			/* if (currentItem.getItemID() == menuItem.getItemID()) {System.out.println("fail1");return false;}
			if (currentItem.getItemName().equals(menuItem.getItemName())) { System.out.println("fail2");return false;}*/
		return this.menu.add(menuItem);
	}
	
	public void deleteItems() throws IOException {
		sc = new Scanner(System.in);
		System.out.println("How many menu items would you like to delete?");
	    int numberToDelete = sc.nextInt();
		sc.nextLine();
		
		for (int i=0; i<numberToDelete; i++) {
			menu = editMenu.deleteItems(menu);
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
