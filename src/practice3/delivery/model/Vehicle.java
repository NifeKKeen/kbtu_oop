package practice3.delivery.model;

abstract public class Vehicle {
    protected String model;
    protected double baseCost;
    Engine engine;

    public Vehicle(String model, double baseCost, Engine engine) {
        this.model = model;
        this.baseCost = baseCost;
        this.engine = engine;
    }

    public double calculateDeliveryCost() {
        return baseCost * .01;
    }

    public String getVehicleInfo() {
        return String.format("Vehicle<%s>", this.getInfoProps());
    }

    public String getInfoProps() {
        return String.format("model=\"%s\",baseCost=%.2f", model, baseCost);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(double baseCost) {
        this.baseCost = baseCost;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
