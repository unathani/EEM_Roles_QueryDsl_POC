package com.eem.role.management.demo;

import java.util.List;
import java.util.Set;

public class UserAccountRolesDto {
    private String aggregateId;

    private Set<String> adminRoles;

    private String givenName;

    private String familyName;

    private String accountType;

    public String getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public Set<String> getAdminRoles() {
        return adminRoles;
    }

    public void setAdminRoles(Set<String> adminRoles) {
        this.adminRoles = adminRoles;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
