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
public class Checkout 
{
    private int ID;
    private int bookID;
    private int memberID;
    private String checkoutDateTime;
    private String returnDateTime;
    private double amountdue;
    //private boolean viol;
    
    public Checkout(int ID, int bookID, int memberID, String checkoutDateTime, String returnDateTime, double amountdue)
    {
        this.ID = ID;
        this.bookID = bookID;
        this.memberID = memberID;
        this.checkoutDateTime = checkoutDateTime;
        this.returnDateTime = returnDateTime;
        this.amountdue = amountdue;
    }

    public int getBookID() {
        return bookID;
    }

    public int getMemberID() {
        return memberID;
    }

    public String getCheckoutDateTime() {
        return checkoutDateTime;
    }

    public String getReturnDateTime() {
        return returnDateTime;
    }

    public double getAmountdue() {
        return amountdue;
    }

    public int getID() {
        return ID;
    }

    
    /*public boolean checkFKViolation() {
        return viol;
    }*/

    @Override
    public String toString() {
        return "Checkout{" + "ID=" + ID + ", bookID=" + bookID + ", memberID=" + memberID + ", checkoutDatetime=" + checkoutDateTime + ", returnDateTime=" + returnDateTime + "amountdue" + amountdue + '}';
    }

  
}
