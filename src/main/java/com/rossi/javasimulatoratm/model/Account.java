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
    public BigInteger getBalance() {
        return balance;
    }
    public Boolean isMatchesPin(String pin){
        return this.pin.equals(pin);
    }

    public Boolean isMatchesAccountNumber(String accountNumber){
        return this.accountNumber.equals(accountNumber);
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
