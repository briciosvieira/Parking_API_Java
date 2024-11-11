package com.sousa.demo_parking_api.jwt;


import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

public class JwtUserDetails extends User {

    private com.sousa.demo_parking_api.entity.User users;

    public JwtUserDetails(com.sousa.demo_parking_api.entity.User user) {
        super(user.getUsername(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getRole().name()));
    }

    public Long id(){
        return this.users.getId();
    }

    public String role(){
        return this.users.getRole().name();
    }
}
