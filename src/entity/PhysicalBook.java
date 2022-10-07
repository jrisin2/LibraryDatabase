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
public class PhysicalBook 
{
    private int ID;
    private int ISBN;

    //private boolean viol;
    
    public PhysicalBook(int ID, int ISBN)
    {
        this.ID = ID;
        this.ISBN = ISBN;
    }

    public int getID() {
        return ID;
    }

    public int getISBN() {
        return ISBN;
    }

    
    /*public boolean checkFKViolation() {
        return viol;
    }*/

    @Override
    public String toString() {
        return "PhysicalBook{" + "ID=" + ID + ", ISBN=" + ISBN + '}';
    }

  
}
