package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Kupac extends AbstractDomainObject {

    private Long kupacID;
    private String ime;
    private String prezime;
    private String email;
    private String telefon;
    private Mesto mesto;

    public Kupac() {
    }

    public Kupac(Long kupacID, String ime, String prezime, String email, String telefon, Mesto mesto) {
        this.kupacID = kupacID;
        this.ime = ime;
        this.prezime = prezime;
        this.email = email;
        this.telefon = telefon;
        this.mesto = mesto;
    }

    @Override
    public String nazivTabele() {
        return " Kupac ";
    }

    @Override
    public String alijasTabele() {
        return " K ";
    }

    @Override
    public String joinUpit() {
        return " JOIN Mesto M ON ( M.MestoID = K.MestoID ) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Mesto m = new Mesto(
                    rs.getLong("MestoID"),
                    rs.getString("M.Naziv")
            );
            Kupac k = new Kupac(
                    rs.getLong("KupacID"),
                    rs.getString("K.Ime"),
                    rs.getString("K.Prezime"),
                    rs.getString("K.Email"),
                    rs.getString("K.Telefon"),
                    m
            );
            lista.add(k);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Ime, Prezime, Email, Telefon, MestoID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + ime + "', '" + prezime + "', '" + email + "', "
                + "'" + telefon + "', " + mesto.getMestoID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "Ime = '" + ime + "', "
         + "Prezime = '" + prezime + "', "
         + "Email = '" + email + "', "
         + "Telefon = '" + telefon + "', "
         + "MestoID = " + mesto.getMestoID();
    }

    @Override
    public String uslovZaWhere() {
        return " KupacID = " + kupacID;
    }

    @Override
    public String uslovZaSelect() {
        String uslov = " WHERE 1=1 ";

   
    if (ime != null && !ime.trim().isEmpty()) {

        String[] delovi = ime.trim()
                .toLowerCase()
                .split("\\s+");

        for (String deo : delovi) {
            deo = deo.replace("'", "''");

            uslov += " AND (LOWER(K.Ime) LIKE '%" + deo
                    + "%' OR LOWER(K.Prezime) LIKE '%"
                    + deo + "%') ";
        }
    }

    if (email != null && !email.trim().isEmpty()) {
        String emailZaPretragu = email.trim()
                .toLowerCase()
                .replace("'", "''");

        uslov += " AND LOWER(K.Email) LIKE '%"
                + emailZaPretragu + "%' ";
    }

    if (mesto != null && mesto.getMestoID() != null) {
        uslov += " AND K.MestoID = "
                + mesto.getMestoID() + " ";
    }

    return uslov + " ORDER BY K.KupacID ASC ";
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

    public Long getKupacID() {
        return kupacID;
    }

    public void setKupacID(Long kupacID) {
        this.kupacID = kupacID;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Mesto getMesto() {
        return mesto;
    }

    public void setMesto(Mesto mesto) {
        this.mesto = mesto;
    }
}
