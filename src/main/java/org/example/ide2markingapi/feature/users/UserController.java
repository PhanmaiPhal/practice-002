package org.example.ide2markingapi.feature.users;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.ide2markingapi.domain.User;
import org.example.ide2markingapi.feature.users.dto.UserPasswordRequest;
import org.example.ide2markingapi.feature.users.dto.UserResponse;
import org.example.ide2markingapi.feature.users.dto.UserUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

   private final UserService userService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    void createNew(@Valid @RequestBody UserCreateRequest userCreateRequest){
        userService.createNew(userCreateRequest);
    }

    @PatchMapping("/{uuid}")
    UserResponse updateByUuid(@PathVariable String uuid,
                              @RequestBody UserUpdateRequest userUpdateRequest) {
        return userService.updateByUuid(uuid, userUpdateRequest);
    }


    @GetMapping("/{uuid}")
    UserResponse findByUuid(@PathVariable String uuid) {
        return userService.findByUuid(uuid);
    }

    @PutMapping("/{uuid}/block")
    BasedMessage blockByUuid(@PathVariable String uuid) {
        return userService.blockByUuid(uuid);
    }

    //Delete user by UUID (hard delete)/{uuid}
    @DeleteMapping
    void deleteByUuid(@PathVariable String uuid){
        userService.deleteByUuid(uuid);
    }
    //Disable user by UUID (soft delete)/{uuid}/disable

    //Enable user by UUID (uuid)/enable

    @GetMapping
    Page<UserResponse> findList(
          @RequestParam(required = false,defaultValue = "0") int page,
          @RequestParam(required = false,defaultValue = "2") int limit
    ){
        return userService.findList(page, limit);
    }
}
