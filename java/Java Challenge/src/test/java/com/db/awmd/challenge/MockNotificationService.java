package com.db.awmd.challenge;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.service.NotificationService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class MockNotificationService implements NotificationService {

  private Map<String, String> collected = new HashMap<>();

  @Override
  public void notifyAboutTransfer(Account account, String transferDescription) {
    collected.put(account.getAccountId(), transferDescription);
  }

  public void checkMessages(String accountId, String expected) {
    assertThat(collected.get(accountId)).isEqualTo(expected);
  }
}
