package com.proiect.proiect.security;

import com.proiect.proiect.model.Persoana;
import com.proiect.proiect.repositories.PersoanaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomPersoanaDetailsService implements UserDetailsService {

    @Autowired
    private PersoanaRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Persoana persoana = repo.findByEmailPersoana(email);
        if (persoana == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomPersoanaDetails(persoana);
    }
}
