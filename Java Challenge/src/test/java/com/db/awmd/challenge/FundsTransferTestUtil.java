package com.db.awmd.challenge;

import com.db.awmd.challenge.domain.Account;
import com.db.awmd.challenge.service.AccountsService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static java.util.stream.IntStream.range;
import static org.junit.Assert.fail;

public class FundsTransferTestUtil {

    public static final int MAX_AMOUNT = 1000000;

    private final ExecutorService threadPool = Executors.newFixedThreadPool(20);
    private final CountDownLatch latch = new CountDownLatch(1);

    private final AccountsService accountsService;
    private Account from;
    private Account to;

    public FundsTransferTestUtil(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    public FundsTransferTestUtil transferFundsFrom(Account from) {
        this.from = from;
        return this;
    }

    public FundsTransferTestUtil to(Account to) {
        this.to = to;
        return this;
    }

    public static FundsTransferTestUtil using(AccountsService accountsService) {
        return new FundsTransferTestUtil(accountsService);
    }

    public void execute() throws InterruptedException {
        List<Runnable> runners = createRunnable();

        range(0, MAX_AMOUNT).forEach(n -> runners.forEach(threadPool::execute));

        latch.countDown();
        threadPool.shutdown();
        threadPool.awaitTermination(60, TimeUnit.SECONDS);
    }

    private List<Runnable> createRunnable() {
        Tuple a = new Tuple(from, to);
        Tuple b = new Tuple(to, from);

        List<Runnable> runners = new ArrayList<>();
        runners.add(new TransferRunner(a, latch));
        runners.add(new TransferRunner(b, latch));

        return runners;
    }

    private class TransferRunner implements Runnable {

        private final Tuple tuple;
        private final CountDownLatch latch;

        TransferRunner(Tuple tuple, CountDownLatch latch) {
            this.tuple = tuple;
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                fail(e.getMessage());
            }
            accountsService.transferFunds(tuple.from, tuple.to, new BigDecimal(1d));
        }
    }

    private class Tuple {

        private final Account from;
        private final Account to;

        Tuple(Account from, Account to) {
            this.from = from;
            this.to = to;
        }
    }
}
