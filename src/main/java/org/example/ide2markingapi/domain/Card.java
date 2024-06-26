package org.example.ide2markingapi.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="card")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true,nullable = false)
    private String number;

    @Column(nullable = false)
    private String cvv;

    private String holder;

    private LocalDate issueDate;

    private LocalDate expiredAt;

    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name= "type-id")
    private CardType cardType;

    @OneToOne(mappedBy = "card")
    private Account account;
}
