package com.rossi.javasimulatoratm.model;

import java.math.BigInteger;

public class TransferDto {
    private Account fromAccount;
    private String toAccountNumber;
    private BigInteger amount;
    private String refrenceNumber;

    public Account getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Account fromAccount) {
        this.fromAccount = fromAccount;
    }

    public String getToAccountNumber() {
        return toAccountNumber;
    }

    public void setToAccountNumber(String toAccountNumber) {
        this.toAccountNumber = toAccountNumber;
    }

    public BigInteger getAmount() {
        return amount;
    }

    public void setAmount(BigInteger amount) {
        this.amount = amount;
    }

    public String getRefrenceNumber() {
        return refrenceNumber;
    }

    public void setRefrenceNumber(String refrenceNumber) {
        this.refrenceNumber = refrenceNumber;
    }
}
