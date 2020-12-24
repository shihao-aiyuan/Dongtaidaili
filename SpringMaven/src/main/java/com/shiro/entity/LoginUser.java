package com.shiro.entity;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

/**
 * @author shihao
 * @create 2020-09-01 17:36
 */
@Repository
public class LoginUser {

    private Set<String> roles = new HashSet();

    private Set<String> permissions = new HashSet();


    public LoginUser() {
    }

    public Set<String> getRoles() {
        return this.roles;
    }

    public Set<String> getPermissions() {
        return this.permissions;
    }


    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

}
