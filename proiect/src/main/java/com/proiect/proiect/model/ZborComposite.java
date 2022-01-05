package com.proiect.proiect.model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class ZborComposite extends ZborItem implements Comparable<ZborComposite>, Cloneable{

    private List<ZborItem> lista;

    private int durataOre;
    private int durataMin;
    private int pret;
    private int oreAdunate;
    private Time oraPlecare;

    public ZborComposite (){
        this.lista=new ArrayList<>();
    }



    public void add(ZborItem item){
        lista.add(item);
        this.setDurataMin();
        this.setDurataOre();
        this.setOraPlecare();
        this.getPret();
    }
    public void remove(ZborItem item){
        lista.remove(item);
        this.setDurataMin();
        this.setDurataOre();
        this.setOraPlecare();
        this.getPret();
    }

    public int getIdAeroportSosire(){
        int index=this.lista.size()-1;
        return this.lista.get(index).getIdAeroportSosire();
    }
    public int getIdAeroportPlecare(){
        return this.lista.get(0).getIdAeroportPlecare();
    }

    public ZborItem getZborItem (int index){
        return lista.get(index);
    }
    public ZborItem getLastZborItem (){
        return lista.get(lista.size()-1);
    }
    public void removeLastZborItem () {
        this.lista.remove(lista.get(lista.size()-1));
    }
    public int getIdZbor(){
        return this.lista.get(0).getIdZbor();
    }

    public void setDurataMin(){
        int minute = 0;
        for (ZborItem item : lista){
            minute += item.getDurataMin();
        }
        while(minute>=60){
            this.oreAdunate++;
            minute-=60;
        }
        this.durataMin=minute;
    }

    public int getDurataMin(){
        return this.durataMin;
    }

    public void setDurataOre(){
        for(ZborItem item : lista){
            this.durataOre+=item.getDurataOre();
        }
        this.durataOre+=this.oreAdunate;
    }

    public int getDurataOre(){
        return this.durataOre;
    }

    public void setOraPlecare(){
        this.oraPlecare= this.lista.get(0).getOraPlecare();
    }

    public Time getOraPlecare(){
        return this.oraPlecare;
    }

    public Time getOraPlecareComp(){
        return this.oraPlecare;
    }

    public void setPret(int pret){
        this.pret=pret;
    }

    public int getPret(){
        int pretCompus=0;
        for(ZborItem  item : lista){
            pretCompus += item.getPret();
        }
        this.pret=pretCompus;
        return pretCompus;
    }

    public String toString(){
        String nrEscale;
        if(lista.size()-1 == 0)
            nrEscale="does not have any layovers ";
        else if(lista.size()-1 == 1)
            nrEscale="does have one layover ";
        else
            nrEscale="does have " + (lista.size()-1) + " layovers";
        String mesaj = "The route "+ nrEscale +", it takes "+ this.durataOre +
                " hours and " + this.durataMin + " minutes. Departure at " + this.oraPlecare + ", at the price of " + this.pret + " EURO " +
                //"si este format din urmatoarele zboruri.\n " ;
                "and contains the next flights.\n";
        for(ZborItem item : lista){
            mesaj = mesaj + item.toString() + "\n";
        }
        return mesaj;
    }

    @Override
    public int compareTo(ZborComposite o) {
        Integer len1 = this.lista.size();
        Integer len2 = o.lista.size();
        return len1.compareTo(len2);
    }

    @Override
    public Object clone() {
        ZborComposite zborComposite= new ZborComposite();
        zborComposite.lista = new ArrayList<>(this.lista);
        zborComposite.durataOre = this.durataOre;
        zborComposite.durataMin = this.durataMin;
        zborComposite.pret = this.pret;
        return zborComposite;
    }
}
