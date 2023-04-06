package com.rossi.javasimulatoratm.repository;

import com.rossi.javasimulatoratm.common.TransactionCode;
import com.rossi.javasimulatoratm.model.Account;
import com.rossi.javasimulatoratm.model.Transaction;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TransactionRepository {
    private static List<Transaction> transactions = new ArrayList<>();
    public List<Transaction> findLatestTenTransactionByAccount(Account account){
        return transactions.stream()
                .filter(t -> t.isAccountMatches(account))
                .sorted(Comparator.comparing(Transaction::getTransactionDate).reversed())
                .limit(10)
                .collect(Collectors.toList());
    }
    public Transaction createNewTransaction(Account account, TransactionCode transactionCode, BigInteger trxAmount, LocalDateTime trxDate, String refNumber){
        Transaction transaction = new Transaction(account, trxDate, transactionCode, trxAmount, refNumber, account.getBalance());
        transactions.add(transaction);
        return transaction;
    }

}
