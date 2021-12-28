package com.proiect.proiect.model;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface PersoanaRepository extends CrudRepository<Persoana, Integer> {

    @Query("SELECT u FROM Persoana u where u.emailPersoana = ?1")
    Persoana findByEmailPersoana(String email);

}
