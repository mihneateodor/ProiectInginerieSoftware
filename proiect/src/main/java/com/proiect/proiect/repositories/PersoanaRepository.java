package com.proiect.proiect.repositories;


import com.proiect.proiect.model.Persoana;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PersoanaRepository extends CrudRepository<Persoana, Integer> {

    @Query("SELECT u FROM Persoana u where u.emailPersoana = ?1")
    Persoana findByEmailPersoana(String email);

}
