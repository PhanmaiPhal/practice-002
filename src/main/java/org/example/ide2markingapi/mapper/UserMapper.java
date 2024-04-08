package org.example.ide2markingapi.mapper;

import org.example.ide2markingapi.domain.User;
import org.example.ide2markingapi.domain.UserAccount;
import org.example.ide2markingapi.feature.users.dto.UserCreateRequest;
import org.example.ide2markingapi.feature.users.dto.UserDetailsResponse;
import org.example.ide2markingapi.feature.users.dto.UserResponse;
import org.example.ide2markingapi.feature.users.dto.UserUpdateRequest;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    //SourceType = UserCreateRequest (Parameter)
    //TargetType = User (ReturnType)


//    void fromUserCreateRequest2(@MappingTarget User user,UserCreateRequest userCreateRequest);

    UserDetailsResponse toUserDetailsResponse(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void fromUserUpdateRequest(UserUpdateRequest userUpdateRequest,@MappingTarget User user);
    UserResponse toUserResponse(User user);
//    List<UserResponse> toUserResponseList(List<User> users);
    @Named("mapUserResponse")
    default UserResponse mapUserResponse(List<UserAccount> userAccountList){
        return toUserResponse(userAccountList.get(0).getUser());
    }

    User fromUserCreateRequest(UserCreateRequest request);
}
