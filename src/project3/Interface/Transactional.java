package project3.Interface;

import project3.exceptions.InsufficientBalance;
import project3.model.Account;

public interface Transactional {
    void deposit(double amount) throws InsufficientBalance;
    void withdraw(double amount) throws InsufficientBalance;
    void transfer(Account destination, double amount) throws InsufficientBalance;
}
