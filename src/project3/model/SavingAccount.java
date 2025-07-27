package project3.model;

import project3.exceptions.InsufficientBalance;

import java.io.Serializable;

public class SavingAccount extends Account{

    public SavingAccount(int num, BankCustomer holder){
        super(num, holder);
    }

    @Override
    public void withdraw(double amount) throws InsufficientBalance{
        if (amount <= 0){
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (amount < balance){
            throw new IllegalArgumentException("Insufficient balance for withdrawal");
        }
        balance -= amount;
        addTransaction("Withdrawal of R$ " + String.format("%.2f", amount));
    }
    @Override
    public void deposit(double amount) throws InsufficientBalance {
        if (amount <= 0){
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        addTransaction("Deposit of R$ " + String.format("%.2f", amount));
    }
}
