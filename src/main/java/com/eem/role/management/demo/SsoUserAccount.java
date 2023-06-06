package com.eem.role.management.demo;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "sso_user_accounts")
public class SsoUserAccount {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="aggregate_id")
    private String aggregateId;
    @Column(name="sso_type")
    private String ssoType;

    @Column(name="issuer")
    private String issuer;
    @Column(name="unique_identifier")
    private String uniqueIdentifier;

    public SsoUserAccount(){

    }

    public String getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String getSsoType() {
        return ssoType;
    }

    public void setSsoType(String ssoType) {
        this.ssoType = ssoType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getUniqueIdentifier() {
        return uniqueIdentifier;
    }

    public void setUniqueIdentifier(String uniqueIdentifier) {
        this.uniqueIdentifier = uniqueIdentifier;
    }
}
