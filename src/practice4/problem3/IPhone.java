package practice4.problem3;

public class IPhone implements SellableAndPluggable {
    @Override
    public void plug() {
        System.out.println("Plugging IPhone");
    }

    @Override
    public void sell() {
        System.out.println("Selling IPhone. Privet Android!!!");
    }
}
