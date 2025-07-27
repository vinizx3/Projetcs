package project3.Interface;

import project3.exceptions.InsufficientBalance;
import project3.model.Account;

public interface Transactional {
    void deposit(Double amount);
    void withdraw(Double amount) throws InsufficientBalance;
    void transfer(Account destination, double amount) throws InsufficientBalance;
}
