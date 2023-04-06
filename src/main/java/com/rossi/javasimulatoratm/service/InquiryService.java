package com.rossi.javasimulatoratm.service;

import com.rossi.javasimulatoratm.model.Account;
import com.rossi.javasimulatoratm.model.Transaction;
import com.rossi.javasimulatoratm.repository.TransactionRepository;

public class InquiryService {

    private SummaryService summaryService;
    private TransactionRepository transactionRepository;

    public InquiryService(SummaryService summaryService, TransactionRepository transactionRepository) {
        this.summaryService = summaryService;
        this.transactionRepository = transactionRepository;
    }

    public String showLatestMutation(Account account){
        int num = 1;
        System.out.println("MUTATION OF ACCOUNT: " + account.getName());
        System.out.println("No. | Date                | Type  | Description         | Amount   | Reference Number | Last Balance  ");
        System.out.println("------------------------------------------------------------------------------------------------------");
        for (Transaction trx: transactionRepository.findLatestTenTransactionByAccount(account)){
            System.out.println(trx.showRecord(num));
            num++;
        }
        System.out.println("------------------------------------------------------------------------------------------------------");
        return summaryService.summaryOption();
    }
}
