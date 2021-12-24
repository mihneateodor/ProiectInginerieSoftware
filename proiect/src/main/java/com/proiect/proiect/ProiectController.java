package com.proiect.proiect;

import com.proiect.proiect.model.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class ProiectController {
    @Autowired
    private AeroportRepository aeroportRepository;

    @Autowired
    private PersoanaRepository persoanaRepository;

    @Autowired
    private ZborRepository zborRepository;

    @GetMapping(path="/airports")
    public @ResponseBody Iterable<Aeroport> getAllAirports() {
        return aeroportRepository.findAll();
    }

    @GetMapping(path="/persons")
    public @ResponseBody Iterable<Persoana> getAllPersons() {
        return persoanaRepository.findAll();
    }

    @GetMapping(path="/flights")
    public @ResponseBody Iterable<Zbor> getAllFlights() {
        return zborRepository.findAll();
    }
}
