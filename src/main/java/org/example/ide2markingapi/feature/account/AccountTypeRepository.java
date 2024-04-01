package org.example.ide2markingapi.feature.account;

import org.example.ide2markingapi.domain.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountTypeRepository extends JpaRepository<AccountType,Integer> {
    Optional<AccountType> findByAliasIgnoreCase(String alias);
}
