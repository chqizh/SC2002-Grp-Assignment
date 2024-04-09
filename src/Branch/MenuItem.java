package Branch;

public class MenuItem {
    private String itemName;
    private float price;
    private int itemID;

    public MenuItem(int itemID, String itemName, float price){
        this.itemID = itemID;
        this.itemName = itemName;
        this.price = price;
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
