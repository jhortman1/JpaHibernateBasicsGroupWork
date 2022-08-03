package org.example.bank.model;

import org.example.bank.db.BankAccountRepository;
import org.example.shared.io.UserInputService;
import org.example.shared.io.UserOutputService;
import org.example.shared.io.console.ConsoleUserInputServiceImpl;
import org.example.shared.io.console.ConsoleUserOutputServiceImpl;
import org.example.shared.io.validation.NonBlankInputValidationRule;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class CreateNewAccount {
    private UserInputService userInputService;
    public CreateNewAccount(UserInputService userInputService) {
        this.userInputService = userInputService;
    }
    public BankAccount getAccount() {
        String ownerName, ownerAddress;
        Long initialBalance;

        ownerName = userInputService.getUserInput("What's the Owner name: ",
                new NonBlankInputValidationRule());
        ownerAddress = userInputService.getUserInput("What's the Owner Address: ",
                new NonBlankInputValidationRule());
        initialBalance = Long.parseLong(userInputService.getUserInput("Enter initial balance: "));

        BankAccount a1 = new BankAccount();
        a1.setOwnerName(ownerName);
        a1.setOwnerAddress(ownerAddress);
        a1.setBalance(initialBalance);

        // create EntityManager (copy paste this when needed)
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        BankAccountRepository bar = new BankAccountRepository(entityManager);
        // access transaction object (copy paste this when needed)
        EntityTransaction transaction = entityManager.getTransaction();
        // create and use transactions (copy paste this when needed)
        transaction.begin();
        // Part 1D - Insert
        bar.save(a1); // IMPORTANT!
        //System.out.println(bar.findById(632L).get().checkBalance());
        transaction.commit();

        return a1;
    }
}
