package com.rossi.javasimulatoratm.model;

import com.rossi.javasimulatoratm.exception.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class AccountTest {

    Account account = new Account("112233", "012108", "John Doe", BigInteger.valueOf(100));

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
