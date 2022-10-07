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
public class PhysicalBookDAO implements DAO<PhysicalBook>
{   
    public PhysicalBookDAO() {
        
    }
    List<PhysicalBook> physicalbooks;
    /**
     * Get a single contact entity as a contact object
     * @param id
     * @return 
     */
    @Override
    public Optional<PhysicalBook> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM PhysicalBook WHERE Book_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            PhysicalBook physicalbook = null;
            while (rs.next()) {
                physicalbook = new PhysicalBook(rs.getInt("Book_ID"), rs.getInt("ISBN"));
            }
            return Optional.ofNullable(physicalbook);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Get all contact entities as a List
     * @return 
     */
    @Override
    public List<PhysicalBook> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        physicalbooks = new ArrayList<>();
        try {
            String sql = "SELECT * FROM PhysicalBook";
            rs = db.executeQuery(sql);
            PhysicalBook physicalbook = null;
            while (rs.next()) {
                physicalbook = new PhysicalBook(rs.getInt("Book_ID"), rs.getInt("ISBN"));
                physicalbooks.add(physicalbook);
            }
            return physicalbooks;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Insert a station object into station table
     * @param physicalbook 
     */
    @Override
    public void insert(PhysicalBook physicalbook)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO PhysicalBook(Book_ID, ISBN) VALUES (?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, physicalbook.getID());
            stmt.setInt(2, physicalbook.getISBN());
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new book copy was inserted successfully!");
            }
        } catch (SQLException ex) {
            //System.err.println(ex.toString());
            JOptionPane.showMessageDialog(null, "Book ISBN does not exist.", "SQL Exception", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Update a station entity in database if it exists using a station object
     * @param physicalbook
     */
    @Override
    public void update(PhysicalBook physicalbook) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE PhysicalBook SET ISBN=? WHERE Book_ID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, physicalbook.getISBN());
            stmt.setInt(2, physicalbook.getID());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing book copy was updated successfully!");
            }
        } catch (SQLException ex) {
            //System.err.println(ex.toString());
            JOptionPane.showMessageDialog(null, "Book ISBN does not exist.", "SQL Exception", JOptionPane.ERROR_MESSAGE);
        }
    }
    public boolean checkFKViolation(int isbn) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM PhysicalBook WHERE ISBN = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, isbn);
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
     * Delete a station from station table if the entity exists
     * @param physicalbook 
     */
    @Override
    public void delete(PhysicalBook physicalbook) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM PhysicalBook WHERE Book_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, physicalbook.getID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A book copy was deleted successfully!");
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
            String sql = "SELECT * FROM PhysicalBook WHERE Book_ID = -1";//We just need this sql query to get the column headers
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
