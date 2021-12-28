package com.proiect.proiect.security;

import com.proiect.proiect.model.Persoana;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class CustomPersoanaDetails implements UserDetails{

    private Persoana user;

    public CustomPersoanaDetails(Persoana user) {
        super();
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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

}
