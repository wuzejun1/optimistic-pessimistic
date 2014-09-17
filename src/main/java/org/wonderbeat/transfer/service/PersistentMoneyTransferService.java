package org.wonderbeat.transfer.service;

import org.wonderbeat.domain.User;
import org.wonderbeat.repository.PessimisticUserRepository;


public class PersistentMoneyTransferService implements MoneyTransferService {

    PessimisticUserRepository repository;

    public PersistentMoneyTransferService(PessimisticUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Integer deposit(Integer userId, Integer amount) {
        User user = repository.findByIdent(userId);
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

    @Override
    public Integer withdraw(Integer userId, Integer amount) {
        User user = repository.findByIdent(userId);
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
