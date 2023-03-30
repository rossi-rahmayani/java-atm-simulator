package com.rossi.javasimulatoratm.service;

import com.rossi.javasimulatoratm.exception.ValidationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidationServiceTest {
    ValidationService validationService = new ValidationService();

    @Test
    void validateAccNum_success() throws ValidationException {
        validationService.validateAccNum("112234");
        Assertions.assertTrue(true);
    }

    @Test
    void validatePin_success() throws ValidationException {
        validationService.validatePin("112234");
        Assertions.assertTrue(true);
    }
}
