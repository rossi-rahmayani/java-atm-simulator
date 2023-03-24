package com.rossi.javasimulatoratm.service;

import com.rossi.javasimulatoratm.model.Account;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.rossi.javasimulatoratm.common.MessageConstant.INSUFFICIENT_BALANCE;

public class WithdrawService {

    public Boolean withdraw(Account account, BigInteger amount){
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
