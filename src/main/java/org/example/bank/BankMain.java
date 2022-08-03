package org.example.bank;

import org.example.shared.io.UserInputService;
import org.example.shared.io.UserOutputService;
import org.example.shared.io.console.ConsoleUserInputServiceImpl;
import org.example.shared.io.console.ConsoleUserOutputServiceImpl;

public class BankMain {
    public static void main(String[] args) throws Exception {
        UserOutputService userOutputService = new ConsoleUserOutputServiceImpl();

        try (UserInputService userInputService = new ConsoleUserInputServiceImpl(userOutputService)) {
            Menu menu = new Menu(userOutputService, userInputService);
            menu.runMenu();
        }
        catch (Exception e) {
            userOutputService.print("Oh no something went wrong!");
        }
//            userOutputService.print("WELCOME");
//            int choice = Integer.parseInt(userInputService ( "Create Accout Enter 1" +
//                    "Withdraw" +
//                    "Deposit"))
//
//            swith
//            case 1:
//            new BankAccountBuilderService(userInputService);
//            new Withdraw(userInputService);
//            new Deposit(userInputService);
//        }



    }
}
