package Menu;

import java.io.Serializable;

public class MenuItem implements Serializable{
    private String itemName;
    private double price;
    private int itemID;
    private String category;
    private String branchName;
    private String customisation;

    public MenuItem(int itemID, String itemName, double price, String category, String branchName){
        this.itemID = itemID;
        this.itemName = itemName;
        this.price = price;
        this.category = category;
        this.branchName = branchName;
        this.customisation = "-";
    }

    
    public String getCategory() {
        return category;
    }


    public void setCategory(String category) {
        this.category = category;
    }


    public String getBranchName() {
        return branchName;
    }


    public void setBranchName(String branch) {
        this.branchName = branch;
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

    public String getCustomisation() {
        return customisation;
    }

    public void setCustomisation(String customisation) {
        this.customisation = customisation;
    }

}
