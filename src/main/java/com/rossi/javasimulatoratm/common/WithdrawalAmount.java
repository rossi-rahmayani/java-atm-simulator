package com.rossi.javasimulatoratm.common;

import java.math.BigInteger;
import java.util.Arrays;

import static com.rossi.javasimulatoratm.common.GlobalConstant.*;

public enum WithdrawalAmount {
    TEN (WITHDRAW_TEN, BigInteger.valueOf(10)),
    FIFTY (WITHDRAW_FIFTY, BigInteger.valueOf(50)),
    ONE_HUNDRED (WITHDRAW_HUNDRED, BigInteger.valueOf(100));

    public String getCode() {
        return code;
    }

    public BigInteger getAmount() {
        return amount;
    }

    private String code;
    private BigInteger amount;

    WithdrawalAmount(String code, BigInteger amount) {
        this.code = code;
        this.amount = amount;
    }

    public static WithdrawalAmount findByCode(String code){
        return Arrays.stream(WithdrawalAmount.values())
                .filter(withdrawalAmount -> withdrawalAmount.getCode().equals(code))
                .findFirst()
                .orElseGet(() -> null);
    }
}
