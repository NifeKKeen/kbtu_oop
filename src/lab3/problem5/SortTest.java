package lab3.problem5;

public class SortTest {
    public static void main(String[] args) {
        Chocolate[] chocolates = {
                new Chocolate("Bounty", 55.0),
                new Chocolate("Twix", 50.0),
                new Chocolate("Mars", 45.0)
        };
        Sort.heapSort(chocolates);
        System.out.println("Sorted Chocolates: " + java.util.Arrays.toString(chocolates));

        Time[] times = {
                new Time(23, 5, 6),
                new Time(4, 24, 33),
                new Time(12, 0, 0)
        };
        Sort.heapSort(times);
        System.out.println("Sorted Times: " + java.util.Arrays.toString(times));
    }
}
