package com.rossi.javasimulatoratm.service;

import com.rossi.javasimulatoratm.common.WithdrawalAmount;
import com.rossi.javasimulatoratm.model.Account;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.rossi.javasimulatoratm.common.MessageConstant.INSUFFICIENT_BALANCE;

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
        BigInteger wa;

        switch (option) {
            case "1", "2", "3":
                wa = WithdrawalAmount.findByCode(option).map(WithdrawalAmount::getAmount).orElse(BigInteger.ZERO);
                checkWithdrawal(account, wa);
                break;
            case "4":
                System.out.println("Other Withdraw");
                System.out.print("Enter amount to withdraw: ");
                String amount = input.nextLine();
                if (validationService.validateWithdrawalAmount(amount)) {
                    wa = new BigInteger(amount);
                    checkWithdrawal(account, wa);
                } else {
                    withdrawScreen(account);
                }
                break;
            default:
                transactionScreen(account);
        }
    }

    private void checkWithdrawal(Account account, BigInteger amount){
        if (withdraw(account, amount)) {
            summaryOption(account);
        } else {
            withdrawScreen(account);
        }
    }
    protected Boolean withdraw(Account account, BigInteger amount){
        if (amount.compareTo(account.getBalance()) > 0){
            System.out.println(INSUFFICIENT_BALANCE + amount.toString());
            return Boolean.FALSE;
        }
        else {
            BigInteger prevBalance = account.getBalance();
            BigInteger currentBalance = prevBalance.subtract(amount);
            account.setBalance(currentBalance);
            System.out.println("Summary");
            System.out.println("Date: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd h:mm a")));
            System.out.println("Withdraw: $" + amount);
            System.out.println("Balance: $" + account.getBalance());
            System.out.println();
            return Boolean.TRUE;
        }
    }
}
