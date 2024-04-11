package menuitems;

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
}
