package org.wonderbeat.repository;

import org.springframework.data.jpa.repository.Lock;
import org.wonderbeat.domain.User;

import javax.persistence.LockModeType;

public interface UserRepository {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    User findByIdent(Integer ident);
}
