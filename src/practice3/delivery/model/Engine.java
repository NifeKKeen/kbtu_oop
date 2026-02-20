package practice3.delivery.model;

public class Engine {
    private double horsepower;

    public Engine(double horsepower) {
        this.horsepower = horsepower;
    }

    String getEngineInfo() {
        return String.format("Engine<horsepower=%.3f>", horsepower);
    }
}
