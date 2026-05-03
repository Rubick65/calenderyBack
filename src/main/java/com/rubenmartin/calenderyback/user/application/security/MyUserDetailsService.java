package com.rubenmartin.calenderyback.user.application.security;

import com.rubenmartin.calenderyback.common.security.CustomUserSecurity;
import com.rubenmartin.calenderyback.rol.domain.entity.Rol;
import com.rubenmartin.calenderyback.user.domain.entity.User;
import com.rubenmartin.calenderyback.user.domain.port.UserRepositoryPort;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Transient;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
@Transient
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepositoryPort userRepositoryPort;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        User user = userRepositoryPort.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));

        return new CustomUserSecurity(
                user.getIdUsuario(),
                user.getEmail(),
                user.getKeypass(),
                user.isEnable(),
                getAuthorities(user.getRoles()));
    }

    private static List<GrantedAuthority> getAuthorities(List<Rol> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Rol role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRolName()));
        }
        return authorities;
    }
}

