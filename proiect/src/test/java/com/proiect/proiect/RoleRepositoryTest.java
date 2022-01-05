package com.proiect.proiect;

import com.proiect.proiect.model.Rol;
import com.proiect.proiect.repositories.RolRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class RoleRepositoryTest {

    @Autowired private RolRepository repo;

    @Test
    public void testCreateRoles() {
        Rol user = new Rol("User");
        Rol admin = new Rol("Admin");

        repo.saveAll(List.of(user, admin));

        List<Rol> listRoles = repo.findAll();

        assertThat(listRoles.size()).isEqualTo(2);
    }

}
