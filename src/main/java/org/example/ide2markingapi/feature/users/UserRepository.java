package org.example.ide2markingapi.feature.users;

import lombok.RequiredArgsConstructor;
import org.example.ide2markingapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserRepository extends JpaRepository<User,Long> {

    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByNationalCardId(String nationalCardId);
    boolean existsByStudentIdCard(String studentIdCard);
    boolean existsByName(String name);
    Optional<User> findByPassword(String password);

    Optional<User> findByUuid(String uuid);

    @Query("SELECT u FROM User AS u WHERE u.uuid = :uuid")
    boolean existsByUuid(String uuid);



    @Modifying
    @Query("UPDATE User AS u SET u.isBlocked = TRUE WHERE u.uuid = ?1")

    void blockByUuid(String uuid);


    //@Query("SELECT u FROM  User As u WHERE u.uuid= :uuid")
   // Optional<User> findByUuid

}
