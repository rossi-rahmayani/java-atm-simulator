package com.rossi.javasimulatoratm.service;

import com.rossi.javasimulatoratm.common.TransactionCode;
import com.rossi.javasimulatoratm.common.WithdrawalAmount;
import com.rossi.javasimulatoratm.exception.ValidationException;
import com.rossi.javasimulatoratm.model.Account;
import com.rossi.javasimulatoratm.repository.TransactionRepository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

import static com.rossi.javasimulatoratm.common.GlobalConstant.*;

public class WithdrawService {
    private ValidationService validationService;
    private SummaryService summaryService;
    private TransactionRepository transactionRepository;

    public WithdrawService(ValidationService validationService, SummaryService summaryService, TransactionRepository transactionRepository){
        this.validationService = validationService;
        this.summaryService = summaryService;
        this.transactionRepository = transactionRepository;
    }

    Scanner input = new Scanner(System.in);

    public String withdrawScreen(Account account) {
        System.out.println(
                "1. $10\n" +
                "2. $50\n" +
                "3. $100\n" +
                "4. Other\n" +
                "5. Back");
        System.out.print("Please choose option[5]: ");
        String option = input.nextLine();
        BigInteger withdrawalAmount;

        try {
            switch (option) {
                case WITHDRAW_TEN, WITHDRAW_FIFTY, WITHDRAW_HUNDRED:
                    withdrawalAmount = Optional.ofNullable(WithdrawalAmount.findByCode(option))
                            .map(WithdrawalAmount::getAmount).orElseGet(() -> BigInteger.ZERO);
                    return withdraw(account, withdrawalAmount);
                case WITHDRAW_CUSTOM:
                    System.out.println("Other Withdraw");
                    System.out.print("Enter amount to withdraw: ");
                    String amount = input.nextLine();
                    withdrawalAmount = validationService.validateWithdrawalAmount(amount);
                    return withdraw(account, withdrawalAmount);
                default:
                    return MAIN_TRANSACTION_MENU;
            }
        }
        catch (ValidationException e){
            System.out.println(e.getMessage());
            return withdrawScreen(account);
        }
    }

    private String withdraw(Account account, BigInteger amount) throws ValidationException {
        account.decreaseBalance(amount);
        //create transaction history
        LocalDateTime trxDate = LocalDateTime.now();
        transactionRepository.createNewTransaction(account, TransactionCode.WITHDRAW, amount, trxDate, "");

        System.out.println("Summary");
        System.out.println("Date: " + trxDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")));
        System.out.println("Withdraw: $" + amount);
        System.out.println("Balance: $" + account.getBalance());
        return summaryService.summaryOption();
    }
}
