package com.dy.dev.integration;

import com.dy.dev.annotation.IT;
import com.dy.dev.dao.CompanyRepository;
import com.dy.dev.dto.entity.Company;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@IT
@RequiredArgsConstructor
@Transactional
@Rollback
public class CompanyRepositoryIT {

    private final EntityManager entityManager;
    private final TransactionTemplate transactionTemplate;

    private final CompanyRepository companyRepository;

    @Test
    void deleteTest() {
        Optional<Company> company = companyRepository.findById(1);
        assertThat(company).isPresent();

        company.ifPresent(companyRepository::delete);
        entityManager.flush();
    }

    @Test
    void findCompanyByName() {
        String googleName = "Google";
        Optional<Company> company = companyRepository.findByName(googleName);
        assertThat(company).isPresent();
        System.out.println(company.get());
    }

    @ParameterizedTest
    @ValueSource(strings = {"Google", "Meta", "Amazon"})
    void findCompanyByNameContainingIgnoreCase(String name) {
        Optional<Company> company = companyRepository.findCompanyByNameContainingIgnoreCase(name);
        assertThat(company).isPresent();
        assertThat(company.get().getName()).isEqualTo(name);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Yandex"})
    void findAllCompanyByNameContainingIgnoreCase(String name) {
        List<Company> companies = companyRepository.findAllByNameContainingIgnoreCase(name);

        assertThat(companies).isNotEmpty();

        assertThat(companies).hasSize(5);

        assertThat(companies).extracting(Company::getName).contains("Yandex");
    }

    @Test
    void find() {
        Company company = entityManager.find(Company.class, 1);

        assertNotNull(company);
        assertThat(company.getLocales()).hasSize(2);
    }

    @Test
    void insertCompany() {
        Company company = Company.builder()
                .name("Yandex7")
                .build();
        entityManager.persist(company);

        assertThat(company.getId()).isNotNull();
    }
}
