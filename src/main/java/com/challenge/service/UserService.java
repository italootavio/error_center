package com.challenge.service;
import com.challenge.model.User;
import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserService {
    User save(User user, String action);
    Page<User> findAll(Predicate predicate, Pageable pageable);
    Optional<User> findById(Long id);
    void deleteById(Long id);
}
