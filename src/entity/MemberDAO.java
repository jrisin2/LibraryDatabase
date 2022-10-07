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
public class MemberDAO implements DAO<Member>
{   
    public MemberDAO() {
        
    }
    List<Member> members;
    /**
     * Get a single contact entity as a contact object
     * @param id
     * @return 
     */
    @Override
    public Optional<Member> get(int id) {
        DB db = DB.getInstance();
        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM Member WHERE Member_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            Member member = null;
            while (rs.next()) {
                member = new Member(rs.getInt("Member_ID"), rs.getString("Member_First_Name"), rs.getString("Member_Last_Name"), rs.getString("Member_Email"), rs.getString("Member_Phone_Number"));
            }
            return Optional.ofNullable(member);
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
    public List<Member> getAll() {
        DB db = DB.getInstance();
        ResultSet rs = null;
        members = new ArrayList<>();
        try {
            String sql = "SELECT * FROM Member";
            rs = db.executeQuery(sql);
            Member member = null;
            while (rs.next()) {
                member = new Member(rs.getInt("Member_ID"), rs.getString("Member_First_Name"), rs.getString("Member_Last_Name"), rs.getString("Member_Email"), rs.getString("Member_Phone_Number"));
                members.add(member);
            }
            return members;
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            return null;
        }
    }
    
    /**
     * Insert a station object into station table
     * @param member 
     */
    @Override
    public void insert(Member member)
    {
        DB db = DB.getInstance();
        try {
            String sql = "INSERT INTO Member(Member_ID, Member_First_Name, Member_Last_Name, Member_Email, Member_Phone_Number) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, member.getID());
            stmt.setString(2, member.getFirstname());
            stmt.setString(3, member.getLastname());
            stmt.setString(4, member.getEmail());
            stmt.setString(5, member.getPhonenumber());
            int rowInserted = stmt.executeUpdate();
            if (rowInserted > 0) {
                System.out.println("A new member was inserted successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Update a station entity in database if it exists using a station object
     * @param member
     */
    @Override
    public void update(Member member) {
        DB db = DB.getInstance();
        try {
            String sql = "UPDATE Member SET Member_First_Name=?, Member_Last_Name=?, Member_Email=?, Member_Phone_Number=? WHERE Member_ID=?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setString(1, member.getFirstname());
            stmt.setString(2, member.getLastname());
            stmt.setString(3, member.getEmail());
            stmt.setString(4, member.getPhonenumber());
            stmt.setInt(5, member.getID());
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("An existing member was updated successfully!");
            }
        } catch (SQLException ex) {
            System.err.println(ex.toString());
        }
    }
    
    /**
     * Delete a station from station table if the entity exists
     * @param member 
     */
    @Override
    public void delete(Member member) {
        DB db = DB.getInstance();
        try {
            String sql = "DELETE FROM Member WHERE Member_ID = ?";
            PreparedStatement stmt = db.getPreparedStatement(sql);
            stmt.setInt(1, member.getID());
            int rowsDeleted = stmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("A member was deleted successfully!");
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
            String sql = "SELECT * FROM Member WHERE Member_ID = -1";//We just need this sql query to get the column headers
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
