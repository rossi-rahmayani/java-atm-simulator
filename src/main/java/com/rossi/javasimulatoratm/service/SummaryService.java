package com.rossi.javasimulatoratm.service;

import java.util.Optional;
import java.util.Scanner;

import static com.rossi.javasimulatoratm.common.GlobalConstant.MAIN_TRANSACTION_MENU;
import static com.rossi.javasimulatoratm.common.GlobalConstant.WELCOME;

public class SummaryService {

    Scanner input = new Scanner(System.in);
    public String summaryOption(){
        System.out.println(
                "1. Transaction \n" +
                "2. Exit");
        System.out.print("Choose option[2]: ");
        String option = input.nextLine();

        return Optional.ofNullable(option)
                .filter(opt -> opt.equals("1"))
                .map(o -> MAIN_TRANSACTION_MENU)
                .orElseGet(() -> WELCOME);
    }
}
