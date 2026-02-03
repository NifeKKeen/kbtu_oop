package lab2;

class Time {
    int hour, minute, second;
    Time(int hour, int minute, int second) {
        if (!(0 <= hour && hour <= 23)) {
            throw new IllegalArgumentException("Hour must be in range [0, 23]");
        }
        if (!(0 <= minute && minute <= 59)) {
            throw new IllegalArgumentException("Minute must be in range [0, 59]");
        }
        if (!(0 <= second && second <= 59)) {
            throw new IllegalArgumentException("Second must be in range [0, 59]");
        }

        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    String toUniversal() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
    String toStandard() {
        if (hour < 12) {
            return String.format("%02d:%02d:%02d AM", hour, minute, second);
        } else {
            return String.format("%02d:%02d:%02d AM", hour - 12, minute, second);
        }
    }
    void add(Time otherTime) {
        this.hour += otherTime.hour;
        this.minute += otherTime.minute;
        this.second += otherTime.second;

        this.minute += this.second / 60;
        this.second %= 60;

        this.hour += this.minute / 60;
        this.minute %= 60;

        this.hour %= 24;
    }
}

public class Task3 {
    public static void solution() {
        System.out.println("---- Task 3 ----");
        Time t = new Time(23, 5, 6);
        System.out.println(t.toUniversal());
        System.out.println(t.toStandard());
        Time t2 = new Time(4, 24, 33);
        t.add(t2);
        System.out.println(t.toUniversal());
    }
}
