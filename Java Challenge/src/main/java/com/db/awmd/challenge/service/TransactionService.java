package com.db.awmd.challenge.service;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.domain.Transaction;
import com.db.awmd.challenge.exception.UnsuccessfulFundsTransferException;
import com.db.awmd.challenge.repository.TransactionRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
public class TransactionService {

    @Getter
    private final TransactionRepository transactionRepository;

    private final AccountsService accountsService;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, AccountsService accountsService) {
        this.transactionRepository = transactionRepository;
        this.accountsService = accountsService;
    }

    public void createTransaction(Transaction transaction) {
        this.transactionRepository.create(transaction);
    }

    public Optional<Transaction> getTransaction(String transactionId) {
        return Optional.ofNullable(this.transactionRepository.getTransaction(transactionId));
    }

    public void doFundsTransfer(Transaction transaction) throws UnsuccessfulFundsTransferException {
        checkIfTransactionExists(transaction);
        transferFunds(transaction);
    }

    private void checkIfTransactionExists(Transaction transaction) {
        if (!this.getTransaction(transaction.getId()).isPresent()) {
            throw new UnsuccessfulFundsTransferException(format("transaction id %s doesn't exist", transaction.getId()));
        }
    }

    private void transferFunds(Transaction transaction) {
        this.accountsService.transferFunds(
                retrieveAccount(transaction.getFrom()),
                retrieveAccount(transaction.getTo()), transaction.getAmount());
    }

    private Account retrieveAccount(String accountId) {
        Account account = this.accountsService.getAccount(accountId);
        if(account == null) {
            throw new UnsuccessfulFundsTransferException(format("account id %s doesn't exist", accountId));
        }
        return account;
    }
}
