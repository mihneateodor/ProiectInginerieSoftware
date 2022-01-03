package com.proiect.proiect;


import com.itextpdf.text.DocumentException;
import com.proiect.proiect.administrate.*;
import com.proiect.proiect.model.*;
import com.proiect.proiect.repositories.AeroportRepository;
import com.proiect.proiect.repositories.PersoanaRepository;
import com.proiect.proiect.repositories.ZborRepository;
import com.proiect.proiect.ticket.ComputeTicket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.sql.Time;
import com.proiect.proiect.repositories.PersoanaRepository;
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

    private CautareZborCreareBilet search;


    private Invoker invoker = new Invoker();
    private Operation operation = new Operation();
    private InsertCommand insertCommand = new InsertCommand(operation);
    private UpdateCommand updateCommand = new UpdateCommand(operation);
    private DeleteCommand deleteCommand = new DeleteCommand(operation);

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

    @RequestMapping(value="/make_ticket", method = RequestMethod.GET)
    @GetMapping("/make_ticket")
    public String Ticket(ZborItem zborItem, Date date1) throws DocumentException, IOException, URISyntaxException {
        if(date1==null)
            System.out.println("nimic");
        else
            System.out.println(date1.toString());
        Zbor zbor = new Zbor(3, 3, 4, Time.valueOf("15:00:00"), 30, 2, 100, "Frontier Airlines");
        Persoana persoana = new Persoana(5, "acmki", "anap@gmail.com", "parola", false);
        ComputeTicket.computeBill(new Bilet(zbor, 1, persoana, "30/12/2021", "Zalau", "Bucuresti"));
        return "index";
    }

    @GetMapping("/search")
    public String search() {
        return "search";
    }

    @RequestMapping(value="/choose", method = RequestMethod.GET)
    @GetMapping("/choose")
    public String choose(ZborItem zborItem, Date date) throws DocumentException, IOException, URISyntaxException {

        Date date1 = date;
        Ticket(zborItem, date1);
        //System.out.println(zborItem.toString());
        //System.out.println(date.toString());
        return "choose";
    }

    @GetMapping("choose2")
    public String choose2(@RequestParam String from, @RequestParam String to, @RequestParam Date date,  Model model) throws DocumentException, IOException, URISyntaxException {
        String mesaj;
        ZborItem zborItem= new ZborComposite();

        CautareZborCreareBilet search = new CautareZborCreareBilet(aeroportRepository);
        try {
            mesaj=search.findZborAStar(from, to).toString();
            zborItem = search.findZborAStar(from,to);

        } catch (Exception e) {
            mesaj="Could not find this route!";
        }
        model.addAttribute("mesaj", mesaj);
        if(mesaj.equals("Could not find this route!")) {
            mesaj="Could not find this route!";
            return "choosenot";
        }
        else {
            mesaj = "Date of the flight is on " + date.toString() + ". " + mesaj;
            model.addAttribute("mesaj", mesaj);
            model.addAttribute("zbor", zborItem);
            return choose(zborItem, date);
        }
    }

    @PostMapping("/choosenot")
    public String chooseNot(){
        return "choosenot";
    }


}
