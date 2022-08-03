package org.example.bank;

import org.example.bank.db.BankAccountRepository;
import org.example.bank.model.BankAccount;
import org.example.bank.model.CreateNewAccount;
import org.example.jaystuff.model.PrimaryTeacher;
import org.example.jaystuff.model.Student;
import org.example.shared.io.UserInputService;
import org.example.shared.io.UserOutputService;
import org.example.shared.io.console.ConsoleUserInputServiceImpl;
import org.example.shared.io.console.ConsoleUserOutputServiceImpl;
import org.example.shared.io.validation.NonBlankInputValidationRule;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.Instant;
import java.util.Date;
import java.util.Random;

public class BankMain {
    public static void main(String[] args) throws Exception {
//        UserOutputService userOutputService = new ConsoleUserOutputServiceImpl();
//        try (UserInputService userInputService = new ConsoleUserInputServiceImpl(userOutputService)) {
//            userOutputService.print("WELCOME");
//            int choice = Integer.parseInt(userInputService ( "Create Accout Enter 1" +
//                    "Withdraw" +
//                    "Deposit"))
//
//            swith
//            case 1:
//            new CreateNewAccount(userInputService);
//            new Withdraw(userInputService);
//            new Deposit(userInputService);
//        }

    }
}
