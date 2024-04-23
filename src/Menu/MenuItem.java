package Menu;

import java.io.Serializable;

public class MenuItem implements Serializable{
    
    private String itemName;
    private double price;
    private int itemID;
    private String category;
    private String branch;

    public MenuItem(int itemID, String itemName, double price, String category, String branch){
        this.itemID = itemID;
        this.itemName = itemName;
        this.price = price;
        this.category = category;
        this.branch = branch;
    }

    
    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public String getBranch() {
        return branch;
    }


    public void setBranch(String branch) {
        this.branch = branch;
    }


    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    public double getPrice() {
        return price;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

}
