package com.rossi.javasimulatoratm.service;

import com.rossi.javasimulatoratm.exception.ValidationException;
import com.rossi.javasimulatoratm.model.Account;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Scanner;

public class AtmService{
    ValidationService validationService = new ValidationService();
    List<Account> accounts = Account.getSampleAccounts();
    Scanner input = new Scanner(System.in);
    Random random = new Random();
    public void welcomeScreen(){
        try {
            // input acc num
            System.out.print("Enter Account Number: ");
            String accountNumber = input.nextLine();
            validationService.validateAccNum(accountNumber);

            // input pin
            System.out.print("Enter PIN: ");
            String pin = input.nextLine();
            validationService.validatePin(pin);

            //validate account
            Account account = validationService.validateAccountLogin(accounts, accountNumber, pin);
            transactionScreen(account);
        }
        catch (ValidationException e){
            System.out.println(e.getMessage());
            welcomeScreen();
        }
    }

    protected void transactionScreen(Account account){
        var withdrawService = new WithdrawService();
        var transferService = new TransferService();

        System.out.println(
                "1. Withdraw\n" +
                "2. Fund Transfer\n" +
                "3. Exit"
        );
        System.out.print("Please choose option [3]: ");
        String option = input.nextLine();
        switch (option) {
            case "1":
                withdrawService.withdrawScreen(account);
                break;
            case "2":
                transferService.transferScreen(account);
                break;
            case "3", "", " ":
                welcomeScreen();
                break;
            default:
                transactionScreen(account);
        }
    }

    protected void summaryOption(Account account){
        System.out.println(
                "1. Transaction \n" +
                "2. Exit");
        System.out.print("Choose option[2]: ");
        String option = input.nextLine();

        if (option.equals("1")){
            transactionScreen(account);
        }
        else {
            welcomeScreen();
        }
    }




}
