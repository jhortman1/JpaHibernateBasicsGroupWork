package org.example.bank;

import org.example.bank.db.BankAccountRepository;
import org.example.bank.model.BankAccount;
import org.example.bank.model.BankAccountBuilderService;
import org.example.shared.io.UserInputService;
import org.example.shared.io.UserOutputService;

public class Menu {
    UserOutputService userOutputService;
    UserInputService userInputService;

    BankAccountRepository bar ;

    public Menu(UserOutputService userOutputService, UserInputService userInputService) {
        this.userOutputService = userOutputService;
        this.userInputService = userInputService;
        bar = new BankAccountRepository();
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

    public void runMenu() {
        userOutputService.print("Welcome to Our Bank!");

        boolean running = true;
        while (running) {
            String selection = createMenu();
            switch (selection) {
                case "Add new bank account":
                    BankAccountBuilderService bankBuilder = new BankAccountBuilderService(userInputService);
                    BankAccount a1 = bankBuilder.createAccount();
                    bar.save(a1);
                    userOutputService.print("new account number: " + a1.getAccountId());
                    break;

                case "Withdraw":
                    Long withdrawAcctNum = Long.parseLong(userInputService.getUserInput("Enter account id to withdraw from: "));
                    BankAccount withdrawAccount = bar.findById(withdrawAcctNum).get();
                    Long withdrawal = Long.parseLong(userInputService.getUserInput("Enter amt to withdraw: "));
                    if (withdrawAccount.withdraw(withdrawal)) {
                        userOutputService.print("Withdraw successful.  Your current balance is " + withdrawAccount.getBalance());
                    }
                    else {
                        userOutputService.print("You have overdrafted.  You have been charged $100.  " +
                                "Your current balance is " + withdrawAccount.getBalance());
                    }
                    bar.save(withdrawAccount);
                    break;

                case "Deposit":
                    Long depositAcctNum = Long.parseLong(userInputService.getUserInput("Enter account id to deposit to: "));
                    BankAccount depositAccount = bar.findById(depositAcctNum).get();
                    Long deposit = Long.parseLong(userInputService.getUserInput("Enter amt to deposit: "));

                    depositAccount.deposit(deposit);
                    userOutputService.print("You have deposited " + deposit + " into your account." +
                                "  Your current balance is " + depositAccount.getBalance());
                    bar.save(depositAccount);
                    break;

                case "Check balance":
                    Long checkAcctNum = Long.parseLong(userInputService.getUserInput("Enter account id to get info: "));
                    BankAccount checkAccount = bar.findById(checkAcctNum).get();

                    userOutputService.print("Your account balance is " + checkAccount.getBalance());
                    break;

                case "Save and Exit":
                    running = false;
                    break;

                default:
                    userOutputService.print("Oh no something went wrong!");
            }
        }


    }

}
