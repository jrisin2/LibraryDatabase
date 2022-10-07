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
/**
 *
 * @author Jules
 */
public class BookCatalogueDAO implements DAO<BookCatalogue>
{   
    public BookCatalogueDAO() {
        
    }
    List<BookCatalogue> bookcatalogues;
    /**
     * Get a single contact entity as a contact object
     * @param isbn
     * @return 
     */
    @Override
    public Optional<BookCatalogue> get(int isbn) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM BookCatalogue WHERE ISBN = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, isbn);
            rs = stmt.executeQuery();
            BookCatalogue bookcatalogue = null;
            while (rs.next()) {
                bookcatalogue = new BookCatalogue(rs.getInt("ISBN"), rs.getString("Book_Title"), rs.getString("Book_Author"), rs.getString("Book_Genre"));
            }
            return Optional.ofNullable(bookcatalogue);
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
    public List<BookCatalogue> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        bookcatalogues = new ArrayList<>();
        try {
            String sql = "SELECT * FROM BookCatalogue";
            rs = db.executeQuery(sql);
            BookCatalogue bookcatalogue = null;
            while (rs.next()) {
                bookcatalogue = new BookCatalogue(rs.getInt("ISBN"), rs.getString("Book_Title"), rs.getString("Book_Author"), rs.getString("Book_Genre"));
                bookcatalogues.add(bookcatalogue);
            }
            return bookcatalogues;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    //@Override
    public List<BookCatalogue> getAllTitles(String title) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        bookcatalogues = new ArrayList<>();
        try {
            String sql = "SELECT * FROM BookCatalogue";
            rs = db.executeQuery(sql);
            BookCatalogue bookcatalogue = null;
            while (rs.next()) {   
                if (rs.getString("Book_Title").toLowerCase().contains(title.toLowerCase())) {
                    bookcatalogue = new BookCatalogue(rs.getInt("ISBN"), rs.getString("Book_Title"), rs.getString("Book_Author"), rs.getString("Book_Genre"));
                    bookcatalogues.add(bookcatalogue);
                }
                
            }
            return bookcatalogues;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    public List<BookCatalogue> getAllAuthors(String author) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        bookcatalogues = new ArrayList<>();
        try {
            String sql = "SELECT * FROM BookCatalogue";
            rs = db.executeQuery(sql);
            BookCatalogue bookcatalogue = null;
            while (rs.next()) {   
                if (rs.getString("Book_Author").toLowerCase().contains(author.toLowerCase())) {
                    bookcatalogue = new BookCatalogue(rs.getInt("ISBN"), rs.getString("Book_Title"), rs.getString("Book_Author"), rs.getString("Book_Genre"));
                    bookcatalogues.add(bookcatalogue);
                }
                
            }
            return bookcatalogues;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    public List<BookCatalogue> getAllGenres(String genre) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        bookcatalogues = new ArrayList<>();
        try {
            String sql = "SELECT * FROM BookCatalogue";
            rs = db.executeQuery(sql);
            BookCatalogue bookcatalogue = null;
            while (rs.next()) {   
                if (rs.getString("Book_Genre").toLowerCase().contains(genre.toLowerCase())) {
                    bookcatalogue = new BookCatalogue(rs.getInt("ISBN"), rs.getString("Book_Title"), rs.getString("Book_Author"), rs.getString("Book_Genre"));
                    bookcatalogues.add(bookcatalogue);
                }
                
            }
            return bookcatalogues;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    /**
     * Insert a station object into station table
     * @param bookcatalogue 
     */
    @Override
    public void insert(BookCatalogue bookcatalogue)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO BookCatalogue(ISBN, Book_Title, Book_Author, Book_Genre) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, bookcatalogue.getISBN());
            stmt.setString(2, bookcatalogue.getTitle());
            stmt.setString(3, bookcatalogue.getAuthor());
            stmt.setString(4, bookcatalogue.getGenre());
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new book was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Update a station entity in database if it exists using a station object
     * @param bookcatalogue
     */
    @Override
    public void update(BookCatalogue bookcatalogue) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE BookCatalogue SET Book_Title=?, Book_Author=?, Book_Genre=? WHERE ISBN=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setString(1, bookcatalogue.getTitle());
            stmt.setString(2, bookcatalogue.getAuthor());
            stmt.setString(3, bookcatalogue.getGenre());
            stmt.setInt(4, bookcatalogue.getISBN());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing book was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Delete a station from station table if the entity exists
     * @param bookcatalogue 
     */
    @Override
    public void delete(BookCatalogue bookcatalogue) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM BookCatalogue WHERE ISBN = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, bookcatalogue.getISBN());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A book was deleted successfully!");
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
            String sql = "SELECT * FROM BookCatalogue WHERE ISBN = -1";//We just need this sql query to get the column headers
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
