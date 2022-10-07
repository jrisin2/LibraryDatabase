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
public class BookCatalogue 
{
    private int ISBN;
    private String title;
    private String author;
    private String genre;
    //private boolean viol;
    
    public BookCatalogue(int ISBN, String title, String author, String genre)
    {
        this.ISBN = ISBN;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public int getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

  
    /*public boolean checkFKViolation() {
        return viol;
    }*/

    @Override
    public String toString() {
        return "BookCatalogue{" + "ISBN=" + ISBN + ", title=" + title + ", author=" + author + ", genre=" + genre + '}';
    }

  
}
