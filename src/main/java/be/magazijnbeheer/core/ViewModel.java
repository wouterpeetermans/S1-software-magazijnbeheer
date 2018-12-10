package be.magazijnbeheer.core;

import java.io.Serializable;
import java.util.ArrayList;

public class ViewModel {
    private Database database;

    public ViewModel(){
        database = Database.getInstance();
    }

    public Integer addType(String typeName) {
        return database.addType(new Type(typeName));
    }

    public Item addItem(String type) {
        Item item = new Item(new Type(type));
        if (database.addItemOfType(item) != -1){
            return item;
        }
        else {
            return null;
        }
    }

    public void removeItem(String itemID) {
        database.removeItemOfType(new Item(Integer.parseInt(itemID)));
    }

    public Lender addLender(String name, String address) {
        Lender lender =  new Lender(name,address);
        if(database.addLender(lender) != -1){
            return lender;
        }
        else {
            return null;
        }
    }

    public void addLenderToItem(String lenderID, String itemID) {
        database.addLenderToItem(Integer.parseInt(lenderID), new Item(Integer.parseInt(itemID)));
    }

    public void removeLenderFromItem(String itemID) {
        database.removeLenderFromItem(new Item(Integer.parseInt(itemID)));
    }

    public String viewItemsLender(String lenderID) {
        if (lenderID.equals("stock")) {
            return database.getStock();
        } else {
            return database.getItemsLender(Integer.parseInt(lenderID));
        }
    }

    public ArrayList getAllTypes() {
        return database.getAllTypes();
    }

    public String getItemsOfType(String typeName){
        return database.getItemsOfType(typeName);
    }
}
