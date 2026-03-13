package lab2.problem4.main;

import lab2.problem4.models.Circuit;
import lab2.problem4.models.Parallel;
import lab2.problem4.models.Resistor;
import lab2.problem4.models.Series;

public class CircuitTest {
    public static void main(String[] args) {
        Circuit r1 = new Resistor(7.0);
        Circuit r2 = new Resistor(10.0);
        Circuit r3 = new Resistor(6.0);
        Circuit r4 = new Resistor(4.0);
        Circuit c1 = new Parallel(r1, r2);
        Circuit c2 = new Series(r3, r4);
        Circuit circuit = new Parallel(c1, c2);
        double R = circuit.getResistance();
        System.out.println(R);
    }
}