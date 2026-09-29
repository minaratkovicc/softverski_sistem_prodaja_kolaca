/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import model.Poslasticar;
import forme.MainForm;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author Mina
 */
public class Sesija {

    private static Sesija instance;
    private Socket socket;
    private Poslasticar ulogovani;
    private MainForm mf;

    private Sesija() {
        try {
            socket = new Socket("localhost", 10000);
        } catch (IOException ex) {
        }
    }

    public static Sesija getInstance() {
        if (instance == null) {
            instance = new Sesija();
        }
        return instance;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setUlogovani(Poslasticar ulogovani) {
        this.ulogovani = ulogovani;
    }

    public Poslasticar getUlogovani() {
        return ulogovani;
    }

    public MainForm getMf() {
        return mf;
    }

    public void setMf(MainForm mf) {
        this.mf = mf;
    }

}
