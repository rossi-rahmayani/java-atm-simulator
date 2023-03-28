package com.rossi.javasimulatoratm.model;

import com.rossi.javasimulatoratm.exception.ValidationException;

import java.math.BigInteger;
import java.util.List;

import static com.rossi.javasimulatoratm.common.GlobalConstant.INSUFFICIENT_BALANCE;

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

    public String getPin() {
        return pin;
    }

    public String getName() {
        return name;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public static List<Account> getSampleAccounts(){
        Account account1 = new Account("112233", "012108", "John Doe", BigInteger.valueOf(100));
        Account account2 = new Account("112244", "932012", "Jane Doe", BigInteger.valueOf(30));
        return List.of(account1, account2);
    }

    public void decreaseBalance(BigInteger amount) throws ValidationException {
        if (amount.compareTo(this.balance) > 0){
            throw new ValidationException(INSUFFICIENT_BALANCE + amount);
        }
        this.balance = this.balance.subtract(amount);
    }

    public void increaseBalance(BigInteger amount){
        this.balance = this.balance.add(amount);
    }

}
