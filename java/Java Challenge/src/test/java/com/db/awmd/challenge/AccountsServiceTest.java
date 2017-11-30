package com.db.awmd.challenge;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.exception.DuplicateAccountIdException;
import com.db.awmd.challenge.exception.InsufficientFundsException;
import com.db.awmd.challenge.repository.AccountsRepository;
import com.db.awmd.challenge.repository.AccountsRepositoryInMemory;
import com.db.awmd.challenge.service.AccountsService;
import com.db.awmd.challenge.service.NotificationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

import static com.db.awmd.challenge.FundsTransferTestUtil.MAX_AMOUNT;
import static com.db.awmd.challenge.FundsTransferTestUtil.using;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountsServiceTest {

  @TestConfiguration
  public static class TestConfig {
    @Bean
    @Primary
    public NotificationService notificationService() {
      return new MockNotificationService();
    }

    @Bean
    public AccountsRepository accountsRepository() {
      return new AccountsRepositoryInMemory();
    }
  }

  @Autowired
  private MockNotificationService notificationService;

  public static final String ACCOUNT_ID_FROM = "Id-122";
  public static final String ACCOUNT_ID_TO = "Id-123";

  @Autowired
  private AccountsService accountsService;

  @Test
  public void addAccount() throws Exception {
    Account account = new Account(ACCOUNT_ID_TO);
    account.setBalance(new BigDecimal(1000));
    this.accountsService.createAccount(account);

    assertThat(this.accountsService.getAccount(ACCOUNT_ID_TO)).isEqualTo(account);
  }

  @Test
  public void addAccount_failsOnDuplicateId() throws Exception {
    String uniqueId = "Id-" + System.currentTimeMillis();
    Account account = new Account(uniqueId);
    this.accountsService.createAccount(account);

    try {
      this.accountsService.createAccount(account);
      fail("Should have failed when adding duplicate account");
    } catch (DuplicateAccountIdException ex) {
      assertThat(ex.getMessage()).isEqualTo("Account id " + uniqueId + " already exists!");
    }

  }

  @Test(expected = InsufficientFundsException.class)
  public void attemptToTransferFromLowBalanceAccount() {
    accountsService.transferFunds(from(), to(), amount(MAX_AMOUNT+1));
  }

  @Test
  public void notificationSentOnFundsTransferred() {
    accountsService.transferFunds(from(), to(), amount(10));

    notificationService.checkMessages(ACCOUNT_ID_FROM, "fund [10.00] transferred to account [Id-123]");
    notificationService.checkMessages(ACCOUNT_ID_TO, "fund [10.00] received from account [Id-122]");
  }

  @Test
  public void transferFundsBetweenAccounts() throws InterruptedException {
    Account from = from();
    Account to = to();

    using(accountsService).transferFundsFrom(from).to(to).execute();

    assertThat(from.getBalance()).isEqualTo(new BigDecimal(MAX_AMOUNT));
    assertThat(to.getBalance()).isEqualTo(new BigDecimal(MAX_AMOUNT));
  }

  private static Account from() {
    return new Account(ACCOUNT_ID_FROM, new BigDecimal(MAX_AMOUNT));
  }

  private static Account to()  {
    return new Account(ACCOUNT_ID_TO, new BigDecimal(MAX_AMOUNT));
  }

  private static BigDecimal amount(int amount) {
    return new BigDecimal(amount);
  }
}
