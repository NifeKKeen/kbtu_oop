package practice3.delivery.model;

public class Car extends Vehicle {
    protected int numberOfSeats;
    public Car(String model, double baseCost, Engine engine, int numberOfSeats) {
        super(model, baseCost, engine);
        this.numberOfSeats = numberOfSeats;
    }

    public double calculateDeliveryCost() {
        return this.baseCost * .001;
    }

    public double calculateDeliveryCost(double extraWeigth) {
        return (this.baseCost + extraWeigth * 50) * .001;
    }

    public String getVehicleInfo() {
        return String.format("Car<%s>", this.getInfoProps());
    }

    public String getInfoProps() {
        return super.getInfoProps() + String.format(",numberOfSeats=%d", numberOfSeats);
    }
}
