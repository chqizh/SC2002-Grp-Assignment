package Menu;

import java.io.*;
import java.util.*;

public class Menu {
	
	MenuItems menu[] = new MenuItems[99];
	File menuFile = new File("Menu.txt");
	int menuLength;
	int numberOfDeletions;
	
	public Menu() {
		this.menuLength = 0;
		this.numberOfDeletions = 0;
	}
	
	public void addItems() {		
		Scanner sc = new Scanner(System.in);
		System.out.println("How many menu items would you like to add?");
	    	int numberToAdd = sc.nextInt();
		sc.close();
		
		for (int i=0; i<numberToAdd; i++) {
			MenuItems item = new MenuItems();
			this.menu[this.menuLength+i] = item;
			int id = this.menuLength + i + 1 + this.numberOfDeletions;
			this.menu[this.menuLength+i].id = id;
			//so the position in array could change after any deletion but the id won't change
			this.menuLength++;
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(this.menuFile, true));
				writer.write(id);
				writer.write(this.menu[this.menuLength+i].itemInfo);
				writer.close();	
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}
	}
	
	public void deleteItems() {
		MenuDisplay display = new MenuDisplay();
		display.displayFullMenu(this);	
		
		Scanner sc1 = new Scanner(System.in);
		System.out.println("How many menu items would you like to delete?");
	    	int numberToDelete = sc1.nextInt();
		sc1.close();
		
		int[] idArray = null;
		
		for (int n=0; n<this.menuLength; n++) {
			idArray[n] = this.menu[n].id;
		}
		
		for (int i=0; i<numberToDelete; i++) {
						
			Scanner sc = new Scanner(System.in);
			System.out.println("Please enter the id of the item you would like to delete");
		    	int id = sc.nextInt();
			sc.close();
			
			int match = 0;
			for (int k=0; k<this.menuLength; k++) {
				if (id==idArray[k]) {
					match = k;
					break;
				}
			}
			
			int shiftNumber = this.menuLength - match;
			for (int p = match; p<=shiftNumber; p++) {
				this.menu[p] = this.menu[p+1];
				idArray[p] = idArray[p+1];
			}
			
			this.menuLength--;
			this.numberOfDeletions++;			
		}
		
		File tempFile = new File("tempFile.txt");
		for (int q = 0; q<this.menuLength; q++) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile,true));
					writer.write(idArray[q]);
					writer.write(this.menu[q].itemInfo);
					writer.close();			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		boolean successful = tempFile.renameTo(this.menuFile);		
	}
	
	public void updateName() {
		MenuDisplay display = new MenuDisplay();
		display.displayFullMenu(this);	
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the id of the item you would like to update the name of");
	    	int id = sc.nextInt();
		sc.close();
		
		int[] idArray = null;
		
		for (int n=0; n<this.menuLength; n++) {
			idArray[n] = this.menu[n].id;
		}
		
		int match = 0;
		for (int k=0; k<this.menuLength; k++) {
			if (id==idArray[k]) {
				match = k;
				break;
			}
		}
		
		Scanner sc2 = new Scanner(System.in);
		System.out.printf("Old name is %s, please enter new name", this.menu[match].name);
	    	String newName = sc2.nextLine();
		sc2.close();	
		
		this.menu[match].name = newName;
		
		File nameTempFile = new File("nameTempFile.txt");
		for (int q = 0; q<this.menuLength; q++) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(nameTempFile,true));
					writer.write(idArray[q]);
					writer.write(this.menu[q].itemInfo);
					writer.close();			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		boolean successful = nameTempFile.renameTo(this.menuFile);		
	}
	
	public void updatePrice() {
		MenuDisplay display = new MenuDisplay();
		display.displayFullMenu(this);	
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the id of the item you would like to update the price of");
	    	int id = sc.nextInt();
		sc.close();
		
		int[] idArray = null;
		
		for (int n=0; n<this.menuLength; n++) {
			idArray[n] = this.menu[n].id;
		}
		
		int match = 0;
		for (int k=0; k<this.menuLength; k++) {
			if (id==idArray[k]) {
				match = k;
				break;
			}
		}
		
		Scanner sc2 = new Scanner(System.in);
		System.out.printf("Old price is %s, please enter new price", this.menu[match].price);
	    	int newPrice = sc2.nextInt();
		sc2.close();	
		
		this.menu[match].price = Integer.toString(newPrice);
		
		File priceTempFile = new File("priceTempFile.txt");
		for (int q = 0; q<this.menuLength; q++) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(priceTempFile,true));
					writer.write(idArray[q]);
					writer.write(this.menu[q].itemInfo);
					writer.close();			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		boolean successful = priceTempFile.renameTo(this.menuFile);		
	}
	
	public void updateBranch() {
		MenuDisplay display = new MenuDisplay();
		display.displayFullMenu(this);	
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the id of the item you would like to update the branch of");
	    	int id = sc.nextInt();
		sc.close();
		
		int[] idArray = null;
		
		for (int n=0; n<this.menuLength; n++) {
			idArray[n] = this.menu[n].id;
		}
		
		int match = 0;
		for (int k=0; k<this.menuLength; k++) {
			if (id==idArray[k]) {
				match = k;
				break;
			}
		}
		
		Scanner sc2 = new Scanner(System.in);
		System.out.printf("Old branch is %s, please enter new branch", this.menu[match].branch);
	    	String newBranch = sc2.nextLine();
		sc2.close();	
		
		this.menu[match].branch = newBranch;
		
		File branchTempFile = new File("branchTempFile.txt");
		for (int q = 0; q<this.menuLength; q++) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(branchTempFile,true));
					writer.write(idArray[q]);
					writer.write(this.menu[q].itemInfo);
					writer.close();			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		boolean successful = branchTempFile.renameTo(this.menuFile);		
	}
	
	public void updateCategory() {
		MenuDisplay display = new MenuDisplay();
		display.displayFullMenu(this);	
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the id of the item you would like to update the category of");
	    	int id = sc.nextInt();
		sc.close();
		
		int[] idArray = null;
		
		for (int n=0; n<this.menuLength; n++) {
			idArray[n] = this.menu[n].id;
		}
		
		int match = 0;
		for (int k=0; k<this.menuLength; k++) {
			if (id==idArray[k]) {
				match = k;
				break;
			}
		}
		
		Scanner sc2 = new Scanner(System.in);
		System.out.printf("Old category is %s, please enter new category", this.menu[match].category);
	    	String newCategory = sc2.nextLine();
		sc2.close();	
		
		this.menu[match].category = newCategory;
		
		File categoryTempFile = new File("categoryTempFile.txt");
		for (int q = 0; q<this.menuLength; q++) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(categoryTempFile,true));
					writer.write(idArray[q]);
					writer.write(this.menu[q].itemInfo);
					writer.close();			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		boolean successful = categoryTempFile.renameTo(this.menuFile);		
	}
	
	public void updateAvailability() {
		MenuDisplay display = new MenuDisplay();
		display.displayFullMenu(this);	
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the id of the item you would like to update the availability of");
	    	int id = sc.nextInt();
		sc.close();
		
		int[] idArray = null;
		
		for (int n=0; n<this.menuLength; n++) {
			idArray[n] = this.menu[n].id;
		}
		
		int match = 0;
		for (int k=0; k<this.menuLength; k++) {
			if (id==idArray[k]) {
				match = k;
				break;
			}
		}
		
		Scanner sc2 = new Scanner(System.in);
		System.out.printf("Old availability is %s, please enter new availability", this.menu[match].availability);
	    	String newAvailability = sc2.nextLine();
		sc2.close();	
		
		this.menu[match].availability = newAvailability;
		
		File availabilityTempFile = new File("availabilityTempFile.txt");
		for (int q = 0; q<this.menuLength; q++) {
			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(availabilityTempFile,true));
					writer.write(idArray[q]);
					writer.write(this.menu[q].itemInfo);
					writer.close();			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		boolean successful = availabilityTempFile.renameTo(this.menuFile);		
	}
}
