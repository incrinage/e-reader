import book.Book;
import book.Chapter;
import book.ChapterMeta;
import book.Page;
import reader.Command;
import reader.ReaderService;

import java.util.List;
import java.util.Scanner;

//TODO mustDo: read by refactoring this class, there is a lot of redundant code
//TODO mustDo: bookmarks
//TODO mustDo: display page out of all page count
//TODO mustDo: show number of pages next to chapter
public class DisplayController {

    private ReaderService readerService;
    private Display display = new Display();
    private Scanner scanner = new Scanner(System.in);
    private DisplayService service = new DisplayService();

    public DisplayController(ReaderService readerService) {
        this.readerService = readerService;
    }

    public Command displayPage(int bookId, int chId, int page) {
        Chapter ch = readerService.getChapter(bookId, chId);
        Command pageMenuCommand = null;
        while (pageMenuCommand == null) {
            List<Page> pages = ch.getPages();
            display.displayPage(pages.get(page));
            System.out.println("\nEnter a page number or type P for prev or N for next Page or C for chapter list or E to exit");
            pageMenuCommand = getPageMenuCommand(pages.size());
        }
        return pageMenuCommand;
    }

    public Command listChapters(int bookId) {
        Command chapterMenuCommand = null;
        while (chapterMenuCommand == null) {
            List<ChapterMeta> metas = readerService.getChapterList(bookId);
            display.displayChapters(metas);
            System.out.println("\nEnter a chapter number or type M to go to the main menu or E to exit");
            chapterMenuCommand = getChapterMenuCommand(metas.size());
        }
        return chapterMenuCommand;
    }

    public Command bookListMenu() {
        Command bookMenuCommand = null;
        while (bookMenuCommand == null) {
            List<Book> books = readerService.getBooks();
            display.displayBookList(books);
            System.out.println("\nEnter a book number or type M to go to the main menu or E to exit");
            bookMenuCommand = getBookMenuCommand(books.size());
        }
        return bookMenuCommand;
    }

    private Command getBookMenuCommand(int numBooks) {
        Command command = null;
        if (!scanner.hasNextInt()) {
            String s = scanner.nextLine();
            if (s.length() == 1) {
                switch (s.charAt(0)) {
                    case 'M':
                        command = this::bookListMenu;
                        break;
                    case 'E':
                        command = () -> null;
                        break;
                    default:
                        System.out.println("Not a valid choice.");
                }
            }
        } else {
            int b = scanner.nextInt();
            scanner.nextLine();
            if (b < 1 || b > numBooks) {
                System.out.println("Not a valid book.");
                return null;
            }
            service.book = b;
            service.page = 0;
            service.chapter = 1;
            command = () -> listChapters(service.book);
        }
        return command;
    }

    private Command getChapterMenuCommand(int numChapters) {
        Command command = null;
        if (!scanner.hasNextInt()) {
            String s = scanner.nextLine();
            if (s.length() == 1) {
                switch (s.charAt(0)) {
                    case 'M':
                        command = this::bookListMenu;
                        break;
                    case 'E':
                        command = () -> null;
                        break;
                    default:
                        System.out.println("Not a valid choice.");
                }
            }
        } else {
            int c = scanner.nextInt();
            scanner.nextLine();

            if (c > numChapters || c < 1) {
                System.out.println("Not a valid chapter.");
                return null;
            }
            service.chapter = c;
            service.page = 0;
            command = () -> displayPage(service.book, service.chapter, service.page);
        }
        return command;
    }

    private Command getPageMenuCommand(int numPages) {
        Command command = null;
        if (!scanner.hasNextInt()) {
            String s = scanner.nextLine();
            if (s.length() == 1) {
                switch (s.charAt(0)) {
                    case 'N':
                        int next = service.page + 1;
                        service.page = next < numPages ? next : service.page;
                        command = displayPage(service.book, service.chapter, service.page);
                        break;
                    case 'P':
                        int prev = service.page - 1;
                        service.page = prev >= 0 ? prev : service.page;
                        command = displayPage(service.book, service.chapter, service.page);
                        break;
                    case 'C':
                        command = listChapters(service.book);
                        break;
                    case 'E':
                        command = () -> null;
                        break;
                    default:
                        System.out.println("Not a valid choice.");
                }
            }
        } else {
            int p = scanner.nextInt();
            scanner.nextLine();
            if (p > numPages || p < 1) {
                System.out.println("Not a page choice out of range.");
                return null;
            }
            service.page = p;
            command = () -> displayPage(service.book, service.chapter, service.page);
        }
        return command;
    }

    //TODO niceToHave: appropriate space formatter
    private void formatPage() {
    }

}
