package com.rossi.javasimulatoratm.service;

import com.rossi.javasimulatoratm.common.WithdrawalAmount;
import com.rossi.javasimulatoratm.exception.ValidationException;
import com.rossi.javasimulatoratm.model.Account;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.Scanner;

import static com.rossi.javasimulatoratm.common.GlobalConstant.*;

public class WithdrawService {
    private ValidationService validationService = new ValidationService();
    private SummaryService summaryService = new SummaryService();
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
        System.out.println("Summary");
        System.out.println("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")));
        System.out.println("Withdraw: $" + amount);
        System.out.println("Balance: $" + account.getBalance());
        return summaryService.summaryOption();
    }
}
