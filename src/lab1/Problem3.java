package lab1;

class Temperature {
    public enum Scale {CELSIUS, FAHRENHEIT}
    private double value;
    private Scale scale;

    Temperature() {
        this(0, Scale.CELSIUS);
    }
    Temperature(double value) {
        this(value, Scale.CELSIUS);
    }
    Temperature(Scale scale) {
        this(0, scale);
    }
    Temperature(double value, Scale scale) {
        this.value = value;
        this.scale = scale;
    }

    public double toCelsius() {
        if (scale == Scale.CELSIUS) return value;
        else return 5 * (value - 32) / 9;
    }

    public double toFahrenheit() {
        if (scale == Scale.FAHRENHEIT) return value;
        else return 9 * (value / 5) + 32;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Scale getScale() {
        return scale;
    }

    public void setScale(Scale scale) {
        this.scale = scale;
    }
}

public class Problem3 {
    static void solution() {
        Temperature t = new Temperature(100, Temperature.Scale.CELSIUS);
        System.out.println(t.toFahrenheit());
    }
}
