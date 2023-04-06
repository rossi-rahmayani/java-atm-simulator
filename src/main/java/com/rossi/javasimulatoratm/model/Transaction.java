package com.rossi.javasimulatoratm.model;

import com.rossi.javasimulatoratm.common.TransactionCode;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Transaction {
    private Account account;
    private LocalDateTime transactionDate;
    private TransactionCode transactionCode;
    private BigInteger transactionAmount;
    private String referenceNumber;
    private BigInteger mutationBalance;

    public Transaction(Account account, LocalDateTime transactionDate, TransactionCode transactionCode, BigInteger transactionAmount, String referenceNumber, BigInteger mutationBalance) {
        this.account = account;
        this.transactionDate = transactionDate;
        this.transactionCode = transactionCode;
        this.transactionAmount = transactionAmount;
        this.referenceNumber = referenceNumber;
        this.mutationBalance = mutationBalance;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public Boolean isAccountMatches(Account account){
        return this.account.equals(account);
    }

    public String showRecord(Integer index){
        String indexStr = String.format("%-4s", index);
        TransactionCode trxCode = this.transactionCode;
        String trxDate = this.transactionDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String debitCredit = String.format("%-6s", trxCode.getDebitCredit());
        String trxDescription = String.format("%-20s", trxCode.getDescription());
        String trxAmount = String.format("%8s", ("$"+ this.transactionAmount));
        String refNumber = String.format("%-17s", Optional.ofNullable(this.referenceNumber).orElseGet(() -> ""));
        String lastBalance = String.format("%13s", ("$" + this.mutationBalance));

        return indexStr.concat("| ").concat(trxDate).concat(" | ").concat(debitCredit)
                .concat("| ").concat(trxDescription).concat("| ").concat(trxAmount)
                .concat(" | ").concat(refNumber).concat("| ").concat(lastBalance).concat(" ");
    }



}
