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
    private AtmService atmService = new AtmService();
    Scanner input = new Scanner(System.in);

    public void withdrawScreen(Account account) {
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
                            .map(WithdrawalAmount::getAmount)
                            .orElseGet(() -> BigInteger.ZERO);
                    withdraw(account, withdrawalAmount);
                    break;
                case WITHDRAW_CUSTOM:
                    System.out.println("Other Withdraw");
                    System.out.print("Enter amount to withdraw: ");
                    String amount = input.nextLine();
                    withdrawalAmount = validationService.validateWithdrawalAmount(amount);
                    withdraw(account, withdrawalAmount);
                    break;
                default:
                    atmService.transactionScreen(account);
            }
        }
        catch (ValidationException e){
            System.out.println(e.getMessage());
            withdrawScreen(account);
        }
    }

    private void withdraw(Account account, BigInteger amount) throws ValidationException {
        account.decreaseBalance(amount);
        System.out.println("Summary");
        System.out.println("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")));
        System.out.println("Withdraw: $" + amount);
        System.out.println("Balance: $" + account.getBalance());
        System.out.println();
        atmService.summaryOption(account);
    }
}
