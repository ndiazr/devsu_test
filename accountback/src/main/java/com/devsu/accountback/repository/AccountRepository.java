package com.devsu.accountback.repository;

import com.devsu.accountback.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "select " +
            "m.`date` as date," +
            "c.name as clientName," +
            "a.`number`as accountNumber," +
            "a.`type` as accountType," +
            "a.initial_balance as initialBalance," +
            "a.state as accountState," +
            "case when m.`type` = 'RETIRO' then concat('-', m.amount) when m.`type` = 'DEPOSITO' then m.amount end as movementAmount," +
            "m.balance as accountBalance " +
            "from accounts a " +
            "left join clients c on c.id = a.client_id " +
            "left join movements m on m.account_id = a.id " +
            "where c.id = :clientId " +
            "and m.`date` between :startDate and :endDate", nativeQuery = true)
    List<Map<String, Object>> findAccountByClient(
            @Param("clientId") Long clientId,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate);
}
