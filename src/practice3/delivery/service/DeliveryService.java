package practice3.delivery.service;

import practice3.delivery.model.Vehicle;

import java.util.List;

public class DeliveryService {
    public static void printAllVehicles(List<Vehicle> vehicles) {
        System.out.println("Displaying info about cars:");
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle.getVehicleInfo());
        }
    }

    public static double calculateAllDeliveries(List<Vehicle> vehicles) {
        double sum = 0;
        for (Vehicle vehicle : vehicles) {
            sum += vehicle.calculateDeliveryCost();
        }

        return sum;
    }

    public static double calculateTotalCost(List<Vehicle> vehicles) {
        double sum = 0;
        for (Vehicle vehicle : vehicles) {
            sum += vehicle.getBaseCost();
        }

        return sum;
    }
}
