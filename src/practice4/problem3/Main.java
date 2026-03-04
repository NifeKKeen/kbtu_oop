package practice4.problem3;

public class Main {
    public static void main(String[] args) {
        App app = new App();
        app.getStatistics(new LogicGame());

        IPhone iphone = new IPhone();
        iphone.plug();
        iphone.sell();
    }
}
