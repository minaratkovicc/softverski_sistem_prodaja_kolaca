/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import model.StavkaRacuna;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Mina
 */
public class TableModelStavkeRacuna extends AbstractTableModel {

    private ArrayList<StavkaRacuna> lista;
    private String[] kolone = {"Rb", "Kolac", "Cena", "Kolicina", "Iznos"};
    private int rb = 0;

    public TableModelStavkeRacuna() {
        lista = new ArrayList<>();
    }

    public TableModelStavkeRacuna(ArrayList<StavkaRacuna> stavkeRacuna) {
        lista = stavkeRacuna;
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
        StavkaRacuna sr = lista.get(row);

        switch (column) {
            case 0:
                return sr.getRb();
            case 1:
                return sr.getKolac().getNaziv();
            case 2:
                return sr.getCena() + "din";
            case 3:
                return sr.getKolicina();
            case 4:
                return sr.getIznos() + "din";

            default:
                return null;
        }
    }

    public void dodajStavku(StavkaRacuna sr) {

        for (StavkaRacuna stavkaRacuna : lista) {
            if (stavkaRacuna.getKolac().getKolacID().equals(sr.getKolac().getKolacID())) {
                stavkaRacuna.setKolicina(stavkaRacuna.getKolicina() + sr.getKolicina());
                stavkaRacuna.setIznos(stavkaRacuna.getIznos() + sr.getIznos());
                fireTableDataChanged();
                return;
            }
        }

        rb = lista.size();
        sr.setRb(++rb);
        lista.add(sr);
        fireTableDataChanged();
    }

    public void obrisiStavku(int row) {
        lista.remove(row);

        rb = 0;
        for (StavkaRacuna stavkaRacuna : lista) {
            stavkaRacuna.setRb(++rb);
        }

        fireTableDataChanged();
    }

    public double vratiUkupanIznos() {
        double ukupanIznos = 0;

        for (StavkaRacuna stavkaRacuna : lista) {
            ukupanIznos += stavkaRacuna.getIznos();
        }

        return ukupanIznos;
    }

    public ArrayList<StavkaRacuna> getLista() {
        return lista;
    }

    
    
}
