package com.proiect.proiect.administrate;

import com.proiect.proiect.connection.ConnectionSql;
import com.proiect.proiect.model.*;
import com.proiect.proiect.repositories.AeroportRepository;
import com.proiect.proiect.repositories.PersoanaRepository;
import com.proiect.proiect.repositories.ZborRepository;
import org.javatuples.Triplet;

import java.sql.*;
import java.sql.Date;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CautareZborCreareBilet {
    private AeroportRepository aeroportRepository;

    protected static final Logger LOGGER = Logger.getLogger(CautareZborCreareBilet.class.getName());

    private static final String findAeroportByTara = "SELECT * FROM aeroport WHERE tara_aeroport LIKE ?";
    private static final String findZborByIdAeroportPlecareString = "SELECT * FROM zbor WHERE id_aeroport_plecare = ?";
    private static final String findAeroportById = "SELECT * FROM aeroport WHERE id_aeroport = ?";

    public CautareZborCreareBilet(AeroportRepository aeroportRepository){
        this.aeroportRepository=aeroportRepository;

    }

    public CautareZborCreareBilet(){}

    public List<ZborItem> findZborIdAeroportPlecare(int idAeroportPlecare){
        List<ZborItem> toReturn = new ArrayList<>();
        Connection dbConnection = ConnectionSql.getConnection();
        PreparedStatement findZborByIdAeroportPlecare = null;
        ResultSet resultSet = null;
        try{
            findZborByIdAeroportPlecare = dbConnection.prepareStatement(findZborByIdAeroportPlecareString);
            findZborByIdAeroportPlecare.setString(1, String.valueOf(idAeroportPlecare));
            resultSet = findZborByIdAeroportPlecare.executeQuery();
            while(resultSet.next()){
                int idZbor= resultSet.getInt("id_zbor");
                String companie = resultSet.getString("companie");
                int durataMin = resultSet.getInt("durata_min");
                int durataOre = resultSet.getInt("durata_ore");
                int idAeroportSosire = resultSet.getInt("id_aeroport_sosire");
                Time oraPlecare = resultSet.getTime("ora_plecare");
                int pret = resultSet.getInt("pret");
                toReturn.add(new Zbor(idZbor,idAeroportPlecare,idAeroportSosire,oraPlecare,durataMin,durataOre,pret,companie));
            }
        }
        catch(SQLException e){
            LOGGER.log(Level.WARNING,"Cautare zbor " + e.getMessage());
        }
        finally{
            ConnectionSql.close(resultSet);
            ConnectionSql.close(findZborByIdAeroportPlecare);
            ConnectionSql.close(dbConnection);
        }
        return toReturn;
    }

    public static Aeroport findAeroportById(int id){
        Aeroport toReturn = null;
        Connection dbConnection = ConnectionSql.getConnection();
        PreparedStatement findAeroportByIdStatement = null;
        ResultSet resultSet = null;
        try{
            findAeroportByIdStatement = dbConnection.prepareStatement(findAeroportById);
            findAeroportByIdStatement.setString(1,String.valueOf(id));
            resultSet = findAeroportByIdStatement.executeQuery();
            resultSet.next();
            String numeAeroport = resultSet.getString("nume_aeroport");
            String orasAeroport = resultSet.getString("oras_aeroport");
            String taraAeroport = resultSet.getString("tara_aeroport");
            toReturn = new Aeroport(id,numeAeroport,orasAeroport,taraAeroport);
        }
        catch(SQLException e){
            LOGGER.log(Level.WARNING,"Cautare zbor " + e.getMessage());
        }
        finally{
            ConnectionSql.close(resultSet);
            ConnectionSql.close(findAeroportByIdStatement);
            ConnectionSql.close(dbConnection);
        }
        return toReturn;
    }


    public List<Aeroport> findAeroportByTara(String tara){
        List<Aeroport> toReturn = new ArrayList<>();
        Connection dbConnection = ConnectionSql.getConnection();
        PreparedStatement findTaraStatement = null;
        ResultSet resultSet = null;
        try{
            findTaraStatement = dbConnection.prepareStatement(findAeroportByTara);
            findTaraStatement.setString(1, "%"+tara+"%");
            resultSet = findTaraStatement.executeQuery();
            while(resultSet.next()) {
                int idAeroport = resultSet.getInt("id_aeroport");
                String numeAeroport = resultSet.getString("nume_aeroport");
                String orasAeroport = resultSet.getString("oras_aeroport");
                String taraAeroport = resultSet.getString("tara_aeroport");
                toReturn.add(new Aeroport(idAeroport,numeAeroport,orasAeroport,taraAeroport));
            }
        }
        catch(SQLException e){
            LOGGER.log(Level.WARNING,"Cautare aeroport " + e.getMessage());
        }
        finally{
            ConnectionSql.close(resultSet);
            ConnectionSql.close(findTaraStatement);
            ConnectionSql.close(dbConnection);
        }
        return toReturn;
    }

    public ZborItem findZborAStar(String tara1, String tara2) throws Exception {
        List<Aeroport> listaAeroportPlecare = this.findAeroportByTara(tara1);
        if(listaAeroportPlecare.isEmpty()) {
            //return "Nu exista zbor cu plecare din aceasta locatie.";
            throw new Exception();
        }
        List<Aeroport> listaAeroportSosire = this.findAeroportByTara(tara2);
        if(listaAeroportSosire.isEmpty()) {
            //return "Nu exista zbor cu sosire in aceasta locatie.";
            throw new Exception();
        }
        for( Aeroport aeroportPlecare : listaAeroportPlecare){
            for( Aeroport aeroportSosire : listaAeroportSosire ){
                PriorityQueue<Triplet<Aeroport,ZborComposite,Integer>> queue = new PriorityQueue<>();
                Set<Integer> visited =new HashSet<>();
                Integer pret = 0;
                Aeroport current ;
                ZborComposite actions = new ZborComposite();
                queue.add(Triplet.with(aeroportPlecare,actions,pret));
                while(!(queue.isEmpty())){
                    Triplet<Aeroport,ZborComposite,Integer> currentTriplet = queue.poll();
                    current = currentTriplet.getValue0();
                    actions = currentTriplet.getValue1();
                    pret = currentTriplet.getValue2();
                    if(!(visited.contains(current.getIdAeroport()))){
                        visited.add(current.getIdAeroport());
                        if(current.getIdAeroport()==aeroportSosire.getIdAeroport()){
                            //String mesaj;
                            return actions;
                        }
                        List<ZborItem> expanded = this.findZborIdAeroportPlecare(current.getIdAeroport());
                        for(ZborItem nextZbor : expanded){
                            ZborComposite newAction = (ZborComposite) actions.clone();
                            newAction.add(nextZbor);
                            int newPret = pret + nextZbor.getPret();
                            Optional<Aeroport> nextAeroportOptional=aeroportRepository.findById(nextZbor.getIdAeroportSosire());
                            Aeroport nextAeroport = nextAeroportOptional.get();
                            queue.add(Triplet.with(nextAeroport,newAction,newPret));
                        }
                    }
                }
            }
        }
        return null;
    }

    public Bilet createTicket(ZborItem zbor, int nrPasageri, String data, Persoana persoana, String from, String to){
        if( nrPasageri > 1)
            zbor.setPret( zbor.getPret() * nrPasageri);
        return new Bilet(zbor, nrPasageri, persoana, data, from, to);
    }

}
