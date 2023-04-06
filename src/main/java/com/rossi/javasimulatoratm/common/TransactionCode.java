package com.rossi.javasimulatoratm.common;

import static com.rossi.javasimulatoratm.common.GlobalConstant.CREDIT_CODE;
import static com.rossi.javasimulatoratm.common.GlobalConstant.DEBIT_CODE;

public enum TransactionCode {
    WITHDRAW("W", "Withdraw", DEBIT_CODE),
    TRANSFER("T", "Transfer Fund", DEBIT_CODE),
    FUND("F", "Received Fund", CREDIT_CODE)
    ;

    private String code;
    private String description;
    private String debitCredit;

    TransactionCode(String code, String description, String debitCredit) {
        this.code = code;
        this.description = description;
        this.debitCredit = debitCredit;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public String getDebitCredit() {
        return debitCredit;
    }

}
