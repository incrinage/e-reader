package reader;

import book.*;

import java.util.List;

//TODO mustDo: Paginate
public class ReaderService {
    private final Reader readerDAO;

    public ReaderService(Reader readerDAO) {
        this.readerDAO = readerDAO;
    }

    public Chapter getChapter(int bookId, int chId) {
        return readerDAO.readChapter(bookId, chId);
    }


    public Page getPage(int bookId, int chId, int pageId) {
        Page page = readerDAO.readPage("src/main/resources/lyric.txt");
        return page == null ? new Page() : page;
    }

    public List<ChapterMeta> getChapterList(int bookId) {
        return readerDAO.readChapterList(bookId);
    }

    public List<Book> getBooks(/* paginated list of books, search query as param*/) {
        return readerDAO.readBookList("src/main/resources/book/book-list.txt");
    }

    public List<BookMark> getBookMarks(int bookId) {
        return null;
    }

}
