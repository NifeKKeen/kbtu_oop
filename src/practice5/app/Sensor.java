package practice5.app;

import java.io.*;

public class Sensor {
    private static final String SENSOR_DATA_PATH = "src/practice5/data/sensor.bin";
    private static final int RECORD_COUNT = 60;

    public static void main(String[] args) {
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(SENSOR_DATA_PATH))) {
            for (int i = 0; i < RECORD_COUNT; i++) {
                double temp = 15.0 + (Math.random() * 20.0);
                dos.writeDouble(temp);
            }
        } catch (IOException e) {
            System.err.println("Error writing: " + e.getMessage());
        }

        try (RandomAccessFile raf = new RandomAccessFile(SENSOR_DATA_PATH, "rw")) {
            long offset = 29 * 8;
            raf.seek(offset);
            raf.writeDouble(999.9);
        } catch (IOException e) {
            System.err.println("Error patching: " + e.getMessage());
        }

        try (DataInputStream dis = new DataInputStream(new FileInputStream(SENSOR_DATA_PATH))) {
            double sum = 0;
            double mx = Double.NEGATIVE_INFINITY;

            for (int i = 0; i < RECORD_COUNT; i++) {
                double val = dis.readDouble();
                sum += val;
                if (val > mx) mx = val;
            }

            double avg = sum / RECORD_COUNT;
            System.out.printf("Average Temperature: %.2f°C\n", avg);
            System.out.printf("Max Temperature: %.2f°C\n", mx);
        } catch (IOException e) {
            System.err.println("Error reading: " + e.getMessage());
        }
    }
}
