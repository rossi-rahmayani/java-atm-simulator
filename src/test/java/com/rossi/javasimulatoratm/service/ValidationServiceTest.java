package com.rossi.javasimulatoratm.service;

import com.rossi.javasimulatoratm.exception.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

public class ValidationServiceTest {
    ValidationService validationService = new ValidationService();

    @Test
    void validateAccNum_success() throws ValidationException {
        validationService.validateAccNum("112234");
        Assertions.assertTrue(true);
    }

    @Test
    void validateAccNum_failedBlank() {
        Assertions.assertThrows(ValidationException.class, () -> validationService.validateAccNum(" "));
    }

    @Test
    void validateAccNum_failedLessThanSixDigit() {
        Assertions.assertThrows(ValidationException.class, () -> validationService.validateAccNum("123"));
    }

    @Test
    void validateAccNum_failedNotNumber() {
        Assertions.assertThrows(ValidationException.class, () -> validationService.validateAccNum("123abc"));
    }

    @Test
    void validatePin_success() throws ValidationException {
        validationService.validatePin("112234");
        Assertions.assertTrue(true);
    }

    @Test
    void validateWithdrawalAmount_success() throws ValidationException {
        Assertions.assertEquals(BigInteger.valueOf(10), validationService.validateWithdrawalAmount("10"));
    }

    @Test
    void validateWithdrawalAmount_failedNotNumber(){
        Assertions.assertThrows(ValidationException.class, () -> validationService.validateWithdrawalAmount("2a"));
    }

    @Test
    void validateWithdrawalAmount_failedExceedMaxAmount(){
        Assertions.assertThrows(ValidationException.class, () -> validationService.validateWithdrawalAmount("2000"));
    }

    @Test
    void validateWithdrawalAmount_failedDivisionByTen(){
        Assertions.assertThrows(ValidationException.class, () -> validationService.validateWithdrawalAmount("203"));
    }

    @Test
    void validateTransferAmount_success() throws ValidationException {
        Assertions.assertEquals(BigInteger.valueOf(10), validationService.validateTransferAmount("10"));
    }

    @Test
    void validateTransferAmount_failedNotNumber(){
        Assertions.assertThrows(ValidationException.class, () -> validationService.validateTransferAmount("2a"));
    }

    @Test
    void validateTransferAmount_failedExceedMaxAmount(){
        Assertions.assertThrows(ValidationException.class, () -> validationService.validateTransferAmount("2000"));
    }

    @Test
    void validateTransferAmount_failedBelowMinAmount(){
        Assertions.assertThrows(ValidationException.class, () -> validationService.validateTransferAmount("0"));
    }
}
