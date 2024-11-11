package com.sousa.demo_parking_api.jwt;


import com.sousa.demo_parking_api.entity.User;
import com.sousa.demo_parking_api.enums.Role;
import com.sousa.demo_parking_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        return new JwtUserDetails(user);
    }

    public JwtToken getTokenAuthenticated(String username){
      Role role = userService.findRoleByUsername(username);
      return JwtUtils.createToken(username, role.name().substring("ROLE_".length()));

    }
}
