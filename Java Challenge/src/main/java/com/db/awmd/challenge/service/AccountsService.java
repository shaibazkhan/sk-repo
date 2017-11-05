package com.db.awmd.challenge.service;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.exception.InsufficientFundsException;
import com.db.awmd.challenge.repository.AccountsRepository;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.db.awmd.challenge.service.FundsManager.fundsReceivedMessaged;
import static com.db.awmd.challenge.service.FundsManager.fundsTransferredMessage;

@Service
public class AccountsService {

  @Getter
  private final AccountsRepository accountsRepository;

  private final NotificationService notificationService;

  private final FundsManager fundsManager = ((from, to, amount) -> {
    if (from.getBalance().compareTo(amount) < 0) {
         throw new InsufficientFundsException();
    }
    from.debit(amount);
    to.credit(amount);
    sendFundsTransferNotification(from, to, amount);
  });

  @Autowired
  public AccountsService(AccountsRepository accountsRepository, NotificationService notificationService) {
    this.accountsRepository = accountsRepository;
    this.notificationService = notificationService;
  }

  public void createAccount(Account account) {
    this.accountsRepository.createAccount(account);
  }

  public Account getAccount(String accountId) {
    return this.accountsRepository.getAccount(accountId);
  }

  public void transferFunds(final Account from, final Account to, final BigDecimal amount) {
    int result = from.getAccountId().compareTo(to.getAccountId());

    if(result < 0){
      synchronized (from) {
        synchronized (to) {
          fundsManager.transferFunds(from, to, amount);
        }
      }
    }

    if(result > 0) {
      synchronized (to) {
        synchronized (from) {
          fundsManager.transferFunds(from, to, amount);
        }
      }
    }

  }

  private void sendFundsTransferNotification(final Account from,
                                             final Account to,
                                             final BigDecimal amount) {
    notificationService.notifyAboutTransfer(from, fundsTransferredMessage(to,  amount));
    notificationService.notifyAboutTransfer(to, fundsReceivedMessaged(from, amount));
  }
}

