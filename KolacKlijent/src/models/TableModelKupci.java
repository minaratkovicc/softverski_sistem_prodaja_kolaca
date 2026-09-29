/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import model.Kupac;
import model.Mesto;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mina
 */
public class TableModelKupci extends AbstractTableModel implements Runnable {

    private ArrayList<Kupac> lista;
    private String[] kolone = {"ID", "Ime", "Prezime", "Email", "Telefon", "Mesto"};
    private String paramImePrezime = "";
    private String paramEmail = "";
    private Mesto mestoFilter = null;

    public TableModelKupci() {
        lista = new ArrayList<>();
        refreshTable();
    }
    public TableModelKupci(ArrayList<Kupac> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int i) {
        return kolone[i];
    }

    @Override
    public Object getValueAt(int row, int column) {
        Kupac k = lista.get(row);

        switch (column) {
            case 0:
                return k.getKupacID();
            case 1:
                return k.getIme();
            case 2:
                return k.getPrezime();
            case 3:
                return k.getEmail();
            case 4:
                return k.getTelefon();
            case 5:
                return k.getMesto();

            default:
                return null;
        }
    }

    public Kupac getSelectedKupac(int row) {
        return lista.get(row);
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Thread.sleep(10000);
                refreshTable();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(TableModelKupci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

  

    public void setFilter(String paramImePrezime, String paramEmail, Mesto mesto) {
        this.paramImePrezime = paramImePrezime;
        this.paramEmail = paramEmail;
        this.mestoFilter=mesto;
        refreshTable();
    }

    public void refreshTable() {
        try {
            Kupac kriterijum = new Kupac();

            kriterijum.setIme(paramImePrezime);
            kriterijum.setEmail(paramEmail);
            kriterijum.setMesto(mestoFilter);

            lista = ClientController.getInstance().getAllKupac(kriterijum);

            fireTableDataChanged();
        } catch (Exception ex) {
            Logger.getLogger(TableModelKupci.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Kupac> getLista() {
        return lista;
    }

}
