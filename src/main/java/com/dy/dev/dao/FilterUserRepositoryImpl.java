package com.dy.dev.dao;

import com.dy.dev.dto.UserFilter;
import com.dy.dev.dto.UserReadDto;
import com.dy.dev.dto.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FilterUserRepositoryImpl implements FilterUserRepository {

    private final EntityManager entityManager;
    private final JdbcTemplate jdbcTemplate;

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
        if (userFilter.lastName() != null) {
            predicates.add(criteriaBuilder.like(userRoot.get("lastname"), userFilter.lastName()));
        }
        if (userFilter.birthDate() != null) {
            predicates.add(criteriaBuilder.lessThan(userRoot.get("birthDate"), userFilter.birthDate()));
        }

        userQuery.where(predicates.toArray(Predicate[]::new));

        return entityManager.createQuery(userQuery).getResultList();
    }

    @Override
    public List<User> findAllByCompanyId(Integer companyId) {
        return jdbcTemplate.query("select * from users where company_id = ?", (rs, rowNum) -> {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setFirstname(rs.getString("firstname"));
            user.setLastname(rs.getString("lastname"));
            return user;
        }, companyId);
    }

    @Override
    public void updateUsersByCompanyId(List<User> users) {
        List<Object[]> list = users.stream()
                .map(user -> new Object[]{user.getCompany().getId(), user.getId()})
                .toList();
        jdbcTemplate.batchUpdate("update set company_id = ? where user_id = ?", list);
    }
}
