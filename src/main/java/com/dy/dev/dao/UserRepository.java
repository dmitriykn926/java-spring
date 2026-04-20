package com.dy.dev.dao;

import com.dy.dev.dto.PersonalInfo;
import com.dy.dev.dto.PersonalInfo2;
import com.dy.dev.dto.entity.Role;
import com.dy.dev.dto.entity.User;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>, FilterUserRepository {

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

//    @QueryHints(@QueryHint(name = "org.hibernate.fetchSize", value = "50"))
//    @Lock(LockModeType.PESSIMISTIC_WRITE)
//    @EntityGraph(attributePaths = {"userChats", "company"})
//    Optional<User> findById(Integer id);

//    @EntityGraph("User.company")
    Optional<User> findById(Integer id);

//    @EntityGraph(attributePaths = {"company", "company.locales"})
    Slice<User> findAllBy(Pageable pageable);

//    @EntityGraph(attributePaths = {"company", "company.locales"})
    Page<User> findAll(Pageable pageable);

    //TODO Projections
//    List<PersonalInfo> findAllByCompanyId(Long companyId);
//    <T> List<T> findAllByCompanyId(Long companyId, Class<T> clazz);
    @Query(value = "SELECT firstname, lastname, birth_date as birthDate from users where company_id = :companyId",
            nativeQuery = true)
    List<PersonalInfo2> findAllByCompanyId(Long companyId); //TODO A native Query should be applied here


    void deleteById(Integer id);

}
