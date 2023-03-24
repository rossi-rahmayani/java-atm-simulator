package com.rossi.javasimulatoratm.service;

import com.rossi.javasimulatoratm.model.Account;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
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
        return Optional.ofNullable(number)
                .filter(num -> num.length() >= 6)
                .map(acc -> {
                    return Optional.of(acc)
                            .filter(ac -> validateDigit(ac).equals(Boolean.TRUE))
                            .map(a -> Boolean.TRUE)
                            .orElseGet(() -> {
                                System.out.println(NUMBERS_ONLY_ERROR);
                                System.out.println( type + NUMBERS_ONLY_ERROR);
                                return Boolean.FALSE;
                            });
                })
                .orElseGet(() -> {
                    System.out.println(type + MINIMUM_DIGIT_LENGTH_ERROR);
                    return Boolean.FALSE;
                });
    }

    public Account validateAccountLogin(List<Account> accounts, String accNum, String pin){
        return accounts.stream()
                .filter(a -> a.getAccountNumber().equals(accNum))
                .findFirst()
                .filter(account -> account.getPin().equals(pin))
                .orElseGet(() -> {
                    System.out.println(INVALID_ACCOUNT_OR_PIN);
                    return null;
                });
    }

    public Boolean validateWithdrawalAmount(String amount){
        return Optional.ofNullable(amount)
                .filter(amt -> validateDigit(amt).equals(Boolean.TRUE))
                .map(BigInteger::new)
                .filter(a -> (a.remainder(BigInteger.TEN)).compareTo(BigInteger.ZERO) == 0)
                .map(a -> {
                    return Optional.of(a).filter(amt -> amt.compareTo(BigInteger.valueOf(1000)) < 0)
                            .map(x -> Boolean.TRUE)
                            .orElseGet(() -> {
                                System.out.println(MAX_AMOUNT_WITHDRAW_ERROR);
                                return Boolean.FALSE;
                            });
                })
                .orElseGet(() -> {
                    System.out.println(INVALID_AMOUNT);
                    return Boolean.FALSE;
                });
    }

    public Account validateDestinationAccount(List<Account> accounts, String fromAccount, String toAccount){
        if (!validateDigit(toAccount)){
            System.out.println(INVALID_ACCOUNT_DEST);
            return null;
        }

        return accounts.stream()
                .filter(ac -> !ac.getAccountNumber().equals(fromAccount))
                .filter(a -> a.getAccountNumber().equals(toAccount))
                .findFirst()
                .orElseGet(() -> {
                    System.out.println(INVALID_ACCOUNT_DEST);
                    return null;
                });
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
