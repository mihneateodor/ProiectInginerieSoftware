package com.proiect.proiect;

import com.proiect.proiect.model.Persoana;
import com.proiect.proiect.model.Rol;
import com.proiect.proiect.repositories.PersoanaRepository;
import com.proiect.proiect.repositories.RolRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PersoanaRepository repo;

    @Test
    public void testCreateUser() {
        Persoana user = new Persoana();
        user.setEmailPersoana("Mihai@gmail.com");
        user.setParolaPersoana("Mihai2020");
        user.setNumePersoana("Cociubei");

        Persoana savedUser = repo.save(user);

        Persoana existUser = entityManager.find(Persoana.class, savedUser.getIdPersoana());

        assertThat(user.getEmailPersoana()).isEqualTo(existUser.getEmailPersoana());

    }

    @Test
    public void testFindUserByEmail(){

        String email= "Mihai@gmail.com";

        Persoana persoana=  repo.findByEmailPersoana(email);

        assertThat(persoana).isNotNull();

    }

    @Test
    public void testAddRoleToNewUser(){

        Persoana user = new Persoana();
        user.setEmailPersoana("ravikumar@gmail.com");
        user.setParolaPersoana("ravi2020");
        user.setNumePersoana("Ravi");

        Rol rolUser = rolRepository.findByName("User");
        user.addRole(rolUser);

        Persoana savedUser = repo.save(user);


        assertThat(savedUser.getRol().size()).isEqualTo(1);

    }

    @Test
    public void testAddRolesToExistingUser(){
        Persoana persoana = repo.findById(2).get();

        Rol rolUser = rolRepository.findByName("User");
        persoana.addRole(rolUser);

        Rol roleAdmin = new Rol(2);
        persoana.addRole(roleAdmin);

        Persoana savedUser = repo.save(persoana);

        assertThat(savedUser.getRol().size()).isEqualTo(2);

    }

}
