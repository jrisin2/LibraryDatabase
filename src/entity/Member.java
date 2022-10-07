/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
/**
 *
 * @author Jules
 */
public class Member 
{
    private int ID;
    private String firstname;
    private String lastname;
    private String email;
    private String phonenumber;
 
    public Member(int ID, String firstname, String lastname, String email, String phonenumber)
    {
        this.ID = ID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phonenumber = phonenumber;
    }
    public String getFirstname() {
        return firstname;
    }


    public String getLastname() {
        return lastname;
    }


    public String getEmail() {
        return email;
    }


    public String getPhonenumber() {
        return phonenumber;
    }

    public int getID() {
        return ID;
    }
    
    /*public boolean checkFKViolation() {
        return viol;
    }*/

    @Override
    public String toString() {
        return "Member{" + "ID=" + ID + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ",phonenumber" + '}';
    }

  
}
