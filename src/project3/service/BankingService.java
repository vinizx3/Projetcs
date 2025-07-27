package project3.service;


import project3.exceptions.AccountNotFound;
import project3.exceptions.InsufficientBalance;
import project3.model.Account;
import project3.model.BankCustomer;
import project3.model.CurrentAccount;
import project3.model.SavingAccount;


import java.io.*;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class BankingService{
    private List<BankCustomer> customerList;
    private List<Account> accountList;
    private int accountNumber = 1;

    public BankingService(){
        this.accountList = new ArrayList<>();
        this.customerList = new ArrayList<>();
    }

    public List<BankCustomer> getCustomerList(){
        return customerList;
    }
    public List<Account> getAccountList(){
        return accountList;
    }

    public void registerCustomer(String name, String cpf){
        BankCustomer existing = searchCustomer(cpf);
        if (existing != null) {
            System.out.println("Customer with CPF already registered");
            return;
        }
        BankCustomer newCustomer = new BankCustomer(name, cpf);
        customerList.add(newCustomer);
        System.out.println("Successfully customer registered");
    }
    public Account newCurrentAccount(String cpf){
        BankCustomer customer = searchCustomer(cpf);
        if (customer == null) {
            System.out.println("Customer with CPF not found");
            return null;
        }
        CurrentAccount newCurrentAccount = new CurrentAccount(accountNumber++, customer);
        customer.addNewAccount(newCurrentAccount);
        accountList.add(newCurrentAccount);
        System.out.println("Successfully Current account created. Nº: " + newCurrentAccount.getNum());
        return newCurrentAccount;
    }
    public Account newSavingAccount(String cpf){
        BankCustomer customer = searchCustomer(cpf);
        if (customer == null){
            System.out.println("Customer with CPF not found");
            return null;
        }
        SavingAccount newSavingAccount = new SavingAccount(accountNumber++, customer);
        System.out.println("Successfully Current account created. Nº: " + newSavingAccount.getNum());
        return newSavingAccount;
    }

    public BankCustomer searchCustomer(String cpf){
        for (BankCustomer customer : customerList){
            if (customer.getCpf().equals(cpf)){
                return customer;
            }
        }
        return null;
    }
    public Account searchAccount(int accountNumber){
        for (Account account : accountList){
            if (account.getNum() == accountNumber){
                return account;
            }
        }
        return null;
    }

    public void withdraw(int accountNumber, double amount) throws AccountNotFound, InsufficientBalance {
        Account account = searchAccount(accountNumber);
        if (account == null){
            throw new AccountNotFound("Account not found");
        }
        account.withdraw(amount);
        System.out.println("Successfully withdraw");
    }
    public void deposit(int accountNumber, double amount) throws AccountNotFound {
        Account account = searchAccount(accountNumber);
        if (account == null){
            throw new AccountNotFound("Account not found");
        }
        try {
            account.deposit(amount);
            System.out.println("Successfully deposit");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void transfer(int sourceAccount, int destinationAccount, double amount) throws AccountNotFound, InsufficientBalance {
        Account source = searchAccount(sourceAccount);
        Account destination = searchAccount(destinationAccount);
        if (source == null || destination == null){
            throw new AccountNotFound("Source account or destination account not found");
        }

        source.transfer(destination, amount);
        System.out.println("successful transfer");
    }

    public void listCustomers(){
        if (customerList.isEmpty()){
            System.out.println("No registered customers");
            return;
        }
        System.out.println(" ==== List Customers ==== ");
        for (BankCustomer customer : customerList){
            System.out.println("Name: " + customer.getName() + " | CPF: " + customer.getCpf());
        }
    }
    public void listAccounts(){
        if (accountList.isEmpty()){
            System.out.println("No account registered");
            return;
        }
        for (Account account : accountList) {
            System.out.println("Number Account: " + account.getNum() + "\nType: " + account.getClass().getSimpleName() + "\nHolder: " + account.getHolder() + "\nBalance: " + String.format("%.2f", account.getBalance()));
        }
    }
    public void displayExtract(int accountNumber){
        Account account = searchAccount(accountNumber);
        if (account == null){
            System.out.println("Account not found");
            return;
        }
        account.displayExtract();
    }

    public void saveData(String data){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(data))){
            outputStream.writeObject(customerList);
            outputStream.writeObject(accountList);
            outputStream.writeInt(accountNumber);
            System.out.println("Successfully data save");
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    public void loadData(String data){
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(data))){
            customerList = (List<BankCustomer>) inputStream.readObject();
            accountList = (List<Account>) inputStream.readObject();
            accountNumber = inputStream.readInt();
            System.out.println("Successfully data load");
        } catch (FileNotFoundException e){
            System.out.println("No data found, starting new database");
            customerList = new ArrayList<>();
            accountList = new ArrayList<>();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public List<BankCustomer> getCustomers(){
        return customerList;
    }
    public List<Account> getAccounts(){
        return accountList;
    }
}

