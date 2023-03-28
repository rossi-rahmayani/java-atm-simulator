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

    protected void transactionScreen(Account account){
        System.out.println(
                "1. Withdraw\n" +
                "2. Fund Transfer\n" +
                "3. Exit"
        );
        System.out.print("Please choose option [3]: ");
        String option = input.nextLine();
        switch (option) {
            case WITHDRAW_OPTION: // create constant/enum
                withdrawService.withdrawScreen(account);
                break;
            case TRANSFER_FUND_OPTION:
                transferService.transferScreen(account);
                break;
            case EXIT_OPTION:
                welcomeScreen();
                break;
            default:
                transactionScreen(account);
        }
    }

    protected void summaryOption(Account account){ // move to separate class
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
