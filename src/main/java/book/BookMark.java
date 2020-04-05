package book;

public class BookMark {
    private int chapter;
    private int page;
    private int book;

    public BookMark withChapter(int chapter) {
        this.chapter = chapter;
        return this;
    }

    public BookMark withPage(int page) {
        this.page = page;
        return this;
    }

    public BookMark withBook(int book) {
        this.book = book;
        return this;
    }

    public int getChapter() {
        return chapter;
    }

    public int getPage() {
        return page;
    }

    public int getBook() {
        return book;
    }
}
