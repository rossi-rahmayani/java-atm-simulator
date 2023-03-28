package com.rossi.javasimulatoratm.common;

public class GlobalConstant {
    public static final String DIGIT_REGEX = "[0-9]+";

    //error msg
    public static final String NUMBERS_ONLY_ERROR = " should only contains numbers";
    public static final String MINIMUM_DIGIT_LENGTH_ERROR = " should have 6 digits length";
    public static final String INVALID_ACCOUNT_OR_PIN = "Invalid Account Number/PIN";
    public static final String MAX_AMOUNT_WITHDRAW_ERROR = "Maximum amount to withdraw is $1000";
    public static final String INVALID_AMOUNT = "Invalid amount";
    public static final String INSUFFICIENT_BALANCE = "Insufficient balance: $";
    public static final String INVALID_ACCOUNT = "Invalid account";
    public static final String MIN_AMOUNT_TRANSFER_ERROR = "Minimum amount to transfer is $1";
    public static final String MAX_AMOUNT_TRANSFER_ERROR = "Maximum amount to transfer is $1000";
    public static final String INVALID_REF_NUMBER = "Invalid Reference Number";

    //option main menu
    public static final String WITHDRAW_OPTION = "1";
    public static final String TRANSFER_FUND_OPTION = "2";
    public static final String EXIT_OPTION = "3";

    //withdraw option
    public static final String WITHDRAW_TEN = "1";
    public static final String WITHDRAW_FIFTY = "2";
    public static final String WITHDRAW_HUNDRED = "3";
    public static final String WITHDRAW_CUSTOM = "4";

    //transfer confirmation option
    public static final String CONFIRM_TRANSFER = "1";
    public static final String CANCEL_TRANSFER = "2";

    //redirect option menu
    public static final String WITHDRAWAL_MENU = "WITHDRAWAL";
    public static final String TRANSFER_MENU = "TRANSFER";
    public static final String MAIN_TRANSACTION_MENU = "MAIN";
    public static final String WELCOME = "WELCOME";

}
