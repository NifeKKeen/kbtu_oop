package lab2.problem1.models;

public class Sphere extends Shape3D {
    private double radius;
    public Sphere(double radius) {
        this.radius = radius;
    }
    @Override
    public double volume() {
        return 4.0 / 3.0 * Math.PI * radius * radius * radius;
    }
    @Override
    public double surfaceArea() {
        return 4.0 * Math.PI * radius * radius;
    }
}
