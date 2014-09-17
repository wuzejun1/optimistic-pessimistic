package org.wonderbeat.transfer.service.impl;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wonderbeat.transfer.service.MoneyTransferService;

/**
 * Strong consistency model prevents consistency errors. But slow
 */
public class IsolationBasedTransferService implements MoneyTransferService {

    MoneyTransferService delegate;

    public IsolationBasedTransferService(MoneyTransferService delegate) {
        this.delegate = delegate;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
            rollbackFor = Exception.class)
    public Integer deposit(Integer userId, Integer amount) {
        return delegate.deposit(userId, amount);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
            rollbackFor = Exception.class)
    public Integer withdraw(Integer userId, Integer amount) {
        return delegate.withdraw(userId, amount);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.SERIALIZABLE,
            rollbackFor = Exception.class)
    public Integer transfer(Integer from, Integer to, Integer amount) {
        return delegate.transfer(from, to, amount);
    }

}
