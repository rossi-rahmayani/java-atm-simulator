package com.rossi.javasimulatoratm;

import com.rossi.javasimulatoratm.repository.AccountRepository;
import com.rossi.javasimulatoratm.repository.TransactionRepository;
import com.rossi.javasimulatoratm.service.*;

public class Main {
    public static void main(String[] args) {
        SummaryService summaryService = new SummaryService();
        ValidationService validationService = new ValidationService();
        AccountRepository accountRepository = new AccountRepository();
        TransactionRepository transactionRepository = new TransactionRepository();

        TransferService transferService = new TransferService(validationService, accountRepository, summaryService, transactionRepository);
        WithdrawService withdrawService = new WithdrawService(validationService, summaryService, transactionRepository);
        InquiryService inquiryService = new InquiryService(summaryService, transactionRepository);

        AtmService service = new AtmService(validationService, accountRepository, withdrawService, transferService, inquiryService);
        service.welcomeScreen();
    }
}