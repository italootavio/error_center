package com.challenge.repository;

import com.challenge.model.EventLog;
import com.challenge.model.QEventLog;
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
public interface EventLogRepository extends CrudRepository<EventLog,Long>,QuerydslPredicateExecutor<EventLog>, QuerydslBinderCustomizer<QEventLog> {
    Page<EventLog> findAll(Pageable pageable);

    @SuppressWarnings("NullableProblems")
    @Override
    default void customize(QuerydslBindings bindings, QEventLog eventlog) {
        bindings.excluding(eventlog.id);
        bindings.bind(String.class)
                .first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }
}
