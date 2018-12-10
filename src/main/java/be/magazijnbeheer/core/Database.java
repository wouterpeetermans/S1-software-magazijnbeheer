package be.magazijnbeheer.core;

import javax.help.Map;
import javax.swing.*;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

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
        String sql = "DELETE FROM Items WHERE ID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,item.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        }
        System.out.println(item + " removed");
    }

    public void addLender(Lender lender) {
        String sql = "INSERT INTO Lenders(ID,Name,Address) VALUES(?,?,?)";
        int lenderID;
        lenderID = -1;
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,lender.getId());
            pstmt.setString(2,lender.getName());
            pstmt.setString(3,lender.getAddress());
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if(rs.next()){
                lenderID = rs.getInt(1);
            }
        } catch (SQLException e){
            e.printStackTrace();
            return;
        }
        JOptionPane.showMessageDialog(null, lender.getName() + " added\nAddress: " + lender.getAddress() + "\nID: " + Integer.toString(lenderID), "new lender", JOptionPane.INFORMATION_MESSAGE);
        System.out.println(lender.getName() + " added, ID " + Integer.toString(lenderID));
    }

    public void addLenderToItem(Integer lenderID, Item item) {
        String sql = "UPDATE Items SET LenderID = ? "
                + "WHERE ID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,lenderID);
            pstmt.setInt(2,item.getId());
            pstmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            return;
        }


        System.out.println("added " + item.getId() + " " + lenderID);
    }

    public void removeLenderFromItem(Item item) {
        String sql = "UPDATE Items SET LenderID = NULL "
                + "WHERE ID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,item.getId());
            pstmt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
            return;
        }
        System.out.println(item.getId() + " removed");
    }

    public String getItemsLender(Integer lenderID) {
        String sql = "SELECT Items.ID, TypeName, LenderID FROM Items "
                + "INNER JOIN Types on Items.TypeID = Types.ID "
                + "WHERE LenderID = ?";
        ResultSet set;
        String output = "";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, lenderID);
            set = pstmt.executeQuery();

            while (set.next()){
                output += set.getInt("ID") + "\t";
                output += set.getString("TypeName") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return e.toString();
        }
        System.out.println("items lended by " + lenderID + " fetched");

        return output;
    }

    public String getStock() {
        String sql = "SELECT Items.ID, TypeName, LenderID FROM Items "
                + "INNER JOIN Types on Items.TypeID = Types.ID "
                + "WHERE LenderID IS NULL";
        ResultSet set;
        String output = "";
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            set = pstmt.executeQuery();

            while (set.next()){
                output += set.getInt("ID") + "\t";
                output += set.getString("TypeName") + "\n";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return e.toString();
        }
        System.out.println("items in stock fetched");

        return output;
    }

    public ArrayList getAllTypes() {
        String sql = "SELECT TypeName FROM Types";
        ResultSet set;
        ArrayList<String> output = new ArrayList<String>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(sql);
            set = pstmt.executeQuery();

            while (set.next()){
                output.add(set.getString("TypeName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            output.clear();
            output.add(e.toString());
            return output;
        }
        System.out.println(output);
        return output;
    }

    public Integer getAmountItemsOfType(String type){
        return 2;
    }
}
