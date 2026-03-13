package lab2.problem3.main;

import lab2.problem3.models.Account;
import lab2.problem3.models.CheckingAccount;
import lab2.problem3.models.SavingsAccount;
import lab2.problem3.services.Bank;

public class Test {
    public static void main(String[] args) {
        Bank myBank = new Bank();
        Account basicAcc = new Account(1001);
        SavingsAccount savingsAcc = new SavingsAccount(1002, 5.0);
        CheckingAccount checkingAcc = new CheckingAccount(1003);

        myBank.openAccount(basicAcc);
        myBank.openAccount(savingsAcc);
        myBank.openAccount(checkingAcc);

        basicAcc.deposit(500.0);
        savingsAcc.deposit(1000.0);
        checkingAcc.deposit(200.0);

        checkingAcc.withdraw(10.0);
        checkingAcc.withdraw(10.0);
        checkingAcc.withdraw(10.0);
        checkingAcc.deposit(30.0);

        checkingAcc.transfer(50.0, savingsAcc);

        System.out.println("Before");
        myBank.printAllAccounts();

        myBank.update();

        System.out.println("After");
        myBank.printAllAccounts();
    }
}
