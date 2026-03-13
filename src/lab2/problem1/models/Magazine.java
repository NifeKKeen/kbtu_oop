package lab2.problem1.models;

public class Magazine extends LibraryItem {
    private int numOfPages;
    public Magazine(String title, String author, int publicationYear, int numOfPages) {
        super(title, author, publicationYear);
        this.numOfPages = numOfPages;
    }

    @Override
    public String toString() {
        return String.format("%s (%d pages) by %s (%d)", title, numOfPages, author, publicationYear);
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }
}
