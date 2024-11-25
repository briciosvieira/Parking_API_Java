package com.sousa.demo_parking_api.jwt;


import com.sousa.demo_parking_api.entity.User;
import com.sousa.demo_parking_api.enums.Role;
import com.sousa.demo_parking_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        return new JwtUserDetails(user);
    }

    public JwtToken getTokenAuthenticated(String username) {
        Role role = userService.findRoleByUsername(username);
        String roleName = role.name().substring("ROLE_".length());
        return JwtTokenUtils.createToken(username, roleName);
    }
}
