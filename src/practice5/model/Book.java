package practice5.model;

import java.io.Serializable;

public class Book implements Serializable {
    String title, author;
    transient int visitCount;

    public Book(String title, String author, int visitCount) {
        this.title = title;
        this.author = author;
        this.visitCount = visitCount;
    }

    @Override
    public String toString() {
        return String.format("Book<title=%s, author=%s, visitCount=%d>", title, author, visitCount);
    }
}
