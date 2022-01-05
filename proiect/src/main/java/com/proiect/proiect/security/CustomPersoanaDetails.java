package com.proiect.proiect.security;

import com.proiect.proiect.model.Persoana;
import com.proiect.proiect.model.Rol;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CustomPersoanaDetails implements UserDetails{

    private Persoana user;

    public CustomPersoanaDetails(Persoana user) {
        super();
        this.user = user;
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Rol> roles = user.getRol();
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        for (Rol rol: roles){
            authorities.add(new SimpleGrantedAuthority(rol.getName()));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getParolaPersoana();
    }

    @Override
    public String getUsername() {
        return user.getEmailPersoana();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getFullName() {
        return user.getNumePersoana();
    }

    public boolean hasRole(String roleName) {
        return this.user.hasRole(roleName);
    }

}
