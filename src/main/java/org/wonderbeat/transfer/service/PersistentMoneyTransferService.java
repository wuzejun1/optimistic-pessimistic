package org.wonderbeat.transfer.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wonderbeat.domain.User;
import org.wonderbeat.repository.OptimisticUserRepository;
import org.wonderbeat.repository.UserQuery;
import org.wonderbeat.repository.UserRepository;
import org.wonderbeat.repository.PessimisticUserRepository;

import javax.annotation.Resource;
import javax.inject.Inject;


@Service
public class PersistentMoneyTransferService implements MoneyTransferService {

    @Inject
    UserRepository repository;

    @Resource(name="pessimisticUserRepository")
    UserQuery pessimisticUserRepository;
    @Resource(name="optimisticUserRepository")
    UserQuery optimisticUserRepository;

    @Resource(name="pessimisticUserRepository")
    private UserQuery delegator;

    public void switchLockType(String lockType) {
        if (lockType.equalsIgnoreCase("optimistic")) delegator = optimisticUserRepository;
        if (lockType.equalsIgnoreCase("pessimistic")) delegator = pessimisticUserRepository;
    }


    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    @Override
    public Integer deposit(Integer userId, Integer amount) {
        User user = delegator.findByIdent(userId);
        Integer currentBalance = user.getBalance().getAmount();
        if(Integer.MAX_VALUE - currentBalance < amount ) {
            throw new OwerflowException("So much money. WOW!");
        }
        if(user.isBlocked()) {
            throw new UserBlockedException("Transactions forbidden for blocked users");
        }
        Integer updatedBalance = currentBalance + amount;
        user.getBalance().setAmount(updatedBalance);
        repository.save(user);
        return updatedBalance;
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    @Override
    public Integer withdraw(Integer userId, Integer amount) {
        User user = delegator.findByIdent(userId);
        Integer currentBalance = user.getBalance().getAmount();
        Integer updatedBalance = currentBalance - amount;
        if(updatedBalance < 0) {
            throw new OutOfBoundsException("Нужно построить зиккурат");
        }
        if(user.isBlocked()) {
            throw new UserBlockedException("Transactions forbidden for blocked users");
        }
        user.getBalance().setAmount(updatedBalance);
        repository.save(user);
        return updatedBalance;
    }

    /**
     * Lower id blocks first to prevent deadlock
     */
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED,
            rollbackFor = Exception.class)
    @Override
    public Integer transfer(Integer from, Integer to, Integer amount) {
        if(from < to) {
            withdraw(from, amount);
            return deposit(to, amount);
        } else {
            Integer updated = deposit(to, amount);
            withdraw(from, amount);
            return updated;
        }
    }


}
