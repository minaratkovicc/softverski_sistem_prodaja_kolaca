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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import session.Sesija;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import operacije.StatusOdg;
import operacije.Operacije;

/**
 *
 * @author Mina
 */
public class ClientController {

    private static ClientController instance;

    private ClientController() {
    }

    public static ClientController getInstance() {
        if (instance == null) {
            instance = new ClientController();
        }
        return instance;
    }

    public Poslasticar login(Poslasticar poslasticar) throws Exception {
        return (Poslasticar) sendRequest(Operacije.LOGIN, poslasticar);
    }

    public void logout(Poslasticar ulogovani) throws Exception {
        sendRequest(Operacije.LOGOUT, ulogovani);
    }

    public void addKupac(Kupac kupac) throws Exception {
        sendRequest(Operacije.ADD_KUPAC, kupac);
    }

    public void addRacun(Racun racun) throws Exception {
        sendRequest(Operacije.ADD_RACUN, racun);
    }

    

    public void deleteKupac(Kupac kupac) throws Exception {
        sendRequest(Operacije.DELETE_KUPAC, kupac);
    }

    public void cancelRacun(Racun racun) throws Exception {
        sendRequest(Operacije.CANCEL_RACUN, racun);
    }

   

    public void updateKupac(Kupac kupac) throws Exception {
        sendRequest(Operacije.UPDATE_KUPAC, kupac);
    }

    public void updateRacun(Racun racun) throws Exception {
        sendRequest(Operacije.UPDATE_RACUN, racun);
    }

    

    public ArrayList<Racun> getAllRacun(Kupac kupac) throws Exception {
        return (ArrayList<Racun>) sendRequest(Operacije.GET_ALL_RACUN, kupac);
    }
    public ArrayList<Racun> getAllRacun(Racun kriterijum) throws Exception {
        return (ArrayList<Racun>) sendRequest(Operacije.GET_ALL_RACUN, kriterijum);
    }

    public ArrayList<Kupac> getAllKupac() throws Exception {
    return getAllKupac(new Kupac());
    }

    public ArrayList<Kupac> getAllKupac(Kupac kriterijum) throws Exception {
    return (ArrayList<Kupac>) sendRequest(Operacije.GET_ALL_KUPAC, kriterijum);
    }

    public ArrayList<Mesto> getAllMesto() throws Exception {
        return (ArrayList<Mesto>) sendRequest(Operacije.GET_ALL_MESTO, null);
    }

    public ArrayList<Kolac> getAllKolac() throws Exception {
        return (ArrayList<Kolac>) sendRequest(Operacije.GET_ALL_KOLAC, null);
    }
    public ArrayList<Poslasticar> getAllPoslasticar() throws Exception {
        return (ArrayList<Poslasticar>) sendRequest(Operacije.GET_ALL_POSLASTICAR, null);
}

    
    
    private synchronized Object sendRequest(int operation, Object data) throws Exception {
        KlijentskiZahtev request = new KlijentskiZahtev(operation, data);

        ObjectOutputStream out = new ObjectOutputStream(Sesija.getInstance().getSocket().getOutputStream());
        out.writeObject(request);

        ObjectInputStream in = new ObjectInputStream(Sesija.getInstance().getSocket().getInputStream());
        ServerskiOdgovor response = (ServerskiOdgovor) in.readObject();

        if (response.getStatusOdgovora().equals(StatusOdg.Error)) {
            throw response.getException();
        } else {
            return response.getOdgovor();
        }

    }

}
