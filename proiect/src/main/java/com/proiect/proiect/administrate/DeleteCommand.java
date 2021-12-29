package com.proiect.proiect.administrate;

import com.proiect.proiect.model.Aeroport;
import com.proiect.proiect.model.Zbor;
import com.proiect.proiect.repositories.AeroportRepository;
import com.proiect.proiect.repositories.ZborRepository;

public class DeleteCommand implements Command{

    private Operation operation;

    public DeleteCommand(Operation operation) {
        this.operation = operation;
    }

    @Override
    public void execute(Aeroport aeroport, AeroportRepository repository) {
        operation.delete(aeroport.getIdAeroport(), repository);
    }

    @Override
    public void execute(Zbor zbor, ZborRepository repository){
        operation.delete(zbor.getIdZbor(), repository);
    }
}
