/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread;

import controller.ServerController;
import model.Kolac;
import model.Kupac;
import model.Poslasticar;
import model.Racun;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import transfer.KlijentskiZahtev;
import transfer.ServerskiOdgovor;
import operacije.StatusOdg;
import operacije.Operacije;

/**
 *
 * @author Mina
 */
public class ThreadClient extends Thread {

    private Socket socket;

    ThreadClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            while (!socket.isClosed()) {
                ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
                KlijentskiZahtev request = (KlijentskiZahtev) in.readObject();
                ServerskiOdgovor response = obradaZahteva(request);
                ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                out.writeObject(response);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

    private ServerskiOdgovor obradaZahteva(KlijentskiZahtev request) {
        ServerskiOdgovor response = new ServerskiOdgovor(null, null, StatusOdg.Success);
        try {
            switch (request.getOperacija()) {
                case Operacije.ADD_KUPAC:
                    ServerController.getInstance().addKupac((Kupac) request.getParametar());
                    break;
                case Operacije.ADD_RACUN:
                    ServerController.getInstance().addRacun((Racun) request.getParametar());
                    break;
                case Operacije.DELETE_KUPAC:
                    ServerController.getInstance().deleteKupac((Kupac) request.getParametar());
                    break;
                case Operacije.CANCEL_RACUN:
                    ServerController.getInstance().cancelRacun((Racun) request.getParametar());
                    break;   
                case Operacije.UPDATE_KUPAC:
                    ServerController.getInstance().updateKupac((Kupac) request.getParametar());
                    break;
                case Operacije.UPDATE_RACUN:
                    ServerController.getInstance().updateRacun((Racun) request.getParametar());
                    break;  
                case Operacije.GET_ALL_KUPAC:
                    response.setOdgovor(ServerController.getInstance().getAllKupac((Kupac) request.getParametar()));
                    break;
                case Operacije.GET_ALL_MESTO:
                    response.setOdgovor(ServerController.getInstance().getAllMesto());
                    break;
                case Operacije.GET_ALL_RACUN:
                    response.setOdgovor(ServerController.getInstance().getAllRacun((Racun) request.getParametar()));
                    break;
                case Operacije.GET_ALL_KOLAC:
                    response.setOdgovor(ServerController.getInstance().getAllKolac());
                    break;
                case Operacije.GET_ALL_POSLASTICAR:
                    response.setOdgovor(ServerController.getInstance().getAllPoslasticar());
                    break;    
                case Operacije.LOGIN:
                    Poslasticar poslasticar = (Poslasticar) request.getParametar();
                    Poslasticar p = ServerController.getInstance().login(poslasticar);
                    response.setOdgovor(p);
                    break;
                case Operacije.LOGOUT:
                    Poslasticar ulogovani = (Poslasticar) request.getParametar();
                    ServerController.getInstance().logout(ulogovani);
                    break;
                default:
                    return null;
            }
        } catch (Exception ex) {
            response.setStatusOdgovora(StatusOdg.Error);
            response.setException(ex);
        }
        return response;
    }

}
