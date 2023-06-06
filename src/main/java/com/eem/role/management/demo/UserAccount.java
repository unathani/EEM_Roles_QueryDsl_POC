package com.eem.role.management.demo;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Set;


@Entity
@Table(name = "useraccount")
public class UserAccount implements Serializable {
    @Id
    @Column(name = "aggregate_id")
    private String aggregateId;
    @Column(name = "given_name")
    private String givenName;
    @Column(name = "family_name")
    private String familyName;

    @Column(name = "primary_email")
    private String primaryEmail;

    @Column(name = "pua_id")
    private Long puaId;

    @OneToMany
    @JoinColumn(name="aggregate_id")
    private Set<UserRolesTest> userRoles;

    @OneToMany
    @JoinColumn(name="aggregate_id", unique = true)
    private List<SsoUserAccount> ssoUserAccounts;

    public UserAccount() {

    }

    public UserAccount(String aggregateId, String givenName, String familyName) {
        this.aggregateId = aggregateId;
        this.givenName = givenName;
        this.familyName = familyName;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
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

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public Long getPuaId() {
        return puaId;
    }

    public void setPuaId(Long puaId) {
        this.puaId = puaId;
    }

    public Set<UserRolesTest> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRolesTest> userRoles) {
        this.userRoles = userRoles;
    }

    public List<SsoUserAccount>  getSsoUserAccounts() {
        return ssoUserAccounts;
    }

    public void setSsoUserAccounts(List<SsoUserAccount>  ssoUserAccounts) {
        this.ssoUserAccounts = ssoUserAccounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserAccount that = (UserAccount) o;
        return Objects.equals(getAggregateId(), that.getAggregateId()) && Objects.equals(getGivenName(), that.getGivenName()) && Objects.equals(getFamilyName(), that.getFamilyName()) && Objects.equals(getPrimaryEmail(), that.getPrimaryEmail()) && Objects.equals(getPuaId(), that.getPuaId()) && Objects.equals(getUserRoles(), that.getUserRoles()) && Objects.equals(getSsoUserAccounts(), that.getSsoUserAccounts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getAggregateId(), getGivenName(), getFamilyName(), getPrimaryEmail(), getPuaId(), getUserRoles(), getSsoUserAccounts());
    }
}
