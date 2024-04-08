package org.example.ide2markingapi.mapper;

import org.example.ide2markingapi.domain.AccountType;
import org.example.ide2markingapi.feature.accountType.dto.AccountTypeResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountTypeMapper {

    List<AccountTypeResponse> toListAccountTypeResponse(List<AccountType> accountTypes);

    AccountTypeResponse toAccountTypeResponse(AccountType accountType);
}
