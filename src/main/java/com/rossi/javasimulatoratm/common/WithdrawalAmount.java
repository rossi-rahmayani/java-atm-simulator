package com.rossi.javasimulatoratm.common;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Optional;

public enum WithdrawalAmount {
    TEN("1", BigInteger.valueOf(10)),
    FIFTY("2", BigInteger.valueOf(50)),
    ONE_HUNDRED("3", BigInteger.valueOf(100));


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

    public static Optional<WithdrawalAmount> findByCode(String code){
        return Arrays.stream(WithdrawalAmount.values())
                .filter(wa -> wa.code.equals(code))
                .findFirst();
    }
}
