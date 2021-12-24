package com.proiect.proiect;

import static org.assertj.core.api.Assertions.assertThat;

import com.proiect.proiect.model.Persoana;
import com.proiect.proiect.model.PersoanaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

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

}