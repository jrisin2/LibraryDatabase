/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import core.*;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author Jules
 */
public class CheckoutDAO implements DAO<Checkout>
{   
    public CheckoutDAO() {
        
    }
    List<Checkout> checkouts;
    /**
     * Get a single contact entity as a contact object
     * @param id
     * @return 
     */
    @Override
    public Optional<Checkout> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Checkout WHERE Checkout_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Checkout checkout = null;
            while (rs.next()) {
                checkout = new Checkout(rs.getInt("Checkout_ID"), rs.getInt("Book_ID"), rs.getInt("Member_ID"), rs.getString("Checkout_Date_Time"), rs.getString("Return_Date_Time"), rs.getDouble("Amount_Due"));
            }
            return Optional.ofNullable(checkout);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    public boolean checkFKViolation(int MemberID) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Checkout WHERE Member_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, MemberID);
            rs = stmt.executeQuery();
            boolean recordFound = false;
            while (rs.next()) {
                recordFound = true;
            }
            return recordFound;
            
        }catch (SQLException ex) {
            System.err.println(ex.toString());
            return false;
        }
    }
    
    public boolean checkFKViolationbook(int bookID) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Checkout WHERE Book_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, bookID);
            rs = stmt.executeQuery();
            boolean recordFound = false;
            while (rs.next()) {
                recordFound = true;
            }
            return recordFound;
            
        }catch (SQLException ex) {
            System.err.println(ex.toString());
            return false;
        }
    }
    /**
     * Get all contact entities as a List
     * @return 
     */
    @Override
    public List<Checkout> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        checkouts = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Checkout";
            rs = db.executeQuery(sql);
            Checkout checkout = null;
            while (rs.next()) {
                checkout = new Checkout(rs.getInt("Checkout_ID"), rs.getInt("Book_ID"), rs.getInt("Member_ID"), rs.getString("Checkout_Date_Time"), rs.getString("Return_Date_Time"), rs.getDouble("Amount_Due"));
                checkouts.add(checkout);
            }
            return checkouts;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Insert a station object into station table
     * @param checkout 
     */
    @Override
    public void insert(Checkout checkout)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO Checkout(Checkout_ID, Book_ID, Member_ID, Checkout_Date_Time, Return_Date_Time, Amount_Due) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, checkout.getID());
            stmt.setInt(2, checkout.getBookID());
            stmt.setInt(3, checkout.getMemberID());
            stmt.setString(4, checkout.getCheckoutDateTime());
            stmt.setString(5, checkout.getReturnDateTime());
            stmt.setDouble(6, checkout.getAmountdue());
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new checkout was inserted successfully!");
            }
        } catch (SQLException ex) {
            //System.err.println(ex.toString());
            JOptionPane.showMessageDialog(null, "Book ID or Member ID does not exist.", "SQL Exception", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Update a station entity in database if it exists using a station object
     * @param checkout
     */
    @Override
    public void update(Checkout checkout) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE Checkout SET Book_ID=?, Member_ID=?, Checkout_Date_Time=?, Return_Date_Time=?, Amount_Due=? WHERE Checkout_ID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, checkout.getBookID());
            stmt.setInt(2, checkout.getMemberID());
            stmt.setString(3, checkout.getCheckoutDateTime());
            stmt.setString(4, checkout.getReturnDateTime());
            stmt.setDouble(5, checkout.getAmountdue());
            stmt.setInt(6, checkout.getID());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing checkout was updated successfully!");
            }
        } catch (SQLException ex) {
            //System.err.println(ex.toString());
            JOptionPane.showMessageDialog(null, "Book ID or Member ID does not exist.", "SQL Exception", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Delete a station from station table if the entity exists
     * @param checkout 
     */
    @Override
    public void delete(Checkout checkout) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM Checkout WHERE Checkout_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, checkout.getID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A checkout was deleted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Get all column names in a list array
     * @return 
     */
    @Override
    public List<String> getColumnNames() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        List<String> headers = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Checkout WHERE Checkout_ID = -1";//We just need this sql query to get the column headers
            rs = db.executeQuery(sql);
            ResultSetMetaData rsmd = rs.getMetaData();
            //Get number of columns in the result set
            int numberCols = rsmd.getColumnCount();
            for (int i = 1; i <= numberCols; i++) {
                headers.add(rsmd.getColumnLabel(i));//Add column headers to the list
            }
            return headers;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        } 
    }
}
