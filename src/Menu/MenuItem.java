package Menu;

public class MenuItem {
    public String itemName;
    public float price;
    public int itemID;
    public String category;
    public String branch;

    public MenuItem(int itemID, String itemName, float price, String category, String branch){
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
    
    public float getPrice() {
        return price;
    }


    public int getItemID() {
        return itemID;
    }


    public void setItemID(int itemID) {
        this.itemID = itemID;
    }
}