package org.wonderbeat.home;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class UserCommand implements Serializable {

    private static final long serialVersionUID = 1L;

    @Range(min = 0, message = "User ID should be greater than 0")
    @NotNull
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
