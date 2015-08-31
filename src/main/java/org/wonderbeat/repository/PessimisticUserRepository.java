package org.wonderbeat.repository;

import org.springframework.stereotype.Repository;
import org.wonderbeat.domain.User;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PessimisticUserRepository implements UserQuery {
    @Inject
    EntityManager em;
    @Transactional
    public User findByIdent(Integer ident) {
        Query query = em.createNativeQuery("select * from TEST_USER where ident=" + ident + " for update nowait", User.class);
        List<User> result = query.getResultList();
        if (result.isEmpty()) return null;
        return result.get(0);

    }
}
