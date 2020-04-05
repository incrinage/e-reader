package book;

public enum IsbnFormat {
    ISBN10("ISBN-10"), ISBN13("ISBN-13");
    private String isbnFormat;

    IsbnFormat(String s) {
        this.isbnFormat = s;
    }

    public String getIsbnFormat() {
        return isbnFormat;
    }
}
