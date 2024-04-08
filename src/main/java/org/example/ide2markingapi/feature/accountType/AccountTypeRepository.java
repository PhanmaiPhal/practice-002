package org.example.ide2markingapi.feature.accountType;

import org.example.ide2markingapi.domain.AccountType;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountTypeRepository extends JpaRepository<AccountType,Integer> {
    Optional<AccountType> findByAliasIgnoreCase(String alias);

    Optional<AccountType> findByAlias(@NotBlank(message = "Account type alias is required") String s);
}
