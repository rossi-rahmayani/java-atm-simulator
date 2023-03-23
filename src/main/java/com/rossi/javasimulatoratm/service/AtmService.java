package com.rossi.javasimulatoratm.service;

import com.rossi.javasimulatoratm.common.BankAccount;
import com.rossi.javasimulatoratm.common.WithdrawalAmount;
import com.rossi.javasimulatoratm.model.Account;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AtmService {
    ValidationService validationService = new ValidationService();
    WithdrawService withdrawService = new WithdrawService();

    List<Account> accounts = BankAccount.getBankAccount();
    static Scanner input = new Scanner(System.in);
    public void welcomeScreen(){
        // input acc num
        System.out.print("Enter Account Number: ");
        String accountNumber = input.next();

        if (validationService.validateAccNum(accountNumber)) {
            // input pin
            System.out.print("Enter PIN: ");
            String pin = input.next();

            if (validationService.validatePin(pin)) {
                //validate account
                Optional.ofNullable(validationService.validateAccountLogin(accounts, accountNumber, pin))
                        .ifPresentOrElse(this::transactionScreen, this::welcomeScreen);
            }
            else {
                welcomeScreen();
            }
        }
        else {
            welcomeScreen();
        }
    }

    private void transactionScreen(Account account){
        System.out.println(
                "1. Withdraw\n" +
                "2. Fund Transfer\n" +
                "3. Exit"
        );
        System.out.print("Please choose option [3]: ");
        String option = input.nextLine();
        switch (option) {
            case "1":
                withdrawScreen(account);
            case "2":
                System.out.println("Transfer");
                transactionScreen(account);
            case "3", "", " ":
                welcomeScreen();
            default:
                System.out.println(option);
                transactionScreen(account);
        }
    }
    public void withdrawScreen(Account account){
        System.out.println(
                        "1. $10\n" +
                        "2. $50\n" +
                        "3. $100\n" +
                        "4. Other\n" +
                        "5. Back");
        System.out.println("Please choose option[5]: ");
        String option = input.nextLine();
        BigInteger wa = BigInteger.ZERO;

        switch (option){
            case "1","2","3":
                wa = WithdrawalAmount.findByCode(option).map(WithdrawalAmount::getAmount).orElse(BigInteger.ZERO);
            case "4":
                System.out.println("Other Withdraw");
                System.out.println("Enter amount to withdraw: ");
                String amount = input.nextLine();
                if (validationService.validateWithdrawalAmount(amount)){
                    wa = new BigInteger(amount);
                }
                else {
                    if (withdrawService.withdraw(account, wa)){
                        summaryOption(account);
                    }
                    else {
                        withdrawScreen(account);
                    }
                }
            default:
                transactionScreen(account);
        }

    }

    private void summaryOption(Account account){
        System.out.println(
                "1. Transaction \n" +
                "2. Exit");
        System.out.println("Choose option[2]:");
        String option = input.nextLine();

        if (option.equals("1")){
            transactionScreen(account);
        }
        else {
            welcomeScreen();
        }
    }




}
