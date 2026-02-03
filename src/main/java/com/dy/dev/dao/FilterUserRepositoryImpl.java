package com.dy.dev.dao;

import com.dy.dev.dto.UserFilter;
import com.dy.dev.dto.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;

    @Override
    public List<User> findAllByFilter(UserFilter userFilter) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<User> userQuery = criteriaBuilder.createQuery(User.class);

        Root<User> userRoot = userQuery.from(User.class);

        userQuery.select(userRoot);

        List<Predicate> predicates = new ArrayList<>();
        if (userFilter.firstName() != null) {
            predicates.add(criteriaBuilder.like(userRoot.get("firstname"), userFilter.firstName()));
        }
        if (userFilter.lastname() != null) {
            predicates.add(criteriaBuilder.like(userRoot.get("lastname"), userFilter.lastname()));
        }
        if (userFilter.birthDate() != null) {
            predicates.add(criteriaBuilder.lessThan(userRoot.get("birthDate"), userFilter.birthDate()));
        }

        userQuery.where(predicates.toArray(Predicate[]::new));

        return entityManager.createQuery(userQuery).getResultList();
    }
}
