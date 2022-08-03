package org.example.bank;

import org.example.bank.db.BankAccountRepository;
import org.example.bank.model.BankAccount;
import org.example.bank.model.BankAccountBuilderService;
import org.example.shared.io.UserInputService;
import org.example.shared.io.UserOutputService;
import org.example.shared.io.console.ConsoleUserInputServiceImpl;
import org.example.shared.io.console.ConsoleUserOutputServiceImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Menu {
    UserOutputService userOutputService;
    UserInputService userInputService;
    EntityManagerFactory entityManagerFactory;
    EntityManager entityManager;
    BankAccountRepository bar ;

    public Menu(UserOutputService userOutputService, UserInputService userInputService) {
        this.userOutputService = userOutputService;
        this.userInputService = userInputService;
        entityManagerFactory = Persistence.createEntityManagerFactory("example");
        entityManager = entityManagerFactory.createEntityManager();
        bar = new BankAccountRepository(entityManager);
    }

    public String createMenu() {
        String selection = userInputService.getUserChoice("Enter a selection from the menu above: ",
                "Add new bank account",
                "Withdraw",
                "Deposit",
                "Check balance",
                "Save and Exit");
        return selection;
    }

    //        // create EntityManager (copy paste this when needed)
//        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
//        BankAccountRepository bar = new BankAccountRepository(entityManager);
//        // access transaction object (copy paste this when needed)
//
//        // create and use transactions (copy paste this when needed)
//
//        // Part 1D - Insert
//        bar.save(a1); // IMPORTANT!
//        //System.out.println(bar.findById(632L).get().checkBalance());
//

    public void runMenu() {
        userOutputService.print("Welcome to Our Bank!");

        EntityTransaction transaction = entityManager.getTransaction();


        boolean running = true;
        while (running) {
            String selection = createMenu();
            switch (selection) {
                case "Add new bank account":
                    BankAccountBuilderService bankBuilder = new BankAccountBuilderService(userInputService);
                    BankAccount a1 = bankBuilder.createAccount();
                    transaction.begin();
                    bar.save(a1);
                    userOutputService.print("new account number: " + a1.getAccountId());
                    transaction.commit();
                    break;
                case "Withdraw":
                    Long withdrawAcct = Long.parseLong(userInputService.getUserInput("Enter account id to withdraw from: "));
                    BankAccount withdrawAccount = bar.findById(withdrawAcct).get();
                    Long withdrawal = Long.parseLong(userInputService.getUserInput("Enter amt to withdraw: "));
                    System.out.println(withdrawal);
                    transaction.begin();
                    if (withdrawAccount.withdraw(withdrawal)) {
                        userOutputService.print("Withdraw successful.  Your current balance is " + withdrawAccount.getBalance());
                    }
                    else {
                        userOutputService.print("You have overdrafted.  You have been charged $100.  " +
                                "Your current balance is " + withdrawAccount.getBalance());
                    }
                    bar.save(withdrawAccount);
                    transaction.commit();
                    break;
                case "Check Balance":
//                    Long depositAcct = Long.parseLong(userInputService.getUserInput("Enter account id to deposit to: "));
//                    BankAccount bankAccount = bar.findById(depositAcct).get();
//                    Long withdrawal = Long.parseLong(userInputService.getUserInput("Enter amt to withdraw: "));
//                    System.out.println(withdrawal);
//                    transaction.begin();
//                    if (bankAccount.withdraw(withdrawal)) {
//                        userOutputService.print("Withdraw successful.  Your current balance is " + bankAccount.getBalance());
//                    }
//                    else {
//                        userOutputService.print("You have overdrafted.  You have been charged $100.  " +
//                                "Your current balance is " + bankAccount.getBalance());
//                    }
//                    bar.save(bankAccount);
//                    transaction.commit();
                    break;
                default:
                    userOutputService.print("Oh no something went wrong!");
            }
        }


    }

}
