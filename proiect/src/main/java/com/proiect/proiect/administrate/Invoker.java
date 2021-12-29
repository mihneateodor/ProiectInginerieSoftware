package com.proiect.proiect.administrate;

import com.proiect.proiect.model.Aeroport;
import com.proiect.proiect.model.Zbor;
import com.proiect.proiect.repositories.AeroportRepository;
import com.proiect.proiect.repositories.ZborRepository;

public class Invoker {

    private Command command;

    public void setCommand(Command command)
    {
        this.command = command;
    }

    public void executeCommand(Aeroport aeroport, AeroportRepository repository) {
        command.execute(aeroport, repository);
    }

    public void executeCommand(Zbor zbor, ZborRepository repository) {
        command.execute(zbor, repository);
    }



}
