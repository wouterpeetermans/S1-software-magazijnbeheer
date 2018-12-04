public class Database {
    public static Integer addItemOfType(String item) {
        // TODO: getItemID, if doesn't exists, addItem, return itemID
        System.out.println(item + " added");
        return 1;
    }

    public static Integer addType (String type) {
        // TODO: getTypeID, if doesn't exists, addType, return typeID
        System.out.println(type + " added");
        return 1;
    }

    public static void removeItemOfType (String item) {
        // TODO: getTypeID, if doesn't exists, addType
        System.out.println(item + " removed");
    }

    public static void addLender(String name, String address) {
        // TODO: getLenderID, if doesn't exists, addLender
        System.out.println(name + " " + address + " added");
    }

    public static void addLenderToItem(String lenderID, String itemID) {
        // TODO: check getLenderID and getItemID, then addLenderIDToItem
        System.out.println("added " + itemID + " " + lenderID);
    }

    public static void removeLenderFromItem(String itemID) {
        // TODO: check getLenderID and getItemID, then removeLenderIDToItem
        System.out.println(itemID + " removed");
    }

    public static String getItemsLender(String lenderID) {
        // TODO: check lenderID, get items with that ID
        System.out.println("items lended by " + lenderID + " fetched");
        return "demo";
    }
}
