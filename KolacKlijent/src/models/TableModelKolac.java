/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import controller.ClientController;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import model.Kolac;

/**
 *
 * @author Mina
 */
public class TableModelKolac extends AbstractTableModel implements Runnable {

    private ArrayList<Kolac> lista;
    
    
    private String[] kolone = {"ID", "Naziv", "Opis", "Cena", "Gluten"};
    private String parametar = "";

    public TableModelKolac(){
    try {
            lista = ClientController.getInstance().getAllKolac();
            refreshTable();
        } catch (Exception ex) {
            Logger.getLogger(TableModelKolac.class.getName()).log(Level.SEVERE, null, ex);
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
        Kolac k = lista.get(row);

        switch (column) {
            case 0:
                return k.getKolacID();
            case 1:
                return k.getNaziv();
            case 2:
                return k.getOpis();
            case 3:
                return String.format("%.2f", k.getCena());
            case 4:
                return k.getGluten();

            default:
                return null;
        }
    }

    public Kolac getSelectedKolac(int row) {
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
            Logger.getLogger(TableModelKolac.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
        refreshTable();
    }

    public void refreshTable() {
        try {
            lista = ClientController.getInstance().getAllKolac();
            if (!parametar.equals("")) {
                ArrayList<Kolac> novaLista = new ArrayList<>();
                for (Kolac k : lista) {
                    if (k.getNaziv().toLowerCase().contains(parametar.toLowerCase())
                            || k.getOpis().toLowerCase().contains(parametar.toLowerCase())) {
                        novaLista.add(k);
                    }
                }
                lista = novaLista;
            }

            fireTableDataChanged();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Kolac> getLista() {
        return lista;
    }

    
}
