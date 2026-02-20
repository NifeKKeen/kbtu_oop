package practice3.delivery.model;

public class Truck extends Vehicle {
    protected double maxLoad;

    public Truck(String model, double baseCost, Engine engine, double maxLoad) {
        super(model, baseCost, engine);
        this.maxLoad = maxLoad;
    }

    public double calculateDeliveryCost() {
        return this.baseCost * .005;
    }

    public double calculateDeliveryCost(double distance, double fuelPrice) {
        return (this.baseCost * .001 + distance * .1) * (Math.max(1, (fuelPrice / 5)));
    }

    public String getVehicleInfo() {
        return String.format("Truck<%s>", this.getInfoProps());
    }

    public String getInfoProps() {
        return super.getInfoProps() + String.format(",maxLoad=%.2f", maxLoad);
    }
}
