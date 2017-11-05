package com.db.awmd.challenge.repository;


import com.db.awmd.challenge.domain.Transaction;
import com.db.awmd.challenge.exception.DuplicateTransactionIdException;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransactionRepositoryInMemory implements TransactionRepository{

    private final Map<String, Transaction> transactions = new ConcurrentHashMap<>();

    @Override
    public void create(Transaction transaction) {
        Transaction previous = this.transactions.putIfAbsent(transaction.getId(), transaction);
        if (previous != null) {
            throw new DuplicateTransactionIdException(
                    "Transaction id " + transaction.getId() + " already exists!");
        }
    }

    @Override
    public Transaction getTransaction(String transactionId) {
        return this.transactions.get(transactionId);
    }

    @Override
    public void clearTransactions() {
        this.transactions.clear();
    }
}
