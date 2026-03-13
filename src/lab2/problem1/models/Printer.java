package lab2.problem1.models;

public class Printer {
    protected String text;
    public Printer(String text) {
        this.text = text;
    }
    public void print() {
        System.out.println(text);
    }

    public boolean equals(Object o) {
        return o instanceof Printer &&
                o.toString().equals(this.toString());
    }
    public int hashCode() {
        return this.toString().hashCode();
    }

    public String toString() {
        return String.format("Printer with text:@%s@", text);
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
