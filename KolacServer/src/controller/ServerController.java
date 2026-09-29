/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import model.Kolac;
import model.Kupac;
import model.Mesto;
import model.Poslasticar;
import model.Racun;
import java.util.ArrayList;


import so.kolac.SOGetAllKolac;

import so.kupac.SOAddKupac;
import so.kupac.SODeleteKupac;
import so.kupac.SOGetAllKupac;
import so.kupac.SOUpdateKupac;
import so.login.SOLogin;
import so.mesto.SOGetAllMesto;
import so.poslasticar.SOGetAllPoslasticar;
import so.racun.SOAddRacun;
import so.racun.SOCancelRacun;
import so.racun.SOGetAllRacun;
import so.racun.SOUpdateRacun;


/**
 *
 * @author Mina
 */
public class ServerController {

    private static ServerController instance;
    private ArrayList<Poslasticar> ulogovaniPoslasticari = new ArrayList<>();

    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public ArrayList<Poslasticar> getUlogovaniPoslasticari() {
        return ulogovaniPoslasticari;
    }

    public void setUlogovaniPoslasticari(ArrayList<Poslasticar> ulogovaniPoslasticari) {
        this.ulogovaniPoslasticari = ulogovaniPoslasticari;
    }

    public Poslasticar login(Poslasticar poslasticar) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(poslasticar);
        return so.getUlogovani();
    }

    public void addKupac(Kupac kupac) throws Exception {
        (new SOAddKupac()).templateExecute(kupac);
    }

    public void addRacun(Racun racun) throws Exception {
        (new SOAddRacun()).templateExecute(racun);
    }

    

    public void deleteKupac(Kupac kupac) throws Exception {
        (new SODeleteKupac()).templateExecute(kupac);
    }

    public void cancelRacun(Racun racun) throws Exception {
        (new SOCancelRacun()).templateExecute(racun);
    }

    
    public void updateKupac(Kupac kupac) throws Exception {
        (new SOUpdateKupac()).templateExecute(kupac);
    }

    public void updateRacun(Racun racun) throws Exception {
        (new SOUpdateRacun()).templateExecute(racun);
    }

   

    public ArrayList<Kupac> getAllKupac(Kupac kriterijum) throws Exception {
    SOGetAllKupac so = new SOGetAllKupac();

    if (kriterijum == null) {
        kriterijum = new Kupac();
    }

    so.templateExecute(kriterijum);

    return so.getLista();
    }

    public ArrayList<Racun> getAllRacun(Kupac kupac) throws Exception {
        SOGetAllRacun so = new SOGetAllRacun();
        
        Racun r = new Racun();
        r.setKupac(kupac);
        
        so.templateExecute(r);
        return so.getLista();
    }
    public ArrayList<Racun> getAllRacun(Racun kriterijum) throws Exception {
        SOGetAllRacun so = new SOGetAllRacun();
        so.templateExecute(kriterijum);
        return so.getLista();
}

    public ArrayList<Mesto> getAllMesto() throws Exception {
        SOGetAllMesto so = new SOGetAllMesto();
        so.templateExecute(new Mesto());
        return so.getLista();
    }

    public ArrayList<Kolac> getAllKolac() throws Exception {
        SOGetAllKolac so = new SOGetAllKolac();
        so.templateExecute(new Kolac());
        return so.getLista();
    }
    public ArrayList<Poslasticar> getAllPoslasticar() throws Exception {
        SOGetAllPoslasticar so = new SOGetAllPoslasticar();
        so.templateExecute(new Poslasticar());
        return so.getLista();
}

    public void logout(Poslasticar ulogovani) {
        ulogovaniPoslasticari.remove(ulogovani);
    }

}
