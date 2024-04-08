package org.example.ide2markingapi.feature.users;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.ide2markingapi.domain.Role;
import org.example.ide2markingapi.domain.User;
import org.example.ide2markingapi.feature.users.dto.UserCreateRequest;
import org.example.ide2markingapi.feature.users.dto.UserPasswordRequest;
import org.example.ide2markingapi.feature.users.dto.UserResponse;
import org.example.ide2markingapi.feature.users.dto.UserUpdateRequest;
import org.example.ide2markingapi.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

    @Value("${media.base-uri}")
    String mediaBaseUri;


   @Override
    public void createNew(UserCreateRequest request) {
        User user = userMapper.fromUserCreateRequest(request);

        if(userRepository.existsByNationalCardId(request.nationalCardId())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "National card id is already existed!"
            );
        }

        if(userRepository.existsByPhoneNumber(request.phoneNumber())){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Phone number is already existed!"
            );
        }

        if(userRepository.existsByStudentIdCard(request.studentIdCard())){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Student card id is already existed!"
            );
        }

        if(!request.password().equals(request.confirmedPassword())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Password does not match"
            );
        }

        // add role
        List<Role> roleList = new ArrayList<>();
        Role role = roleRepository.findByName("USER")
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User role does not exist!"
                ));
        user.setRoles(roleList);
        request.roles().forEach(r -> {
            Role newRole = roleRepository.findByName(r.name())
                    .orElseThrow(()->
                            new ResponseStatusException(
                                    HttpStatus.NOT_FOUND,
                                    "Role User has not been found!"
                            ));
            roleList.add(newRole);
        });

        // add role to roleList
        roleList.add(role);

        user.setUuid(UUID.randomUUID().toString());
        user.setProfileImage("Avatar.png");
        user.setCreatedAt(LocalDateTime.now());
        user.setIsBlocked(false);
        user.setIsDeleted(false);
        user.setRoles(roleList);

        userRepository.save(user);
    }


    @Override
    public void changeUserPassword(UserPasswordRequest userPasswordRequest) {
        User user = userRepository.findByPassword(userPasswordRequest.oldPassword())
                .orElseThrow(()-> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Password does not match!"
                ));

        if(!userPasswordRequest.NewPassword().equals(userPasswordRequest.confirmedNewPassword())){
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Password does not match!"
            );
        }

        user.setPassword(userPasswordRequest.NewPassword());

        userRepository.save(user);

    }

    @Override
    public UserResponse updateByUuid(String uuid, UserUpdateRequest userUpdateRequest) {
        // check uuid if exists
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User has not been found!"));

        log.info("before user: {}", user);

        userMapper.fromUserUpdateRequest(userUpdateRequest, user);

        user = userRepository.save(user);

        return userMapper.toUserResponse(user);
}

    @Override
    public UserResponse findByUuid(String uuid) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User has not been found!"));

        return userMapper.toUserResponse(user);
    }

    @Transactional
    @Override
    public BasedMessage blockByUuid(String uuid) {
        if (!userRepository.existsByUuid(uuid)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "User has not been found!");
        }

        userRepository.blockByUuid(uuid);

        return new BasedMessage("User has been blocked");
    }

    @Override
    public void deleteByUuid(String uuid) {

    }

    @Override
    public Page<UserResponse> findList(int page, int limit) {
        //Create pageRequest object
        PageRequest pageRequest= PageRequest.of(page,limit);

        Page<User> users = userRepository.findAll(pageRequest);

        return users.map(userMapper::toUserResponse);
    }

    @Override
    public String updateProfileImage(String uuid, String mediaName) {
        User user = userRepository.findByUuid(uuid)
                .orElseThrow(()->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "User has not been found!"));
        user.setProfileImage(mediaName);

        userRepository.save(user);

        return mediaBaseUri +"mages/" +mediaName;
    }


}
