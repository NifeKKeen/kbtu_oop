package lab2.problem1.models;

public class FilePrinter extends Printer {
    protected String fileName;
    public FilePrinter(String text, String fileName) {
        super(text);
        this.fileName = fileName;
    }

    @Override
    public void print() {
        System.out.println("filename: " + fileName);
        System.out.println(text);
    }

    @Override
    public String toString() {
        return String.format("File printer with filename @%s@ and text @%s@", fileName, text);
    }
    @Override
    public boolean equals(Object o) {
        return o instanceof FilePrinter &&
                o.toString().equals(this.toString());
    }
    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
