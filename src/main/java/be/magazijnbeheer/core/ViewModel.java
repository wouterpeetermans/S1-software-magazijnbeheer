package be.magazijnbeheer.core;

public class ViewModel {
    private Database database;

    public ViewModel(){
        database = Database.getInstance();
    }

    public Integer addType(String type) {
        return database.addType(type);
    }

    public Integer addItem(String item) {
        return database.addItemOfType(item);
    }

    public void removeItem(String item) {
        database.removeItemOfType(item);
    }

    public void addLender(String name, String address) {
        database.addLender(name, address);
    }

    public void addLenderToItem(String lenderID, String itemID) {
        database.addLenderToItem(lenderID, itemID);
    }

    public void removeLenderFromItem(String itemID) {
        database.removeLenderFromItem(itemID);
    }

    public String viewItemsLender(String lenderID) {
        return database.getItemsLender(lenderID);
    }
}
