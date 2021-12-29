package com.proiect.proiect;


import com.proiect.proiect.administrate.InsertCommand;
import com.proiect.proiect.administrate.Invoker;
import com.proiect.proiect.administrate.Operation;
import com.proiect.proiect.model.*;
import com.proiect.proiect.repositories.AeroportRepository;
import com.proiect.proiect.administrate.CautareZborCreareBilet;
import com.proiect.proiect.repositories.ZborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.proiect.proiect.repositories.PersoanaRepository;
import java.util.List;


@Controller
public class ProiectController {
    @Autowired
    private AeroportRepository aeroportRepository;

    @Autowired
    private ZborRepository zborRepository;

    @Autowired
    private PersoanaRepository persoanaRepository;

    private Invoker invoker = new Invoker();
    private Operation operation = new Operation();
    private InsertCommand insertCommand = new InsertCommand(operation);
    //private UpdateCommand updateCommand;
    //private DeleteCommand deleteCommand;

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

    @GetMapping("/test_search")
    public @ResponseBody String test() {

        CautareZborCreareBilet cautareZborBilet1 = new CautareZborCreareBilet(zborRepository,aeroportRepository,persoanaRepository);
        try {
            return cautareZborBilet1.findZborAStar("Romania", "Statele Unite").toString();
        }
        catch (Exception e){
            return "Could not find this route!";
        }
    }

    ///////////////////////////////////////principal admin
    @GetMapping("/main_adm")
    public String MainAdm(){
        return "main_adm";
    }

    ///////////////////////////////////////////aeroport admin
    @GetMapping("/airport_adm")
    public String AirportAdm(){
        return "adm_aeroport";
    }

    @GetMapping("/insert_airport")
    public String showInsertForm(Model model) {
        model.addAttribute("aeroport", new Aeroport());
        return "insert_form";
    }

    @PostMapping("/process_insert")
    public String processInsertAirport(Aeroport aeroport) {
        invoker.setCommand(insertCommand);
        invoker.executeCommand(aeroport, aeroportRepository);
        return "insert_success";
    }

    @GetMapping("/delete_airport")
    public String showDeleteForm(Model model) {
        Integer id = 0;
        model.addAttribute("Integer", id);
        return "delete_form";
    }

    /* @PostMapping("/process_delete")
     public String processDelete(Integer id) {
         System.out.println(id);
         invoker.setCommand(deleteCommand);
         invoker.executeCommand(id, aeroportRepository);
         return "insert_success";
     }*/
//////////////////////////////////////////zbor admin
    @GetMapping("/zbor_adm")
    public String ZborAdm(){
        return "adm_zbor";
    }

    @GetMapping("/insert_flight")
    public String showInsertFlightForm(Model model) {
        model.addAttribute("zbor", new Zbor());
        return "insert_zbor_form";
    }

    @PostMapping("/process_flight_insert")
    public String processInsertFlight(Zbor zbor) {
        invoker.setCommand(insertCommand);
        invoker.executeCommand(zbor, zborRepository);
        return "insert_success";
    }

    @GetMapping("/search")
    public String search(){
        return "search";
    }
}
