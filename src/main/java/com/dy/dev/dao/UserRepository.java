package com.dy.dev.dao;

import com.dy.dev.dto.entity.Role;
import com.dy.dev.dto.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.firstname like %:firstName% and u.lastname like %:lastName%")
    List<User> findByAll(String firstName, String lastName);

    @Query(nativeQuery = true,
            value = "select u.* from users u")
    List<User> findAll();

    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("update User u " +
            "set u.role = :role" +
            " where u.id in (:ids)")
    int updateRole(Role role, Long... ids);

    Optional<User> findById(Integer id);

//    @EntityGraph(attributePaths = {"company", "company.locales"})
    Slice<User> findAllBy(Pageable pageable);

    @EntityGraph(attributePaths = {"company", "company.locales"})
    Page<User> findAll(Pageable pageable);

    void deleteById(Integer id);

}
