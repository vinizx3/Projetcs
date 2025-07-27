package project3.model;

import project3.exceptions.InsufficientBalance;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class Account implements Serializable{
    protected int num;
    protected BankCustomer holder;
    protected double balance;
    protected List<String> transactionHistory;

    public Account(int num, BankCustomer holder){
        this.num = num;
        this.holder = holder;
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public int getNum(){
        return num;
    }
    public BankCustomer getHolder(){
        return holder;
    }
    public double getBalance(){
        return balance;
    }
    public List<String> getTransactionHistory(){
        return transactionHistory;
    }

    public void addTransaction(String description) throws InsufficientBalance{
        String dateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        transactionHistory.add("[" + dateTime + "]" + description);
    }
    public void displayExtract(){
        System.out.println(" ==== Extract Account " + num + " ==== ");
        for (String transition : transactionHistory){
            System.out.println(transition);
        }
    }
    public void transfer(Account source, double amount) throws InsufficientBalance {
        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }

        this.withdraw(amount);
        source.deposit(amount);

        this.addTransaction("Transfer sent of R$ " + amount + " to account " + source.getNum());
        source.addTransaction("Received transfer of R$ " + amount + " of the account " + this.getNum());
    }

    public abstract void withdraw(double amount) throws InsufficientBalance;
    public abstract void deposit(double amount) throws InsufficientBalance;
}
