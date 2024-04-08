package org.example.ide2markingapi.feature.account;

import org.example.ide2markingapi.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account,Long> {
    Optional<Account> findAccountByActNo(String ActNumber);


    @Modifying
    @Query("""
        UPDATE Account AS a
        SET a.isHidden=TRUE 
        WHERE a.actNo=?1
   
""")
    void hiddenAccountActNo(String ActNo);

    Optional<Account> findByActNo(String actNo);

    boolean existsByActNo(String actNo);
}


