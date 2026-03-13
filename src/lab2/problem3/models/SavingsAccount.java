package lab2.problem3.models;

public class SavingsAccount extends Account {
    protected double interest;
    public SavingsAccount(int a, double i) {
        super(a);
        interest = i;
    }

    public void addInterest() {
        balance += balance * interest;
    }

    @Override
    public String toString() {
        return String.format("Account number: %d, balance: %.2f, interest rate: %.3f", accNumber, balance, interest);
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }
}
