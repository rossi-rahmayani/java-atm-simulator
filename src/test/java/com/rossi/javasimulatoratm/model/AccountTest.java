package com.rossi.javasimulatoratm.model;

import com.rossi.javasimulatoratm.exception.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.List;

public class AccountTest {

    Account account = Account.getSampleAccounts().get(0);

    @Test
    void getBankAccounts(){
        List<Account> accounts = Account.getSampleAccounts();
        Assertions.assertEquals(2, accounts.size());
        Assertions.assertTrue(accounts.stream().anyMatch(account -> account.getAccountNumber().equals("112233")));
    }

    @Test
    void decreaseBalance_success() throws ValidationException {
        account.decreaseBalance(BigInteger.valueOf(10));
        Assertions.assertEquals(BigInteger.valueOf(90), account.getBalance());
    }

    @Test
    void decreaseBalance_failed(){
        Assertions.assertThrows(ValidationException.class, () -> account.decreaseBalance(BigInteger.valueOf(200)));
    }

    @Test
    void increaseBalance(){
        account.increaseBalance(BigInteger.valueOf(20));
        Assertions.assertEquals(BigInteger.valueOf(120), account.getBalance());
    }
}
