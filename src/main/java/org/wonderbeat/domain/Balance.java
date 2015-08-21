package org.wonderbeat.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Version;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "TEST_BALANCE")
public class Balance extends AbstractPersistable<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(nullable = false)
    private Integer amount = 0;

    @Version
    private Timestamp version;

    public Balance() {
    }

    public Balance(int amount) {
        this.amount = amount;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Timestamp getVersion() {
        return version;
    }

    public void setVersion(Timestamp version) {
        this.version = version;
    }
}
