package com.rossi.javasimulatoratm.service;

import com.rossi.javasimulatoratm.exception.ValidationException;
import com.rossi.javasimulatoratm.model.Account;
import com.rossi.javasimulatoratm.model.TransferRequest;
import java.math.BigInteger;
import java.util.List;

public class TransferService extends AtmService{

    private ValidationService validationService = new ValidationService();

    protected void transferScreen(Account account){
        TransferRequest request = new TransferRequest();
        request.setFromAccount(account);
        //input dest account
        System.out.print("Please enter destination account and press enter to continue " +
                "or press cancel (Esc) to go back to Transaction: ");
        request.setToAccountNumber(input.nextLine());

        System.out.print("Please enter transfer amount and press enter to continue " +
                "or press cancel (Esc) to go back to Transaction: ");
        request.setAmount(input.nextLine());

        System.out.print("Reference Number: (This is an autogenerated random 6 digits number)\n" +
                "press enter to continue or press cancel (Esc) to go back to Transaction: ");
        input.nextLine();

        var refNum = random.nextInt(999999);
        var ref = String.format("%06d", refNum);
        request.setReferenceNumber(ref);

        System.out.println("Transfer Confirmation");
        System.out.println("Destination Account : " + request.getToAccountNumber());
        System.out.println("Transfer Amount     : $" + request.getAmount());
        System.out.println("Reference Number    : " + request.getReferenceNumber());
        System.out.println();
        System.out.print(
                "1. Confirm Trx\n" +
                "2. Cancel Trx\n" +
                "Choose option[2]: "
        );
        String option = input.nextLine();
        try {
            switch (option) {
                case "1":
                    transfer(accounts, request);
                    break;
                default:
                    transactionScreen(account);
                    break;
            }
        }
        catch (ValidationException e){
            System.out.println(e.getMessage());
            transferScreen(account);
        }
    }
    public void transfer(List<Account> accounts, TransferRequest request) throws ValidationException{
        Account fromAccount = request.getFromAccount();
        Account toAccount = validationService.validateDestinationAccount(accounts, fromAccount.getAccountNumber(), request.getToAccountNumber());
        BigInteger trfAmount = validationService.validateTransferAmount(request.getAmount());
        validationService.validateBalance(fromAccount, trfAmount);
        fromAccount.setBalance(fromAccount.getBalance().subtract(trfAmount));
        toAccount.setBalance(toAccount.getBalance().add(trfAmount));
        summaryTransfer(request, fromAccount);
    }

    private void summaryTransfer(TransferRequest request, Account fromAccount){
        System.out.println("Fund Transfer Summary");
        System.out.println("Destination Account : " + request.getToAccountNumber());
        System.out.println("Transfer Amount     : $" + request.getAmount());
        System.out.println("Reference Number    : " + request.getReferenceNumber());
        System.out.println("Balance             : $" + fromAccount.getBalance().toString());
        System.out.println();
        summaryOption(fromAccount);
    }
}
