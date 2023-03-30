package com.rossi.javasimulatoratm.repository;

import com.rossi.javasimulatoratm.exception.ValidationException;
import com.rossi.javasimulatoratm.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountRepositoryTest {
    AccountRepository accountRepository = new AccountRepository();

    @Test
    void findByAccountNumberAndPin_success() throws ValidationException {
        Account account = accountRepository.findByAccountNumberAndPin("112233", "012108");
        Assertions.assertNotNull(account);
        Assertions.assertEquals("112233", account.getAccountNumber());
    }

    @Test
    void findByAccountNumberAndPin_failedAccountNumber(){
        Assertions.assertThrows(ValidationException.class, () -> accountRepository.findByAccountNumberAndPin("134253", "012108"));
    }

    @Test
    void findByAccountNumberAndPin_failedPin(){
        Assertions.assertThrows(ValidationException.class, () -> accountRepository.findByAccountNumberAndPin("112233", "134253"));
    }


    @Test
    void findByAccountNumber_success() throws ValidationException {
        Account account = accountRepository.findByAccountNumber("112244");
        Assertions.assertNotNull(account);
        Assertions.assertEquals("112244", account.getAccountNumber());
    }

    @Test
    void findByAccountNumber_failed() throws ValidationException{
        Assertions.assertThrows(ValidationException.class, () -> accountRepository.findByAccountNumber("123412"));
    }


}
