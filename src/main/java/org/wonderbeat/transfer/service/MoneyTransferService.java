package org.wonderbeat.transfer.service;


public interface MoneyTransferService {
    Integer deposit(Integer userId, Integer amount);

    Integer withdraw(Integer userId, Integer amount);

    /**
     *
     * @param from user ID amount should be transferred from
     * @param to user ID amount should be transferred to
     * @param amount amount
     * @return updated balance for user transfer was made to
     */
    Integer transfer(Integer from, Integer to, Integer amount);
}
