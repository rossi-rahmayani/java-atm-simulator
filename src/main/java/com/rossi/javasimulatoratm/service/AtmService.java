package com.rossi.javasimulatoratm.service;

import com.rossi.javasimulatoratm.exception.ValidationException;
import com.rossi.javasimulatoratm.model.Account;
import com.rossi.javasimulatoratm.repository.AccountRepository;
import java.util.Scanner;

import static com.rossi.javasimulatoratm.common.GlobalConstant.*;

public class AtmService{
    private ValidationService validationService = new ValidationService();
    private AccountRepository accountRepository = new AccountRepository();
    private WithdrawService withdrawService = new WithdrawService();
    private TransferService transferService = new TransferService();
    Scanner input = new Scanner(System.in);

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
            Account account = accountRepository.findByAccountNumberAndPin(accountNumber, pin);
            transactionScreen(account);
        }
        catch (ValidationException e){
            System.out.println(e.getMessage());
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
            case WITHDRAW_OPTION:
                redirectMenu(withdrawService.withdrawScreen(account), account);
                break;
            case TRANSFER_FUND_OPTION:
                redirectMenu(transferService.transferScreen(account), account);
                break;
            case EXIT_OPTION:
                welcomeScreen();
                break;
            default:
                transactionScreen(account);
        }
    }

    private void redirectMenu(String menuFlag, Account account){
        switch (menuFlag){
            case MAIN_TRANSACTION_MENU:
                transactionScreen(account);
                break;
            case WELCOME:
                welcomeScreen();
                break;
        }
    }




}
