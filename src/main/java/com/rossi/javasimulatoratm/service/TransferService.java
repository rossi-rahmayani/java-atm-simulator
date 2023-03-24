package com.rossi.javasimulatoratm.service;

import com.rossi.javasimulatoratm.model.Account;
import com.rossi.javasimulatoratm.model.TransferRequest;
import java.math.BigInteger;
import java.util.List;

import static com.rossi.javasimulatoratm.common.MessageConstant.INSUFFICIENT_BALANCE;

public class TransferService {

    private ValidationService validationService = new ValidationService();
    public Boolean transfer(List<Account> accounts, TransferRequest request){
        Account fromAccount = request.getFromAccount();
        Account toAccount = validationService.validateDestinationAccount(accounts, fromAccount.getAccountNumber(), request.getToAccountNumber());
        BigInteger trfAmount;
        if (toAccount == null){
            return Boolean.FALSE;
        }
        if (!validationService.validateTransferAmount(request.getAmount())){
            return Boolean.FALSE;
        }
        trfAmount = new BigInteger(request.getAmount());

        if (trfAmount.compareTo(fromAccount.getBalance()) > 0){
            System.out.println(INSUFFICIENT_BALANCE + trfAmount.toString());
            return Boolean.FALSE;
        }

        fromAccount.setBalance(fromAccount.getBalance().subtract(trfAmount));
        toAccount.setBalance(toAccount.getBalance().add(trfAmount));

        summaryTransfer(request, fromAccount);
        return Boolean.TRUE;
    }

    private void summaryTransfer(TransferRequest request, Account fromAccount){
        System.out.println("Fund Transfer Summary");
        System.out.println("Destination Account : " + request.getToAccountNumber());
        System.out.println("Transfer Amount     : $" + request.getAmount());
        System.out.println("Reference Number    : " + request.getReferenceNumber());
        System.out.println("Balance             : $" + fromAccount.getBalance().toString());
    }
}
