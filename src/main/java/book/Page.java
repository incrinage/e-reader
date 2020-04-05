package book;

import java.util.ArrayList;
import java.util.List;

public class Page {
    private List<String> lines;
    private int pageNum;

    public Page() {
        lines = new ArrayList<>();
        pageNum = 0;
    }

    public Page(List<String> lines, int pageNum) {
        this.lines = lines;
        this.pageNum = pageNum;
    }

    public List<String> getLines() {
        return this.lines;
    }

    public int getNumLines() {
        return this.lines.size();
    }

    public int getPageNumber() {
        return this.pageNum;
    }
}
