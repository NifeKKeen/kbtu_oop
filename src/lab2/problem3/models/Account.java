package lab2.problem3.models;

public class Account {
    protected double balance; // The current balance
    protected int accNumber; // The account number
    private boolean isOpened = false;

    public Account(int a) {
        balance = 0.0;
        accNumber = a;
    }

    public void open() {
        this.isOpened = true;
    }
    public void close() {
        this.isOpened = false;
    }

    public void deposit(double sum) {
        this.balance += sum;
    }

    public boolean withdraw(double sum) {
        if (this.balance < sum) {
            return false;
        }
        this.balance -= sum;
        return true;
    }

    public double getBalance() {
        return this.balance;
    }

    public double getAccountNumber() {
        return this.accNumber;
    }

    public boolean transfer(double amount, Account other) {
        if (this.balance < amount) {
            return false;
        }
        this.balance -= amount;
        other.deposit(amount);
        return true;
    }

    public String toString() {
        return String.format("Account number: %d, balance: %.2f", accNumber, balance);
    }

    public final void print() {
        // Do not override this method, override the toString method
        System.out.println(toString());
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }
}
