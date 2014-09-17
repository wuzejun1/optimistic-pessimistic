package org.wonderbeat.home.service;


import java.io.Serializable;

public class UserStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private boolean blocked;

    private Integer amount;

    public UserStatus(Integer userId, boolean blocked, Integer amount) {
        this.userId = userId;
        this.blocked = blocked;
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }
}
