package com.db.awmd.challenge.repository;


import com.db.awmd.challenge.domain.Transaction;
import com.db.awmd.challenge.exception.DuplicateTransactionIdException;

public interface TransactionRepository {

    void create(Transaction transaction) throws DuplicateTransactionIdException;

    Transaction getTransaction(String transactionId);

    void clearTransactions();
}
