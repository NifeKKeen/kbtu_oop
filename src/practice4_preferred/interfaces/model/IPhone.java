package practice4_preferred.interfaces.model;

import practice4.problem3.SellableAndPluggable;

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
