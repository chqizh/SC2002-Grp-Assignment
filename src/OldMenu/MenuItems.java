package OldMenu;

import java.io.*;
import java.util.*;

public class MenuItems {
	
	public String name=null;
	public String price=null;
	public String branch=null;
	public String category=null;
	public String availability=null;
	public String itemInfo=null;
	public String id = null;
	
	public MenuItems() {
	    System.out.println("Here we create a menu item");
	    createMenuItem();
	}
	
	public MenuItems(String id, String name, String price, String branch, String category, String availability) {
	    this.id = id;
	    this.name = name;
	    this.price = price;
	    this.branch = branch;
	    this.category = category;
	    this.availability = availability;
	    this.itemInfo = String.format(",%s,%s,%s,%s,%s\n", this.name, this.price, this.branch, this.category, this.availability);
	}

	//this constructor is necessary for initialising the object from the text file
	public MenuItems(String[] menuItemInfoList) {
	    this.id = menuItemInfoList[0];
	    this.name = menuItemInfoList[1];
	    this.price = menuItemInfoList[2];
	    this.branch = menuItemInfoList[3];
	    this.category = menuItemInfoList[4];
	    this.availability = menuItemInfoList[5];
	    this.itemInfo = String.format(",%s,%s,%s,%s,%s\n", this.name, this.price, this.branch, this.category, this.availability);
	}
	
	public void createMenuItem() {
		Scanner sc = new Scanner(System.in);

	    	System.out.println("Enter menu item name:");
	    	String n = sc.nextLine();
		this.name = n;
		
		System.out.println("Enter menu item price:");
	    	String p = sc.nextLine();
		this.price = p;
		
		System.out.println("Enter menu item branch:");
	    	String b = sc.nextLine();
		this.branch = b;
		
		System.out.println("Enter menu item category");
	    	String c = sc.nextLine();
		this.category = c;
		
		System.out.println("Enter menu item availability");
	    	String a = sc.nextLine();
		this.availability = a;
		
		this.itemInfo = String.format(",%s,%s,%s,%s,%s\n", this.name, this.price, this.branch, this.category, this.availability);
	}	

	//must remember to update itemInfo of the object when writing to text file
	public void updateItemInfo() {
		this.itemInfo = String.format(",%s,%s,%s,%s,%s\n", this.name, this.price, this.branch, this.category, this.availability);
	}
}
