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

    public Integer addItem(String type) {
        return database.addItemOfType(new Item(new Type(type)));
    }

    public void removeItem(String itemID) {
        database.removeItemOfType(new Item(Integer.parseInt(itemID)));
    }

    public void addLender(String name, String address) {
        database.addLender(new Lender(name,address));
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
}
