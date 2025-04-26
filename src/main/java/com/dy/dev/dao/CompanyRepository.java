package com.dy.dev.dao;

import com.dy.dev.dto.entity.Company;
import com.dy.dev.listener.dto.CompanyReadDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

//    @Query(name = "Company.findByName")
    @Query("select c from Company c " +
            " join fetch c.locales cl " +
            "where lower(c.name) = lower(:companyName) ")
    Optional<Company> findByName(@Param("companyName") String name);

    //TODO. Possible types: Optional, Entity, Future
    Optional<Company> findCompanyByNameContainingIgnoreCase(String name);

    //TODO. Possible types: Collections, Stream (batch -> then needs to be closed)
    List<Company> findAllByNameContainingIgnoreCase(String name);

    void delete(Company company);

}
