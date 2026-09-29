/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mina
 */
public class Kolac extends AbstractDomainObject {

    private Long kolacID;
    private String naziv;
    private String opis;
    private double cena;
    private String gluten;

    public Kolac() {
    }

    public Kolac(Long kolacID, String naziv, String opis, double cena, String gluten) {
        this.kolacID = kolacID;
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.gluten = gluten;
    }

    @Override
    public String nazivTabele() {
        return " Kolac ";
    }

    @Override
    public String alijasTabele() {
        return " KO ";
    }

    @Override
    public String joinUpit() {
        return " ";

    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Kolac k = new Kolac(
                    rs.getLong("kolacID"),
                    rs.getString("KO.Naziv"),
                    rs.getString("KO.Opis"),
                    rs.getDouble("KO.Cena"),
                    rs.getString("KO.Gluten")
            );
            lista.add(k);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Naziv, Opis, Cena, Gluten) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + naziv + "', '" 
     + opis + "', '" 
     + cena + "', '"  
     + gluten + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Opis = '" + opis + "', Cena = " + cena;
    }

    @Override
    public String uslovZaWhere() {
        return " KolacID = " + kolacID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }
    
     @Override
    public String toString() {
        return naziv + " (Cena: " + cena + "din)";
    }

    public Long getKolacID() {
        return kolacID;
    }

    public void setKolacID(Long kolacID) {
        this.kolacID = kolacID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    

    public String getGluten() {
        return gluten;
    }

    public void setGluten(String gluten) {
        this.gluten = gluten;
    }
    
    

}
