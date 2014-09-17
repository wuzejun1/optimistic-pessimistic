package org.wonderbeat.transfer.service.impl;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wonderbeat.transfer.service.MoneyTransferService;

/**
 * Optimistic concurrency control ROCKS on big Databases.
 */
public class OptimisticLockTransferService implements MoneyTransferService {

    MoneyTransferService delegate;

    public OptimisticLockTransferService(MoneyTransferService delegate) {
        this.delegate = delegate;
    }


    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer deposit(Integer userId, Integer amount) {
        return delegate.deposit(userId, amount);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer withdraw(Integer userId, Integer amount) {
        return delegate.withdraw(userId, amount);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public Integer transfer(Integer from, Integer to, Integer amount) {
        return delegate.transfer(from, to, amount);
    }

}
