package be.magazijnbeheer.core;

import java.io.Serializable;
import java.sql.*;

public class Database {

    private static Database instance = new Database();

    private Connection conn;

    private Database(){
        initDB("data");
    }

    private void initDB(String dbName){
        String url = "jdbc:sqlite:" + dbName + ".db";

        try {
            conn = DriverManager.getConnection(url);
            if(conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A connection to the database has been instantiated.");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        initTables();
    }

    private void initTables(){
        String sql1 = "CREATE TABLE IF NOT EXISTS Items (\n"
                + "	ID integer PRIMARY KEY,\n"
                + "	TypeID integer NOT NULL,\n"
                + "	LenderID integer\n"
                + ");\n";
        String sql2 = "CREATE TABLE IF NOT EXISTS Types (\n"
                + "	ID integer PRIMARY KEY,\n"
                + "	TypeName text NOT NULL\n"
                + ");\n";
        String sql3 = "CREATE TABLE IF NOT EXISTS Lenders (\n"
                + "	ID integer PRIMARY KEY,\n"
                + "	Name text NOT NULL,\n"
                + "	Address text NOT NULL\n"
                + ");";
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql1);
            stmt.execute(sql2);
            stmt.execute(sql3);
        } catch (SQLException e){
            e.printStackTrace();
        }

    }



    public static Database getInstance(){
        return instance;
    }





    public Integer addItemOfType(Item item) {
        String sql = "INSERT INTO Items(TypeID) VALUES(?)";//todo error on type not existing
        Integer itemID = -1;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,item.getType().getId());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                itemID = rs.getInt(1);
            }
            item.setId(itemID);
        } catch (SQLException e){
            e.printStackTrace();
        }
        System.out.println(item + " added");
        return itemID;
    }

    public Integer addType (Type type) {
        String sql = "INSERT INTO Types(ID,TypeName) VALUES(?,?)";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,type.getId());
            pstmt.setString(2,type.getTypeName());
            pstmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            return -1;
        }
        System.out.println(type + " added");
        return 1;
    }

    public void removeItemOfType (Item item) {
        // TODO: getTypeID, if doesn't exists, addType
        System.out.println(item + " removed");
    }

    public void addLender(Lender lender) {
        // TODO: getLenderID, if doesn't exists, addLender
        System.out.println(lender.getName() + " " + lender.getAddress() + " added");
    }

    public void addLenderToItem(Integer lenderID, Item item) {
        // TODO: check getLenderID and getItemID, then addLenderIDToItem
        System.out.println("added " + item.getId() + " " + lenderID);
    }

    public void removeLenderFromItem(Item item) {
        // TODO: check getLenderID and getItemID, then removeLenderIDToItem
        System.out.println(item.getId() + " removed");
    }

    public String getItemsLender(Integer lenderID) {
        // TODO: check lenderID, get items with that ID
        System.out.println("items lended by " + lenderID + " fetched");
        return "demo";
    }

    public Integer getAmountItemsOfType(String type){
        return 2;
    }
}
