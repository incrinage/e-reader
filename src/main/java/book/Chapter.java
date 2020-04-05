package book;

import java.util.List;

public class Chapter {
    private List<Page> pages;
    private ChapterMeta chapterMeta;

    public Chapter withChapterMeta(ChapterMeta chapterMeta) {
        this.chapterMeta = chapterMeta;
        return this;
    }

    public ChapterMeta getChapterMeta() {
        return this.chapterMeta;
    }

    public List<Page> getPages() {
        return pages;
    }

    public Chapter withPages(List<Page> pages) {
        this.pages = pages;
        return this;
    }


}
