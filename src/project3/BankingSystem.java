package project3;

import project3.exceptions.AccountNotFound;
import project3.exceptions.InsufficientBalance;
import project3.service.BankingService;

import java.sql.SQLOutput;
import java.util.Scanner;

public class BankingSystem {
    public static void main(String[] args) throws AccountNotFound, InsufficientBalance {
        Scanner sc = new Scanner(System.in);
        BankingService bank = new BankingService();
        int option;
        bank.loadData("Data.dat");

        do {
            System.out.println(" ==== Bank System ==== ");
            System.out.println("1. Register Customer");
            System.out.println("2. Create Current Account");
            System.out.println("3. Create Saving Account");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Transfer");
            System.out.println("7. List Customers");
            System.out.println("8. List Accounts");
            System.out.println("9. Display Extract");
            System.out.println("0. Leave");
            System.out.println("Choose an option: ");
            option = sc.nextInt();
            sc.nextLine();

            switch (option){
                case 1 -> {
                    System.out.println("Name: ");
                    String name = sc.nextLine();
                    System.out.println("CPF: ");
                    String cpf = sc.nextLine();
                    bank.registerCustomer(name, cpf);
                }
                case 2 -> {
                    System.out.println("CPF Customer: ");
                    String cpf = sc.nextLine();
                    bank.newCurrentAccount(cpf);
                }
                case 3 -> {
                    System.out.println("CPF Customer: ");
                    String cpf = sc.nextLine();
                    bank.newSavingAccount(cpf);
                }
                case 4 -> {
                    System.out.println("Account Number: ");
                    int num = sc.nextInt();
                    System.out.println("Deposit Amount: ");
                    double amount = sc.nextDouble();
                    bank.deposit(num, amount);
                }
                case 5 -> {
                    System.out.println("Account Number: ");
                    int num = sc.nextInt();
                    System.out.println("Withdraw Amount");
                    double amount = sc.nextDouble();
                    try {
                        bank.withdraw(num, amount);
                    } catch (InsufficientBalance | AccountNotFound e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                }
                case 6 -> {
                    System.out.println("Source Account: ");
                    int source = sc.nextInt();
                    System.out.println("Destination Account: ");
                    int destination = sc.nextInt();
                    System.out.println("Transfer Amount: ");
                    double amount = sc.nextDouble();
                    bank.transfer(source, destination, amount);
                }
                case 7 -> bank.listCustomers();
                case 8 -> bank.listAccounts();
                case 9 -> {
                    System.out.println("Account Number: ");
                    int num = sc.nextInt();
                    bank.displayExtract(num);
                }
                case 0 -> System.out.println("System Closed");
                default -> System.out.println("Invalid Option");
            }
        } while (option != 0);

        bank.saveData("Data.dat");
        System.out.println("Program Closed");
        sc.close();
    }
}
