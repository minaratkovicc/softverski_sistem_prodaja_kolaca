/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import model.Kupac;
import model.Mesto;
import model.Racun;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import model.Poslasticar;

/**
 *
 * @author Mina  
 */
public class TableModelRacuni extends AbstractTableModel implements Runnable {

    private ArrayList<Racun> lista;
    private String[] kolone = {"ID", "Datum", "Status",
        "Storno od racuna", "Ukupan iznos", "Poslasticar", "Kupac"};
    private String parametar = "";
    private Kupac kupacFilter=null;
    private Poslasticar poslasticarFilter=null;
   

    public TableModelRacuni() {
        try {
            Racun kriterijum = new Racun();
            kriterijum.setKupac(kupacFilter);
            kriterijum.setPoslasticar(poslasticarFilter);
            lista = ClientController.getInstance().getAllRacun(kriterijum);
            if (lista == null) lista = new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            lista = new ArrayList<>();
        }
    }

    public TableModelRacuni(Kupac kupac) {
        try {
            this.kupacFilter = kupac;
            Racun kriterijum = new Racun();
            kriterijum.setKupac(kupacFilter);
            kriterijum.setPoslasticar(poslasticarFilter);
            lista = ClientController.getInstance().getAllRacun(kriterijum);
            if (lista == null) lista = new ArrayList<>();
        } catch (Exception ex) {
            ex.printStackTrace();
            lista = new ArrayList<>();
        }
    }
    public TableModelRacuni(ArrayList<Racun> lista) {
    this.lista = lista;
    if (this.lista == null) {
        this.lista = new ArrayList<>();
    }
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
        Racun r = lista.get(row);
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");

        switch (column) {
            case 0:
                return r.getRacunID();
            case 1:
                return sdf.format(r.getDatumVreme());
            case 2:
                return r.getStatus();
            case 3:
                 if (r.getStornoOdRacunaID() == 0) {
                    return null;
                }
                return r.getStornoOdRacunaID();
            case 4:
                return r.getUkupanIznos() + "din";
            case 5:
                return r.getPoslasticar();
            case 6:
                return r.getKupac();

            default:
                return null;
        }
    }

    public Racun getSelectedRacun(int row) {
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
            Logger.getLogger(TableModelRacuni.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void setFilter(String parametar, Kupac kupac, Poslasticar poslasticar) {
        this.parametar = parametar;
        this.kupacFilter = kupac;
        this.poslasticarFilter = poslasticar;
        refreshTable();
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }
    

    public void refreshTable() {
        try {
           Racun kriterijum = new Racun();
        kriterijum.setKupac(kupacFilter);
        kriterijum.setPoslasticar(poslasticarFilter);
        kriterijum.setParametar(parametar);
        lista = ClientController.getInstance().getAllRacun(kriterijum);
        if (lista == null) {
            lista = new ArrayList<>();
        }

        } catch (Exception ex) {
            ex.printStackTrace();
            lista = new ArrayList<>();
        }
    }

    public ArrayList<Racun> getLista() {
        return lista;
    }

    
}
