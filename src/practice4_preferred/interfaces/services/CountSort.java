package practice4_preferred.interfaces.services;

public class CountSort {
    private int minInt = 0, maxInt = 9;
    public CountSort(int minInt, int maxInt) {
        this.minInt = minInt;
        this.maxInt = maxInt;
    }
    void sort(int[] arr) {
        int[] cnt = new int[maxInt - minInt + 1];

        for (int i = 0; i < arr.length; i++) {
            cnt[arr[i] - minInt]++;
        }
        int it = 0;
        for (int val = minInt; val <= maxInt; val++) {
            while (cnt[val - minInt] > 0) {
                arr[it++] = val;
                cnt[val - minInt]--;
            }
        }
    }
}
