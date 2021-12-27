package com.proiect.proiect;


import com.proiect.proiect.model.Aeroport;
import com.proiect.proiect.repositories.AeroportRepository;
import com.proiect.proiect.model.Persoana;
import com.proiect.proiect.repositories.PersoanaRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class ProiectController {
    @Autowired
    private AeroportRepository aeroportRepository;

    @Autowired
    private PersoanaRepository persoanaRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Aeroport> getAllAirports() {
        // This returns a JSON or XML with the users
        return aeroportRepository.findAll();
    }

    @GetMapping("")
    public String viewHomePage(){
        return "index";
    }

    @GetMapping("/register")
    public String showSingUpForm(Model model){
        model.addAttribute("persoana",new Persoana());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(Persoana persoana){
        persoanaRepository.save(persoana);
        return "registration_success";
    }
}
