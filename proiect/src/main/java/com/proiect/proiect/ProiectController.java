package com.proiect.proiect;


import com.proiect.proiect.model.*;
import com.proiect.proiect.repositories.AeroportRepository;
import com.proiect.proiect.repositories.ZborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.proiect.proiect.model.PersoanaRepository;

import java.util.List;
import java.util.Optional;


@Controller
public class ProiectController {
    @Autowired
    private AeroportRepository aeroportRepository;

    @Autowired
    private ZborRepository zborRepository;

    @Autowired
    private PersoanaRepository persoanaRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<Aeroport> getAllAirports() {
        // This returns a JSON or XML with the users
        return aeroportRepository.findAll();
    }

    @GetMapping(path="/zboruri")
    public @ResponseBody Iterable<Zbor> getAllZbor(){
        return zborRepository.findAll();
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
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodenPassword = encoder.encode(persoana.getParolaPersoana());
        persoana.setParolaPersoana(encodenPassword);
        persoanaRepository.save(persoana);
        return "registration_success";
    }

    @GetMapping("/list_users")
    public String viewUserList(Model model){
        List<Persoana> persoanaList = (List<Persoana>) persoanaRepository.findAll();
        model.addAttribute("listUsers",persoanaList);
        return "users";
    }

    @GetMapping("/login")
    public String logInPage(){
        return "login";
    }

    @GetMapping("/composite_test")
    public @ResponseBody String test(){
        ZborComposite comp = new ZborComposite();
        for (int i=3;i<8;i++){
            Optional<Zbor> zbor = zborRepository.findById(i);
            comp.add(zbor.get());
        }
        System.out.println(comp.toString());
        return comp.toString();
    }
}
