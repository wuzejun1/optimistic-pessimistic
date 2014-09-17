package org.wonderbeat.transfer;


import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class BalanceUpdateCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull
    @Range(min = 0, message = "User ID should be greater than 0")
    private Integer userId;
    @NotNull
    @Range(min = 0, message = "Don't try to hack me ;)")
    private Integer amount;

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
}
