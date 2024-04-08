package org.example.ide2markingapi.feature.users;

import org.example.ide2markingapi.feature.users.dto.UserCreateRequest;
import org.example.ide2markingapi.feature.users.dto.UserPasswordRequest;
import org.example.ide2markingapi.feature.users.dto.UserResponse;
import org.example.ide2markingapi.feature.users.dto.UserUpdateRequest;
import org.springframework.data.domain.Page;

public interface UserService {

    void createNew(UserCreateRequest userCreateRequest);

    void changeUserPassword(UserPasswordRequest userPasswordRequest);

    UserResponse updateByUuid(String uuid, UserUpdateRequest userUpdateRequest);

    UserResponse findByUuid(String uuid);

    BasedMessage blockByUuid(String uuid);

    void deleteByUuid(String uuid);
    Page<UserResponse> findList(int page, int limit);

    String updateProfileImage(String uuid, String mediaName);


}
