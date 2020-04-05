package book;

public class Isbn {
    private String isbn;
    private IsbnFormat format;

    public Isbn(String isbn, IsbnFormat format) {
        this.isbn = isbn;
        this.format = format;
    }

    public IsbnFormat getFormat() {
        return format;
    }

    public String getIsbn() {
        return isbn;
    }

    public String toString() {
        return format.getIsbnFormat() + ": " + isbn;
    }
}
