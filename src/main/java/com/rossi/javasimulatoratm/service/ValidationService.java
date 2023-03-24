package com.rossi.javasimulatoratm.service;

import com.rossi.javasimulatoratm.model.Account;

import java.math.BigInteger;
import java.util.List;
import java.util.regex.Pattern;

import static com.rossi.javasimulatoratm.common.MessageConstant.*;

public class ValidationService {

    String regex = "[0-9]+";
    Pattern p = Pattern.compile(regex);
    public Boolean validateAccNum(String accNum){
        return validateNumber(accNum, "Account Number");
    }
    public Boolean validatePin(String pin){
        return validateNumber(pin, "PIN");
    }

    private Boolean validateDigit(String s){
        return p.matcher(s).matches();
    }

    private Boolean validateNumber(String number, String type){
        if (number.isBlank() || number.length() < 6){
            System.out.println(type + MINIMUM_DIGIT_LENGTH_ERROR);
            return Boolean.FALSE;
        }
        if (!validateDigit(number)){
            System.out.println( type + NUMBERS_ONLY_ERROR);
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public Account validateAccountLogin(List<Account> accounts, String accNum, String pin){
        for (Account a: accounts){
            if (a.getAccountNumber().equals(accNum) && (a.getPin().equals(pin))){
                return a;
            }
        }
        System.out.println(INVALID_ACCOUNT_OR_PIN);
        return null;
    }

    public Boolean validateWithdrawalAmount(String amount){
        if (!validateDigit(amount)){
            System.out.println(INVALID_AMOUNT);
            return Boolean.FALSE;
        }
        BigInteger amt = new BigInteger(amount);
        if ((amt.remainder(BigInteger.TEN)).compareTo(BigInteger.ZERO) != 0){
            System.out.println(MAX_AMOUNT_WITHDRAW_ERROR);
            return Boolean.FALSE;
        }
        if (amt.compareTo(BigInteger.valueOf(1000)) < 0){
            System.out.println(MAX_AMOUNT_WITHDRAW_ERROR);
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
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

}
