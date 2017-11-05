package com.db.awmd.challenge.service;

import com.db.awmd.challenge.domain.Account;

import java.math.BigDecimal;

import static java.lang.String.format;


interface FundsManager {

  void transferFunds(final Account from,
                     final Account to,
                     final BigDecimal amount);

  static String fundsTransferredMessage(final Account to, final BigDecimal amount) {
    return format("fund [%.2f] transferred to account [%s]", amount, to.getAccountId());
  }

  static String fundsReceivedMessaged(final Account from, final BigDecimal amount) {
    return format("fund [%.2f] received from account [%s]", amount, from.getAccountId());
  }
}
