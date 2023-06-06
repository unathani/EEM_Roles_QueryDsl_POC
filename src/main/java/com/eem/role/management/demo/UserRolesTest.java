package com.eem.role.management.demo;

import jakarta.persistence.*;
import org.springframework.util.StringUtils;

import java.io.Serializable;

@Entity
@Table(name = "userroles_test")
public class UserRolesTest implements Serializable {

    @EmbeddedId
    private UserRoleKey adminRoles;

    private String namespace;

    private String context;

    @Column(name = "roleid")
    private String roleId;



    public UserRolesTest(){

    }
//    public UserRolesTest(UserRoleKey userRoleId){
//        this.adminRoles = userRoleId;
//    }



    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public UserRolesTest(UserRoleKey userRoleId){
        this.adminRoles = userRoleId;
    }


    public UserRoleKey getAdminRoles() {
        return adminRoles;
    }

    public void setAdminRoles(UserRoleKey userRoleId) {
        this.adminRoles = userRoleId;
    }
}
