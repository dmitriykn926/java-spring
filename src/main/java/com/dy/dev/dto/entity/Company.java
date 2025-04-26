package com.dy.dev.dto.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashMap;
import java.util.Map;

@NamedQuery(
        name = "Company.findByName",
        query = "select c from Company c where lower(c.name) = lower(:companyName) " // TODO name should be matches with param name of findByName method, or we can use @Param annotation
)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table
@EqualsAndHashCode(exclude = "locales")
@ToString(exclude = "locales")
public class Company implements BaseEntity<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String name;

    @Builder.Default
    @ElementCollection
    @CollectionTable(name = "company_locales", joinColumns = @JoinColumn(name = "company_id"))
    @MapKeyColumn(name = "lang")
    @Column(name = "description")
    private Map<String, String> locales = new HashMap<>();

}
