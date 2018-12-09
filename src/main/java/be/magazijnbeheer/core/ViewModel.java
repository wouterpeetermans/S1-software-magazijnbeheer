package be.magazijnbeheer.core;

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
        database.removeItemOfType(new Item(Integer.getInteger(itemID)));
    }

    public void addLender(String name, String address) {
        database.addLender(new Lender(name,address));
    }

    public void addLenderToItem(String lenderID, String itemID) {
        database.addLenderToItem(Integer.getInteger(lenderID), new Item(Integer.getInteger(itemID)));
    }

    public void removeLenderFromItem(String itemID) {
        database.removeLenderFromItem(new Item(Integer.getInteger(itemID)));
    }

    public String viewItemsLender(String lenderID) {
        return database.getItemsLender(Integer.getInteger(lenderID));
    }
}
