package com.proiect.proiect.administrate;

import com.proiect.proiect.model.Aeroport;
import com.proiect.proiect.model.Zbor;
import com.proiect.proiect.repositories.AeroportRepository;
import com.proiect.proiect.repositories.ZborRepository;

public interface Command {

    //for insert and delete airports
    default void execute(Aeroport aeroport, AeroportRepository repository) {
    }

    //for insert and delete flights
    default void execute(Zbor zbor, ZborRepository repository) {
    }

}
