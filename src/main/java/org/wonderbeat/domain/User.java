package org.wonderbeat.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "USER")
public class User extends AbstractPersistable<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(unique = true, updatable = false, nullable = false)
    private Integer ident;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true, optional = false)
    @JoinColumn(name = "balance_id", unique = true)
    private Balance balance;

    @Column(nullable = false)
    private Boolean blocked = false;

    public User() {
    }

    public User(Integer userId, Balance balance) {
        ident = userId;
        this.balance = balance;
    }

    public Boolean isBlocked() {
        return blocked;
    }

    public void block() {
        blocked = true;
    }

    public void unBlock() {
        blocked = false;
    }

    public void setBlocked(Boolean blocked) {
        this.blocked = blocked;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public Integer getIdent() {
        return ident;
    }

    public void setIdent(Integer ident) {
        this.ident = ident;
    }
}
