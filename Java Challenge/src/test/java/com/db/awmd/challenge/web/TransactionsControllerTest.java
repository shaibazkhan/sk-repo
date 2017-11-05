package com.db.awmd.challenge.web;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.service.AccountsService;
import com.db.awmd.challenge.service.NotificationService;
import com.db.awmd.challenge.service.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@MockBean(NotificationService.class)
@SpringBootTest
@WebAppConfiguration
public class TransactionsControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private AccountsService accountsService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private WebApplicationContext webApplicationContext;

    private static final String TRANSACTION_ID = "TRAN-111";
    private static final String FROM_ACCOUNT_ID = "Id-122";
    private static final String TO_ACCOUNT_ID = "Id-123";

    @Before
    public void prepareMockMvc() {
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();

        // Reset the existing data before each test.
        transactionService.getTransactionRepository().clearTransactions();
        accountsService.getAccountsRepository().clearAccounts();

        accountsService.createAccount(new Account("Id-122", new BigDecimal("1000.00")));
        accountsService.createAccount(new Account("Id-123", new BigDecimal("1000.00")));
    }

    @Test
    public void transferNegativeBalance() throws Exception {
        createTransaction();

        this.mockMvc.perform(put("/v1/transactions").contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"id\":\""+TRANSACTION_ID+"\",\"from\":\""+FROM_ACCOUNT_ID+"\",\"to\":\""+TO_ACCOUNT_ID+"\",\"amount\":-20.00}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void transferFundsBetweenAccounts() throws Exception {
        createTransaction();

        transferFunds();

        checkAccountsBalances();
    }

    @Test
    public void transactionNotFound() throws Exception {
        this.mockMvc.perform(put("/v1/transactions").contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"id\":\"34534\",\"from\":\""+FROM_ACCOUNT_ID+"\",\"to\":\""+TO_ACCOUNT_ID+"\",\"amount\":20.00}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void payerAccountNotFound() throws Exception {
        createTransaction();

        this.mockMvc.perform(put("/v1/transactions").contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"id\":\""+TRANSACTION_ID+"\",\"from\":\"JUNK\",\"to\":\""+TO_ACCOUNT_ID+"\",\"amount\":20.00}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void payeeAccountNotFound() throws Exception {
        createTransaction();

        this.mockMvc.perform(put("/v1/transactions").contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"id\":\""+TRANSACTION_ID+"\",\"from\":\""+FROM_ACCOUNT_ID+"\",\"to\":\"JUNK\",\"amount\":20.00}"))
                .andExpect(status().isBadRequest());
    }


    private void checkAccountsBalances() throws Exception {
        this.mockMvc.perform(get("/v1/accounts/" + FROM_ACCOUNT_ID))
                .andExpect(status().isOk())
                .andExpect(
                        content().string("{\"accountId\":\""+FROM_ACCOUNT_ID+"\",\"balance\":980.00}"));

        this.mockMvc.perform(get("/v1/accounts/" + TO_ACCOUNT_ID))
                .andExpect(status().isOk())
                .andExpect(
                        content().string("{\"accountId\":\""+TO_ACCOUNT_ID+"\",\"balance\":1020.00}"));
    }

    private void transferFunds() throws Exception {
        this.mockMvc.perform(put("/v1/transactions").contentType(MediaType.APPLICATION_JSON)
                .content(
                        "{\"id\":\""+TRANSACTION_ID+"\",\"from\":\""+FROM_ACCOUNT_ID+"\",\"to\":\""+TO_ACCOUNT_ID+"\",\"amount\":20.00}"))
                .andExpect(status().isOk());
    }

    private void createTransaction() throws Exception {
        this.mockMvc.perform(post("/v1/transactions").contentType(MediaType.APPLICATION_JSON)
                .content("{\"id\":\""+TRANSACTION_ID+"\"}")).andExpect(status().isCreated());
    }
}