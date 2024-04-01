package org.example.ide2markingapi.mapper;

import org.example.ide2markingapi.domain.User;
import org.example.ide2markingapi.feature.users.UserCreateRequest;
import org.example.ide2markingapi.feature.users.dto.UserDetailsResponse;
import org.example.ide2markingapi.feature.users.dto.UserResponse;
import org.example.ide2markingapi.feature.users.dto.UserUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    //SourceType = UserCreateRequest (Parameter)
    //TargetType = User (ReturnType)

    User fromUserCreateRequest(UserCreateRequest userCreateRequest);

    //void fromUserCreateRequest2(@MappingTarget User user,UserCreateRequest userCreateRequest);

    UserDetailsResponse toUserDelaitsResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUserUpdateRequest(UserUpdateRequest userUpdateRequest,@MappingTarget User user);

    UserResponse toUserResponse(User user);
}
