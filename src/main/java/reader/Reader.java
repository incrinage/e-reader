package reader;

import book.*;

import java.util.List;

public interface Reader {
    Page readPage(String page);
    List<Book> readBookList(String path);
    Chapter readChapter(int bookId, int chId);
    List<ChapterMeta> readChapterList(int bookId);
    List<BookMark> readBookMarks(String path);

}
