package com.rossi.javasimulatoratm.common;

import java.math.BigInteger;

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

    public static WithdrawalAmount findByCode(String code){
        for (WithdrawalAmount wa: WithdrawalAmount.values()){
            if (wa.code.equals(code)){
                return wa;
            }
        }
        return null;
    }
}
