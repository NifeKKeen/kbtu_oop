package lab2.problem3.models;

public class CheckingAccount extends Account {
    protected int transferCount;
    static final int FREE_TRANSACTIONS = 10;
    static final double COMISSION = 0.02;

    public CheckingAccount(int a) {
        super(a);
        transferCount = 0;
    }

    public boolean deductFee() {
        return withdraw(COMISSION);
    }
    public void undoDeductFee() {
        deposit(COMISSION);
    }

    @Override
    public boolean transfer(double amount, Account other) {
        if (this.transferCount < FREE_TRANSACTIONS) {
            this.transferCount++;
            return super.transfer(amount, other);
        } else {
            if (!deductFee()) {
                return false;
            }
            if (super.transfer(amount, other)) {
                return true;
            } else {
                undoDeductFee();
                return false;
            }
        }
    }

    @Override
    public String toString() {
        return String.format("Checking account number: %d, balance: %.2f, transfer count: %d", accNumber, balance, transferCount);
    }
}
