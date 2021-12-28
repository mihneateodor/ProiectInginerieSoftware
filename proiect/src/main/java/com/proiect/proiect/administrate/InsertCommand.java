package com.proiect.proiect.administrate;

import com.proiect.proiect.model.Aeroport;
import com.proiect.proiect.model.Zbor;
import com.proiect.proiect.repositories.AeroportRepository;
import com.proiect.proiect.repositories.ZborRepository;

public class InsertCommand implements Command {

    private Operation operation;

    public InsertCommand(Operation operation) {
        this.operation = operation;
    }

    @Override
    public void execute(Aeroport aeroport, AeroportRepository repository) {
        operation.insert(aeroport, repository);
    }

    public void execute(Zbor zbor, ZborRepository repository){
        operation.insert(zbor, repository);
    }




}
