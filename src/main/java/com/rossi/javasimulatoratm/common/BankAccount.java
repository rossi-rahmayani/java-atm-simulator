package com.rossi.javasimulatoratm.common;

import com.rossi.javasimulatoratm.model.Account;

import java.math.BigInteger;
import java.util.List;

public class BankAccount {
    public static List<Account> getBankAccount(){
        Account account1 = new Account("112233", "012108", "John Doe", BigInteger.valueOf(100));
        Account account2 = new Account("112244", "932012", "Jane Doe", BigInteger.valueOf(30));
        return List.of(account1, account2);
    }
}
