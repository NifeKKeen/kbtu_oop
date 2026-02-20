package practice3.delivery.app;

import practice3.delivery.model.Car;
import practice3.delivery.model.Engine;
import practice3.delivery.model.Truck;
import practice3.delivery.model.Vehicle;
import practice3.delivery.service.DeliveryService;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        Engine e1 = new Engine(10),
               e2 = new Engine(66.9);

        Car c1 = new Car("Kanocho", 100_000, e1, 5);
        Truck t1 = new Truck("Adiles", 370_000, e2, 20_000);

        List<Vehicle> arr = new ArrayList<>();
        arr.add(c1);
        arr.add(t1);

        System.out.printf("Delivery cost is %.2f%n", DeliveryService.calculateAllDeliveries(arr));
        System.out.printf("Total cost is %.2f%n", DeliveryService.calculateTotalCost(arr));
        DeliveryService.printAllVehicles(arr);
    }
}
