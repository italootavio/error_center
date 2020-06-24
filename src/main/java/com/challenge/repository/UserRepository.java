package com.challenge.repository;

import com.challenge.model.QUser;
import com.challenge.model.User;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User,Long>, QuerydslPredicateExecutor<User>, QuerydslBinderCustomizer<QUser> {
    User findByUsername(String login);
    boolean existsByUsername(String login);
    Page<User> findAll(Pageable pageable);

    @SuppressWarnings("NullableProblems")
    @Override
    default void customize(QuerydslBindings bindings, QUser user) {
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);

    }
}
