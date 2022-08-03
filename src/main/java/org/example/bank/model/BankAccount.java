package org.example.bank.model;

import lombok.Getter;
import lombok.Setter;
import org.example.bank.service.BankAccountService;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Table
@Setter
@Getter
@Entity
public class BankAccount implements BankAccountService {
    @Id
    @GeneratedValue
    private Long accountId;
    private String ownerName;
    private String ownerAddress;
    private long balance;

    @Override
    public void deposit(long deposit) {
        this.balance += deposit;
    }

    @Override
    public boolean withdraw(long withdraw) {
        this.balance -= withdraw;
        if(withdraw <= this.balance)
        {
            return true;
        }
        else {
            this.balance -= 100;
            return false;
        }
    }

    @Override
    public long checkBalance() {
        return this.balance;
    }
}
