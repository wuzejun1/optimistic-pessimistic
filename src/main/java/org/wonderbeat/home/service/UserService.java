package org.wonderbeat.home.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.wonderbeat.domain.Balance;
import org.wonderbeat.domain.User;
import org.wonderbeat.repository.UserRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserService {

    @Inject
    private UserRepository userRepository;


    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer createUser(Integer userId) {
        userRepository.save(new User(userId, new Balance(0)));
        return userId;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Integer blockUser(Integer userId) {
        User user = userRepository.findByIdent(userId);
        user.block();
        userRepository.save(user);
        return userId;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
    public List<UserStatus> usersStatuses() {
        return StreamSupport.stream(userRepository.findAll().spliterator(), true).map(user -> new UserStatus(user.getIdent(),
                user.isBlocked(), user.getBalance().getAmount())).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Integer userBalance(Integer user) {
        return userRepository.findByIdent(user).getBalance().getAmount();
    }
}
