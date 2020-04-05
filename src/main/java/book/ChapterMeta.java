package book;

public class ChapterMeta {
    private String name;
    private int num;
    private int numPages;

    public String getName() {
        return name;
    }

    public ChapterMeta withName(String name) {
        this.name = name;
        return this;
    }

    public int getNum() {
        return num;
    }

    public ChapterMeta withNum(int num) {
        this.num = num;
        return this;
    }

    public int getNumPages() {
        return numPages;
    }

    public ChapterMeta withNumPages(int numPages) {
        this.numPages = numPages;
        return this;
    }

    @Override
    public String toString() {
        return num + "," + numPages + "," + name;
    }
}
