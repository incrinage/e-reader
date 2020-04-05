import book.Book;
import book.ChapterMeta;
import book.Page;

import java.util.List;

//TODO niceToHave: display GUI
public class Display {

    public void displayPage(Page page) {
        for (String line : page.getLines()) {
            System.out.println(line);
        }
    }

    public void displayChapters(List<ChapterMeta> chapterMetas) {
        for (ChapterMeta meta : chapterMetas) {
            System.out.println("Chapter " + meta.getNum() + " " + meta.getName());
        }
    }


    public void displayBookList(List<Book> books) {
        for (Book b : books) {
            System.out.println(b.getId() + "-" + b.getName() + " by " + b.getAuthor());
        }
    }
}
