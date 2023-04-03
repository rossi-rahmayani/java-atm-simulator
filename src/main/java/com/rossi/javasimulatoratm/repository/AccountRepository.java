package com.rossi.javasimulatoratm.repository;

import com.rossi.javasimulatoratm.exception.ValidationException;
import com.rossi.javasimulatoratm.model.Account;

import java.math.BigInteger;
import java.util.List;

import static com.rossi.javasimulatoratm.common.GlobalConstant.INVALID_ACCOUNT;
import static com.rossi.javasimulatoratm.common.GlobalConstant.INVALID_ACCOUNT_OR_PIN;

public class AccountRepository {
    private Account account1 = new Account("112233", "012108", "John Doe", BigInteger.valueOf(100));
    private Account account2 = new Account("112244", "932012", "Jane Doe", BigInteger.valueOf(30));
    private List<Account> accounts =  List.of(account1, account2);

    public Account findByAccountNumberAndPin(String accountNumber, String pin) throws ValidationException {
        return accounts.stream()
                .filter(account -> account.isMatchesAccountNumber(accountNumber))
                .filter(account -> account.isMatchesPin(pin))
                .findFirst()
                .orElseThrow(() -> new ValidationException(INVALID_ACCOUNT_OR_PIN));
    }

    public Account findByAccountNumber(String accountNumber) throws ValidationException{
        return accounts.stream()
                .filter(account -> account.isMatchesAccountNumber(accountNumber))
                .findFirst()
                .orElseThrow(() -> new ValidationException(INVALID_ACCOUNT));
    }

}
