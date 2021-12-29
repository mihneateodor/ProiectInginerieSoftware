package com.proiect.proiect;


import com.proiect.proiect.administrate.*;
import com.proiect.proiect.model.Aeroport;
import com.proiect.proiect.model.Persoana;
import com.proiect.proiect.model.Zbor;
import com.proiect.proiect.repositories.AeroportRepository;
import com.proiect.proiect.repositories.PersoanaRepository;
import com.proiect.proiect.repositories.ZborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
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
    private UpdateCommand updateCommand = new UpdateCommand(operation);
    private DeleteCommand deleteCommand = new DeleteCommand(operation);

    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable<Aeroport> getAllAirports() {
        // This returns a JSON or XML with the users
        return aeroportRepository.findAll();
    }

    @GetMapping(path = "/zboruri")
    public @ResponseBody
    Iterable<Zbor> getAllZbor() {
        return zborRepository.findAll();
    }

    @GetMapping("")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/register")
    public String showSingUpForm(Model model) {
        model.addAttribute("persoana", new Persoana());
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegistration(Persoana persoana) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodenPassword = encoder.encode(persoana.getParolaPersoana());
        persoana.setParolaPersoana(encodenPassword);
        persoanaRepository.save(persoana);
        return "registration_success";
    }

    @GetMapping("/list_users")
    public String viewUserList(Model model) {
        List<Persoana> persoanaList = (List<Persoana>) persoanaRepository.findAll();
        model.addAttribute("listUsers", persoanaList);
        return "users";
    }

    @GetMapping("/login")
    public String logInPage() {
        return "login";
    }

    @GetMapping("/test_search")
    public @ResponseBody
    String test() {

        CautareZborCreareBilet cautareZborBilet1 = new CautareZborCreareBilet(zborRepository, aeroportRepository, persoanaRepository);
        try {
            return cautareZborBilet1.findZborAStar("Romania", "Statele Unite").toString();
        } catch (Exception e) {
            return "Could not find this route!";
        }
    }

    ///////////////////////////////////////principal admin
    @GetMapping("/main_adm")
    public String MainAdm() {
        return "main_adm";
    }

    ///////////////////////////////////////////aeroport admin
    @GetMapping("/airport_adm")
    public String AirportAdm() {
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
        return "operation_success";
    }

    @GetMapping("/delete_airport")
    public String showDeleteForm(Model model) {
        Integer id = 0;
        model.addAttribute("id", id);
        return "delete_form";
    }

    @RequestMapping(value = "/process_delete", method = RequestMethod.GET)
    @PostMapping("/process_delete")
    public String processDelete(Integer id) {
        Aeroport aeroport = null;
        invoker.setCommand(deleteCommand);
        if (aeroportRepository.findById(id).isPresent()) {
            aeroport = aeroportRepository.findById(id).get();
            invoker.executeCommand(aeroport, aeroportRepository);
        } else
            return "operation_failed";
        return "operation_success";
    }

    @GetMapping("/update_airport")
    public String showUpdateForm(Model model) {
        Integer id = 0;
        String nume = null, oras = null, tara = null;
        model.addAttribute("id", id);
        model.addAttribute("nume", nume);
        model.addAttribute("oras", oras);
        model.addAttribute("tara", tara);
        return "update_form";
    }

    @RequestMapping(value = "/process_update", method = RequestMethod.GET)
    @PostMapping("/process_update")
    public String processUpdate(Integer id, String nume, String oras, String tara) {
        Aeroport aeroport = null;
        invoker.setCommand(updateCommand);

        if (aeroportRepository.findById(id).isPresent())
            aeroport = aeroportRepository.findById(id).get();
        else
            return "operation_failed";

        if (nume != null && !nume.isEmpty())
            aeroport.setNumeAeroport(nume);
        if (oras != null && !oras.isEmpty())
            aeroport.setOrasAeroport(oras);
        if (tara != null && !tara.isEmpty())
            aeroport.setTaraAeroport(tara);

        invoker.executeCommand(aeroport, aeroportRepository);
        return "operation_success";
    }


    //////////////////////////////////////////zbor admin
    @GetMapping("/zbor_adm")
    public String ZborAdm() {
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
        return "operation_success";
    }

    @GetMapping("/delete_flight")
    public String showDeleteFlightForm(Model model) {
        Integer id = 0;
        model.addAttribute("id", id);
        return "delete_flight_form";
    }

    @RequestMapping(value = "/process_flight_delete", method = RequestMethod.GET)
    @PostMapping("/process_flight_delete")
    public String processFlightDelete(Integer id) {
        Zbor zbor = null;
        invoker.setCommand(deleteCommand);
        if (zborRepository.findById(id).isPresent()) {
            zbor = zborRepository.findById(id).get();
            invoker.executeCommand(zbor, zborRepository);
        } else
            return "operation_failed";
        return "operation_success";
    }

    @GetMapping("/update_flight")
    public String showUpdateFlightForm(Model model) {
        Integer id = 0;
        model.addAttribute("id", id);
        model.addAttribute("ora_plecare", null);
        model.addAttribute("ore", null);
        model.addAttribute("minute", null);
        model.addAttribute("pret", null);
        model.addAttribute("companie", null);

        return "update_flight_form";
    }

    @RequestMapping(value = "/process_update_flight", method = RequestMethod.GET)
    @PostMapping("/process_update")
    public String processFlightUpdate(Integer id, String ora_plecare, Integer ore, Integer minute,
                                      Integer pret, String companie) {
        Zbor zbor = null;
        invoker.setCommand(updateCommand);

        if (zborRepository.findById(id).isPresent())
            zbor = zborRepository.findById(id).get();
        else
            return "operation_failed";

        if (ora_plecare != null && !ora_plecare.isEmpty())
            zbor.setOraPlecare(Time.valueOf(ora_plecare));
        if (ore != null)
            zbor.setDurataOre(ore);
        if (minute != null)
            zbor.setDurataMin(minute);
        if (pret != null)
            zbor.setPret(pret);
        if (companie != null && !companie.isEmpty())
            zbor.setCompanie(companie);

        invoker.executeCommand(zbor, zborRepository);
        return "operation_success";
    }

    @GetMapping("/list_airports")
    public String viewAirportsList(Model model) {
        List<Aeroport> aeroportList = (List<Aeroport>) aeroportRepository.findAll();
        model.addAttribute("listAirports", aeroportList);
        return "airports";
    }

    @GetMapping("/list_flights")
    public String viewFlightsList(Model model) {
        List<Zbor> zborList = (List<Zbor>) zborRepository.findAll();
        model.addAttribute("listFlights", zborList);
        return "flights";
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }
}
