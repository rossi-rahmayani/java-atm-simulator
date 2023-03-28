package com.rossi.javasimulatoratm.repository;

import com.rossi.javasimulatoratm.exception.ValidationException;
import com.rossi.javasimulatoratm.model.Account;

import static com.rossi.javasimulatoratm.common.GlobalConstant.INVALID_ACCOUNT;
import static com.rossi.javasimulatoratm.common.GlobalConstant.INVALID_ACCOUNT_OR_PIN;

public class AccountRepository {

    public Account findByAccountNumberAndPin(String accountNumber, String pin) throws ValidationException {
        return Account.getSampleAccounts().stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .filter(account -> account.getPin().equals(pin))
                .findFirst()
                .orElseThrow(() -> new ValidationException(INVALID_ACCOUNT_OR_PIN));
    }

    public Account findByAccountNumber(String accountNumber) throws ValidationException{
        return Account.getSampleAccounts().stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElseThrow(() -> new ValidationException(INVALID_ACCOUNT));
    }

}
