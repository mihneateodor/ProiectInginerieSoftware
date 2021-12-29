package com.proiect.proiect.administrate;

import com.proiect.proiect.model.Aeroport;
import com.proiect.proiect.model.Zbor;
import com.proiect.proiect.repositories.AeroportRepository;
import com.proiect.proiect.repositories.ZborRepository;

public class Operation {

    public void insert(Aeroport aeroport, AeroportRepository repository) {
        repository.save(aeroport);
    }

    public void insert(Zbor zbor, ZborRepository repository) {
        repository.save(zbor);
    }

    public void delete(Integer id, AeroportRepository repository) {
        repository.deleteById(id);
    }

    public void delete(Integer id, ZborRepository repository) {
        repository.deleteById(id);
    }
}
