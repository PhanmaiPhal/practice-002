package org.example.ide2markingapi.feature.cardtype;

import org.example.ide2markingapi.domain.CardType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CardTypeRepository  extends JpaRepository<CardType,Integer> {
    Optional<CardType> findByNameIgnoreCase(String name);
}
