package com.rossi.javasimulatoratm.model;

public class TransferRequest {
    private Account fromAccount;
    private String toAccountNumber;
    private String amount;
    private String referenceNumber;

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

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    public String showSummaryTransfer(){
        return  "Fund Transfer Summary \n" +
                "Destination Account : " + this.toAccountNumber + "\n" +
                "Transfer Amount     : $" + this.amount + "\n" +
                "Reference Number    : " + this.referenceNumber;
    }
}
