package com.proiect.proiect.defaultPersoana;

import com.proiect.proiect.model.Persoana;
import com.proiect.proiect.model.Rol;
import com.proiect.proiect.repositories.PersoanaRepository;
import com.proiect.proiect.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersoanaService {

    @Autowired
    private PersoanaRepository persoanaRepository;

    @Autowired
    RolRepository roleRepo;

    public void registerDefaultUser(Persoana persoana) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPass = encoder.encode(persoana.getParolaPersoana());
        persoana.setParolaPersoana(encodedPass);
        Rol roleUser = roleRepo.findByName("User");
        persoana.addRole(roleUser);

        persoanaRepository.save(persoana);
    }

    public List<Persoana> listAll() {
        return (List<Persoana>) persoanaRepository.findAll();
    }

    public Persoana getPersoana(int id) {
        return persoanaRepository.findById(id).get();
    }

    public List<Rol> listRoles() {
        return roleRepo.findAll();
    }

    public void save(Persoana persoana){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedPass = encoder.encode(persoana.getParolaPersoana());
        persoana.setParolaPersoana(encodedPass);
        persoanaRepository.save(persoana);
    }



}
