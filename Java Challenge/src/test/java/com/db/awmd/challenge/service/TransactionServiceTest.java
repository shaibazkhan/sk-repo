package com.db.awmd.challenge.service;

import com.db.awmd.challenge.domain.Transaction;
import com.db.awmd.challenge.exception.DuplicateAccountIdException;
import com.db.awmd.challenge.exception.DuplicateTransactionIdException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@MockBean(NotificationService.class)
@SpringBootTest
public class TransactionServiceTest {

    @Autowired
    private TransactionService transactionService;

    @Test
    public void createTransaction() {
        Transaction transaction = new Transaction("TRAN-123", "", "", BigDecimal.ZERO);
        this.transactionService.createTransaction(transaction);

        assertThat(this.transactionService.getTransaction("TRAN-123").get()).isEqualTo(transaction);
    }

    @Test
    public void duplicateTransaction() {
        String uniqueId = "Id-" + System.currentTimeMillis();

        Transaction transaction = new Transaction(uniqueId, "", "", BigDecimal.ZERO);
        this.transactionService.createTransaction(transaction);

        try {
            this.transactionService.createTransaction(transaction);
            fail("Should have failed when adding duplicate transaction");
        } catch (DuplicateTransactionIdException ex) {
            assertThat(ex.getMessage()).isEqualTo("Transaction id " + uniqueId + " already exists!");
        }
    }

}