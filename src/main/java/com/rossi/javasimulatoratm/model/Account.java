package com.rossi.javasimulatoratm.model;

import java.math.BigInteger;

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
}
