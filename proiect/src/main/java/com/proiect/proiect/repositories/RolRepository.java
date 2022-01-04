package com.proiect.proiect.repositories;

import com.proiect.proiect.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RolRepository extends JpaRepository<Rol, Integer> {

    @Query("select r from Rol r where r.name = ?1")
    public Rol findByName(String name);


}
