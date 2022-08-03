package org.example.bank.service;

public interface BankAccountService {
//* The user can deposit money.
    void deposit(long deposit);
//* The user can withdraw money.
    boolean withdraw(long withdraw);
//* The user can see how much money is in their account.
    long checkBalance();
}
