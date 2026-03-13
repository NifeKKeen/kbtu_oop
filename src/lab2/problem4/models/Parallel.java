package lab2.problem4.models;

public class Parallel extends Circuit {
    private Circuit c1, c2;
    private double potentialDifference;

    public Parallel(Circuit c1, Circuit c2) {
        this.c1 = c1;
        this.c2 = c2;
    }

    @Override
    public double getResistance() {
        // 1 / R = 1 / R1 + 1 / R2
        // R = (1 / (1 / R1 + 1 / R2))1
        return 1.0 / (1.0 / c1.getResistance() + 1.0 / c2.getResistance());
    }

    @Override
    public double getPotentialDiff() {
        return potentialDifference;
    }

    @Override
    public void applyPotentialDiff(double V) {
        this.potentialDifference = V;
        // V = Vi
        c1.applyPotentialDiff(V);
        c2.applyPotentialDiff(V);
    }
}
