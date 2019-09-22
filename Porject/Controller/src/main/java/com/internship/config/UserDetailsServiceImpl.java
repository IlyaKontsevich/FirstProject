package com.internship.config;

import com.internship.model.User;
import com.internship.model.UserRoleEnum;
import com.internship.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userService.getByEmail(email);
        System.out.println(user.toString());
        Set<GrantedAuthority> roles = new HashSet();
        if(user.getName().equals("Admin"))
            roles.add(new SimpleGrantedAuthority(UserRoleEnum.ADMIN.name()));
        else
            roles.add(new SimpleGrantedAuthority(UserRoleEnum.USER.name()));
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getEmail(),
                        user.getPassword(),
                        roles);

        return userDetails;
    }

}
