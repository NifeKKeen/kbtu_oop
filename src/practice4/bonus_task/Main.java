package practice4.bonus_task;

public class Main {
    public static void main(String[] args) {
        CountSort cs = new CountSort(0, 9);
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0,5,2,4,1,2,2,2,1,1,1,1,5,6,6,6,9};
        cs.sort(arr);
        for (int i = 0; i < arr.length; i++)
            System.out.printf("%d ", arr[i]);
    }
}
