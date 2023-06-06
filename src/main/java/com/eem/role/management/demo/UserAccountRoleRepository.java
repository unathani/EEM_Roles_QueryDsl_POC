package com.eem.role.management.demo;

import com.querydsl.core.types.Predicate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;


public interface UserAccountRoleRepository extends JpaRepository<UserRolesTest, Long> , QuerydslPredicateExecutor<UserRolesTest> {
    @Query(value = "SELECT u.aggregate_id, u.family_name, u.given_name, u.pua_id, u.primary_email, u2.user_role, sua.sso_type FROM useraccount u join userroles_test u2 " +
            "on u.aggregate_id = u2.aggregate_id left join sso_user_accounts sua on u.aggregate_id = sua.aggregate_id where :role", nativeQuery = true)
    Page<UserAccount> findByRole(Predicate role, Pageable pageable);

    @Query(value = "SELECT u.aggregate_id, u.family_name, u.given_name, u.pua_id, u.primary_email, u2.user_role, sua.sso_type, SUBSTRING_INDEX(u2.user_role ,'.',1) as namespace, \n" +
            "SUBSTRING_INDEX(SUBSTRING_INDEX(u2.user_role, '.', 2), '.', -1) AS customerId,\n" +
            "\tSUBSTRING_INDEX(u2.user_role ,'.',-1) as roleId\n" +
            "FROM useraccount u join userroles_test u2 on u.aggregate_id = u2.aggregate_id left join sso_user_accounts sua on u.aggregate_id = sua.aggregate_id\n" +
            "where SUBSTRING_INDEX(u2.user_role ,'.',1) in :namespace% and SUBSTRING_INDEX(u2.user_role ,'.',-1) = :context% and SUBSTRING_INDEX(SUBSTRING_INDEX(u2.user_role, '.', 2), '.', -1) like %:roleId ", nativeQuery = true)
    Page<UserAccount> find(String namespace, String context, String roleId, Pageable paging);
}

