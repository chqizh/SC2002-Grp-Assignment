package Menu;

import java.io.*;
import java.util.*;

public class MenuItems {
	
	public String name;
	public String price;
	public String branch;
	public String category;
	public String availability;
	public String itemInfo;
	public int id;
	
	public MenuItems() {
	    System.out.println("Here we create a menu item");
	    createMenuItem();
	}
	
	public MenuItems(String name, String price, String branch, String category, String availability) {
	    this.name = name;
	    this.price = price;
	    this.branch = branch;
	    this.category = category;
	    this.availability = availability;
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
		this.category = a;
		
		sc.close();
		
		this.itemInfo = String.format(",%s,%s,%s,%s,%s\n", this.name, this.price, this.branch, this.category, this.availability);
	}	
}
