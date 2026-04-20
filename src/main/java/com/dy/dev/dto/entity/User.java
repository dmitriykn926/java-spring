package com.dy.dev.dto.entity;

import jakarta.persistence.*;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.envers.Audited;
import org.hibernate.envers.NotAudited;
import org.hibernate.envers.RelationTargetAuditMode;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString(exclude = {"company", "userChats"})
@EqualsAndHashCode(exclude = {"company", "userChats"})
@Table(name = "users")
@Audited(targetAuditMode = RelationTargetAuditMode.NOT_AUDITED)
public class User extends AuditEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String firstname;
    private String lastname;

    @NotAudited 
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @NotAudited
    private Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    @NotAudited
    private Company company;

    @Builder.Default
    @OneToMany(mappedBy = "user")
    @NotAudited
    private List<UserChat> userChats = new ArrayList<>();
}
