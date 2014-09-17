package org.wonderbeat.repository;


import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.wonderbeat.domain.User;

import javax.persistence.LockModeType;

public interface PessimisticUserRepository extends CrudRepository<User, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    User findByIdent(Integer ident);
}
