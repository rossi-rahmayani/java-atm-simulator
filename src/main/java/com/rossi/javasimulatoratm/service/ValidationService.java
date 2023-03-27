package com.rossi.javasimulatoratm.service;

import com.rossi.javasimulatoratm.exception.ValidationException;
import com.rossi.javasimulatoratm.model.Account;

import java.math.BigInteger;
import java.util.List;
import java.util.regex.Pattern;

import static com.rossi.javasimulatoratm.common.MessageConstant.*;

public class ValidationService {

    String regex = "[0-9]+";
    Pattern p = Pattern.compile(regex);
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

    public Account validateAccountLogin(List<Account> accounts, String accNum, String pin) throws ValidationException{
        for (Account a: accounts){
            if (a.getAccountNumber().equals(accNum) && (a.getPin().equals(pin))){
                return a;
            }
        }
        throw new ValidationException(INVALID_ACCOUNT_OR_PIN);
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
        return new BigInteger(amount);
    }

    public Account validateDestinationAccount(List<Account> accounts, String fromAccount, String toAccount){
        if (!validateDigit(toAccount)){
            System.out.println(INVALID_ACCOUNT_DEST);
            return null;
        }
        for (Account a : accounts){
            if (a.getAccountNumber().equals(toAccount) && !a.getAccountNumber().equals(fromAccount)){
                return a;
            }
        }
        System.out.println(INVALID_ACCOUNT_DEST);
        return null;
    }

    public Boolean validateTransferAmount(String amount){
        if (!validateDigit(amount)){
            System.out.println(INVALID_AMOUNT);
            return Boolean.FALSE;
        }
        BigInteger transfAmount = new BigInteger(amount);

        if (transfAmount.compareTo(BigInteger.ONE) < 0){
            System.out.println(MIN_AMOUNT_TRANSFER_ERROR);
            return Boolean.FALSE;
        }
        if (transfAmount.compareTo(BigInteger.valueOf(1000)) > 0){
            System.out.println(MAX_AMOUNT_TRANSFER_ERROR);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public void validateBalance(Account account, BigInteger amount) throws ValidationException{
        if (amount.compareTo(account.getBalance()) > 0){
            throw new ValidationException(INSUFFICIENT_BALANCE + amount);
        }
    }

}
