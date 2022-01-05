package com.proiect.proiect.model;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@Entity
@Table(name="persoana")
public class Persoana {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPersoana;
    @Column(nullable = false, unique = true)
    private String numePersoana;
    @Column(nullable = false, unique = true)
    private String emailPersoana;
    @Column(nullable = false)
    private String parolaPersoana;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_rol",
            joinColumns = @JoinColumn(name = "persoana_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")

    )
    private Set<Rol> rol = new HashSet<>();

    public Persoana(){}

    public Persoana(int idPersoana, String numePersoana, String emailPersoana, String parolaPersoana) {
        this.idPersoana = idPersoana;
        this.numePersoana = numePersoana;
        this.emailPersoana = emailPersoana;
        this.parolaPersoana = parolaPersoana;
    }

    public int getIdPersoana(){
        return this.idPersoana;
    }
    public String getNumePersoana(){
        return this.numePersoana;
    }
    public String getEmailPersoana(){
        return this.emailPersoana;
    }
    public String getParolaPersoana(){
        return this.parolaPersoana;
    }
    public void setIdPersoana(int idPersoana) {
        this.idPersoana = idPersoana;
    }

    public void setNumePersoana(String numePersoana) {
        this.numePersoana = numePersoana;
    }

    public void setEmailPersoana(String emailPersoana) {
        this.emailPersoana = emailPersoana;
    }

    public void setParolaPersoana(String parolaPersoana) {
        this.parolaPersoana = parolaPersoana;
    }

    public String toString(){
        String persoana;
        persoana = "ID " + this.idPersoana + ": " + this.numePersoana + " - " + this.emailPersoana + " - " + this.parolaPersoana;
        return persoana;
    }

    public void addRole(Rol rol){
        this.rol.add(rol);
    }

    public Set<Rol> getRol() {
        return rol;
    }

    public void setRol(Set<Rol> rol) {
        this.rol = rol;
    }

    public String getRoStringl() {
        if(rol.isEmpty())
            return "[]";
        String rolS = "[";
        for (Rol s: rol){
            rolS += s.getName() + ", ";
        }
        rolS = rolS.substring(0, rolS.length() - 2);
        return rolS + "]";
    }

    public boolean hasRole(String roleName) {
        Iterator<Rol> iterator = this.rol.iterator();
        while (iterator.hasNext()) {
            Rol role = iterator.next();
            if (role.getName().equals(roleName)) {
                return true;
            }
        }

        return false;
    }
}
