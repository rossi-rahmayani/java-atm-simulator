package com.rossi.javasimulatoratm.service;

import com.rossi.javasimulatoratm.common.WithdrawalAmount;
import com.rossi.javasimulatoratm.exception.ValidationException;
import com.rossi.javasimulatoratm.model.Account;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class WithdrawService extends AtmService{

    protected void withdrawScreen(Account account) {
        System.out.println(
                "1. $10\n" +
                "2. $50\n" +
                "3. $100\n" +
                "4. Other\n" +
                "5. Back");
        System.out.print("Please choose option[5]: ");
        String option = input.nextLine();
        BigInteger wa = BigInteger.ZERO;

        try {
            switch (option) {
                case "1", "2", "3":
                    WithdrawalAmount withdrawalAmount = WithdrawalAmount.findByCode(option);
                    if (withdrawalAmount != null){
                        wa = withdrawalAmount.getAmount();
                    }
                    withdraw(account, wa);
                    break;
                case "4":
                    System.out.println("Other Withdraw");
                    System.out.print("Enter amount to withdraw: ");
                    String amount = input.nextLine();
                    wa = validationService.validateWithdrawalAmount(amount);
                    withdraw(account, wa);
                    break;
                default:
                    transactionScreen(account);
            }
        }
        catch (ValidationException e){
            System.out.println(e.getMessage());
            withdrawScreen(account);
        }
    }

    protected void withdraw(Account account, BigInteger amount) throws ValidationException {
        validationService.validateBalance(account, amount);
        BigInteger prevBalance = account.getBalance();
        BigInteger currentBalance = prevBalance.subtract(amount);
        account.setBalance(currentBalance);
        System.out.println("Summary");
        System.out.println("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")));
        System.out.println("Withdraw: $" + amount);
        System.out.println("Balance: $" + account.getBalance());
        System.out.println();
        summaryOption(account);
    }
}
