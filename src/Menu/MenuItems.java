package menuitems;

import java.io.*;
import java.util.*;

public class MenuItems {
	
	public String name;
	public String price;
	public String branch;
	public String category;
	public String itemInfo;
	public int id;
	
	public MenuItems() {
	    System.out.println("Here we create a menu item");
	    createMenuItem();
	}
	
	public MenuItems(String name, String price, String branch, String category) {
	    this.name = name;
	    this.price = price;
	    this.branch = branch;
	    this.category = category;
	    this.itemInfo = String.format(",%s,%s,%s,%s\n", this.name, this.price, this.branch, this.category);
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
		
		sc.close();
		
		this.itemInfo = String.format(",%s,%s,%s,%s\n", this.name, this.price, this.branch, this.category);
	}	
}
