package lab2.problem3.services;

import lab2.problem3.models.Account;
import lab2.problem3.models.CheckingAccount;
import lab2.problem3.models.SavingsAccount;

import java.util.Vector;

public class Bank {
    Vector<Account> accounts = new Vector<>();

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public void update() {
        for (Account account : accounts) {
            if (account instanceof SavingsAccount) {
                ((SavingsAccount) account).addInterest();
            } else if (account instanceof CheckingAccount) {
                ((CheckingAccount) account).deductFee();
            }
        }
    }

    public void openAccount(Account account) {
        account.open();
        accounts.add(account);
    }

    public void printAllAccounts() {
        for (Account account : accounts) {
            System.out.println(account);
        }
    }
}
