package com.rossi.javasimulatoratm;

import com.rossi.javasimulatoratm.repository.AccountRepository;
import com.rossi.javasimulatoratm.service.*;

public class Main {
    public static void main(String[] args) {
        SummaryService summaryService = new SummaryService();
        ValidationService validationService = new ValidationService();
        AccountRepository accountRepository = new AccountRepository();
        TransferService transferService = new TransferService(validationService, accountRepository, summaryService);
        WithdrawService withdrawService = new WithdrawService(validationService, summaryService);
        AtmService service = new AtmService(validationService, accountRepository, withdrawService, transferService);
        service.welcomeScreen();
    }
}