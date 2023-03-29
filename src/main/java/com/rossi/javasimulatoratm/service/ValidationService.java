package com.rossi.javasimulatoratm.service;

import com.rossi.javasimulatoratm.exception.ValidationException;
import java.math.BigInteger;
import java.util.regex.Pattern;

import static com.rossi.javasimulatoratm.common.GlobalConstant.*;

public class ValidationService {

    Pattern p = Pattern.compile(DIGIT_REGEX);
    public void validateAccNum(String accNum) throws ValidationException{
        validateNumber(accNum, "Account Number");
    }
    public void validatePin(String pin) throws ValidationException{
        validateNumber(pin, "PIN");
    }

    private Boolean validateDigit(String s){
        return p.matcher(s).matches();
    }

    private void validateNumber(String number, String type) throws ValidationException {
        if (number.isBlank() || number.length() < 6){
            throw new ValidationException(type + MINIMUM_DIGIT_LENGTH_ERROR);
        }
        if (!validateDigit(number)){
            throw new ValidationException(type + NUMBERS_ONLY_ERROR);
        }
    }

    public BigInteger validateWithdrawalAmount(String amount) throws ValidationException{
        if (!validateDigit(amount)){
            throw new ValidationException(INVALID_AMOUNT);
        }
        BigInteger amt = new BigInteger(amount);
        if (amt.compareTo(BigInteger.valueOf(1000)) > 0){
            throw new ValidationException(MAX_AMOUNT_WITHDRAW_ERROR);
        }
        if ((amt.remainder(BigInteger.TEN)).compareTo(BigInteger.ZERO) != 0){
            throw new ValidationException(INVALID_AMOUNT);
        }
        return amt;
    }

    public BigInteger validateTransferAmount(String amount) throws ValidationException{
        if (!validateDigit(amount)){
            throw new ValidationException(INVALID_AMOUNT);
        }
        BigInteger transferAmount = new BigInteger(amount);

        if (transferAmount.compareTo(BigInteger.ONE) < 0){
            throw new ValidationException(MIN_AMOUNT_TRANSFER_ERROR);
        }
        if (transferAmount.compareTo(BigInteger.valueOf(1000)) > 0){
            throw new ValidationException(MAX_AMOUNT_TRANSFER_ERROR);
        }
        return transferAmount;
    }

}
