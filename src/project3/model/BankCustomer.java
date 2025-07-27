package project3.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BankCustomer implements Serializable{
    private String name;
    private String cpf;
    private List<Account> accountList;

    public BankCustomer(String name, String cpf) {
        this.name = name;
        this.cpf = cpf;
        this.accountList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public List<Account> getAccountList() {
        return accountList;
    }

    public void addNewAccount(Account account) {
        accountList.add(account);
    }

    public void listAccounts() {
        System.out.println("customer account: " + name + ": ");
        for (Account account : accountList) {
            System.out.println("- Account nº " + account.getNum() + " | Balance: R$ " + account.getBalance() + String.format("%.2f", account.getBalance()));
        }
    }

    @Override
    public String toString() {
        return "Customer[Name: " + name + ", CPF: " + cpf + ", Acconts: " + accountList.size() + "]";
    }
}
