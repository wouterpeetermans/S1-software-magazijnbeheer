package be.magazijnbeheer.core;

import be.magazijnbeheer.core.Database;

public class viewModel {
    public static Integer addType(String type) {
        return Database.addType(type);
    }

    public static Integer addItem(String item) {
        return Database.addItemOfType(item);
    }

    public static void removeItem(String item) {
        Database.removeItemOfType(item);
    }

    public static void addLender(String name, String address) {
        Database.addLender(name, address);
    }

    public static void addLenderToItem(String lenderID, String itemID) {
        Database.addLenderToItem(lenderID, itemID);
    }

    public static void removeLenderFromItem(String itemID) {
        Database.removeLenderFromItem(itemID);
    }

    public static String viewItemsLender(String lenderID) {
        return Database.getItemsLender(lenderID);
    }
}
