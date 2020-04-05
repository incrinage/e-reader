package book;

import java.time.LocalDate;
import java.util.List;

public class Book {

    private int id;
    private String name;
    private String author;
    private String publisher;
    private LocalDate datePublished;
    private List<Isbn> isbn;

    public int getId() {
        return id;
    }

    public Book withId(int id) {
        this.id = id;
        return this;
    }

    public Book(String name) {
        this.name = name;
    }

    public Book withAuthor(String author) {
        this.author = author;
        return this;
    }

    public Book withPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public Book withDatePublished(LocalDate date) {
        this.datePublished = date;
        return this;
    }

    public Book withIsbn(List<Isbn> isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public LocalDate getDatePublished() {
        return datePublished;
    }

    public List<Isbn> getIsbn() {
        return isbn;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Isbn s1 : getIsbn()) s.append(s1.toString()).append(" ");
        return id +" " + name + " by " + author + " and published by " + publisher + " on " + datePublished.toString() + " " + s.toString().trim();

    }
}
