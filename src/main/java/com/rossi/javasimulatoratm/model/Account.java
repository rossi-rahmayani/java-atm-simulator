package com.rossi.javasimulatoratm.model;

import java.math.BigInteger;
import java.util.List;

public class Account {
    private String accountNumber;
    private String pin;
    private String name;
    private BigInteger balance;

    public Account() {
    }

    public Account(String accountNumber, String pin, String name, BigInteger balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.name = name;
        this.balance = balance;
    }


    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }


    public static List<Account> getSampleAccounts(){
        Account account1 = new Account("112233", "012108", "John Doe", BigInteger.valueOf(100));
        Account account2 = new Account("112244", "932012", "Jane Doe", BigInteger.valueOf(30));
        return List.of(account1, account2);
    }
}
