package com.eem.role.management.demo;

import jakarta.persistence.*;
import org.springframework.util.StringUtils;

import java.io.Serializable;

@Entity
@Table(name = "userroles")
public class UserRoles implements Serializable {

    @EmbeddedId
    private UserRoleKey adminRoles;

    @Transient
    private String namespace = "";

    @Transient
    private String context = "";

    @Transient
    private String roleId = "";



    public UserRoles(){

    }
    public UserRoles(UserRoleKey userRoleId){
        this.adminRoles = userRoleId;
    }


    public UserRoleKey getAdminRoles() {
        return adminRoles;
    }

    public void setAdminRoles(UserRoleKey userRoleId) {
        this.adminRoles = userRoleId;
    }

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



    @PostLoad
    private void postLoadFunction(){
        if (adminRoles != null){
            System.out.println(adminRoles.getUserRole());
            String[] tokens = StringUtils.split(adminRoles.getUserRole(), ".");
            if(tokens != null && tokens.length == 3){
                    namespace = tokens[0];
                    context = tokens[1];
                    roleId = tokens[2];
            } else if (tokens != null && tokens.length == 2){
                context = tokens[0];
                roleId = tokens[1];
            } else {
                roleId = tokens == null ? adminRoles.getUserRole() : tokens[0];
            }
        }
    }
}
