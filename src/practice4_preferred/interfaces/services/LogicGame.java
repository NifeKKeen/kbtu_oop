package practice4_preferred.interfaces.services;

import practice4_preferred.interfaces.model.IGame;

public class LogicGame implements IGame {
    @Override
    public void a() {
        System.out.println("A");
    }

    @Override
    public void b() {
        System.out.println("B");
    }

    @Override
    public void c() {
        System.out.println("C");
    }

    @Override
    public void d() {
        System.out.println("D");
    }
}
