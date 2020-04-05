package reader;

import book.*;
import exception.NotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class ReaderDAO implements Reader {
    //TODO improvement: remove redundant strings, use enum for queries or string formatter
    private static final Logger log = Logger.getLogger("reader.ReaderDAO");

    public Page readPage(String page) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(page));
            int pageNum = lines.get(lines.size() - 1).charAt(0) - '0';
            lines.remove(lines.size() - 1);
            return new Page(lines, pageNum);
        } catch (IOException ex) {
            log.info("Could not find page: " + page);
            throw new NotFoundException("Could not find page: " + page, ex);
        }
    }

    public List<Book> readBookList(String booklist) {
        try {
            List<String> rows = Files.readAllLines(Paths.get(booklist));
            List<Book> books = new ArrayList<>();
            for (String s : rows) {
                books.add(bookMapper(s));
            }
            return books;
        } catch (IOException ex) {
            log.info("Could not get books from: " + booklist);
            throw new NotFoundException("Could not get books from: " + booklist, ex);
        }
    }

    //TODO improvement: binary search chapter-meta for specific  chapter
    @Override
    public Chapter readChapter(int bookId, int chId) {
        try {
            List<String> chapterRows = Files.readAllLines(
                    Paths.get("src/main/resources/book/" + bookId + "/chapter-list.txt"));
            ChapterMeta meta = findChapterMeta(chapterRows, chId);
            List<Page> pages = getPages(meta.getNumPages(), bookId, chId);
            return new Chapter().withPages(pages).withChapterMeta(meta);
        } catch (NotFoundException ex) {
            log.info("Could not get chapter from: " + "bookId: " + bookId + " chId: " + chId);
            throw ex;
        } catch (IOException ex) {
            log.info("Could not get chapter from: " + "bookId: " + bookId + " chId: " + chId);
            throw new NotFoundException("Could not get chapter from: " + "bookId: " + bookId + " chId: " + chId, ex);
        }
    }

    @Override
    public List<ChapterMeta> readChapterList(int bookId) {
        try {
            List<String> chapterRows = Files.readAllLines(
                    Paths.get("src/main/resources/book/" + bookId + "/chapter-list.txt"));
            List<ChapterMeta> metas = new ArrayList<>();
            for (String chMeta : chapterRows) {
                metas.add(chapterMetaMapper(chMeta));
            }
            return metas;
        } catch (IOException ex) {
            log.info("Could not get chapter list  for bookId: " + bookId );
            throw new NotFoundException("Could not get chapter list  for bookId: " + bookId  , ex);
        }
    }

    private ChapterMeta findChapterMeta(List<String> rows, int chId) {
        for (String meta : rows) {
            ChapterMeta chapter = chapterMetaMapper(meta);
            if (chId == chapter.getNum()) {
                return chapter;
            }
        }
        throw new NotFoundException("Could not find chapter:" + chId + " meta.", null);
    }

    @Override
    public List<BookMark> readBookMarks(String path) {
        return null;
    }

    private ChapterMeta chapterMetaMapper(String csv) {
        String[] split = csv.split(",");
        return new ChapterMeta()
                .withNum(Integer.parseInt(split[0]))
                .withNumPages(Integer.parseInt(split[1]))
                .withName(split[2]);
    }

    private List<Page> getPages(int numPages, int bookId, int chId) throws IOException {
        List<Page> pages = new ArrayList<>();
        for (int i = 1; i <= numPages; i++) {
            List<String> pageLines = Files.readAllLines(
                    Paths.get("src/main/resources/book/" + bookId + "/" + chId + "/" + i + ".txt"));
            pages.add(new Page(pageLines, i));
        }
        return pages;
    }

    private Book bookMapper(String csv) {
        String[] split = csv.split(",");
        return new Book(split[1])
                .withId(Integer.parseInt(split[0]))
                .withAuthor(split[2])
                .withPublisher(split[3])
                .withDatePublished(LocalDate.parse(split[4]))
                .withIsbn(Arrays.asList(
                        new Isbn(split[5], IsbnFormat.ISBN10),
                        new Isbn(split[6], IsbnFormat.ISBN13)
                ));

    }
}
