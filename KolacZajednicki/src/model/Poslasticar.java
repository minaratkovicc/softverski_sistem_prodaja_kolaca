package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Poslasticar extends AbstractDomainObject {

    private Long poslasticarID;
    private String ime;
    private String prezime;
    private String korisnickoIme;
    private String lozinka;

    public Poslasticar() {
    }

    public Poslasticar(Long poslasticarID, String ime, String prezime, String korisnickoIme, String lozinka) {
        this.poslasticarID = poslasticarID;
        this.ime = ime;
        this.prezime = prezime;
        this.korisnickoIme = korisnickoIme;
        this.lozinka = lozinka;
    }

    

    @Override
    public String nazivTabele() {
        return " poslasticar ";
    }

    @Override
    public String alijasTabele() {
        return " P ";
    }

    @Override
    public String joinUpit() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {

            Poslasticar p = new Poslasticar(rs.getLong("PoslasticarID"),
                    rs.getString("P.Ime"), rs.getString("P.Prezime"),
                    rs.getString("P.KorisnickoIme"), rs.getString("P.Lozinka"));

            lista.add(p);

        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (ime, prezime, korisnickoIme, lozinka) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + ime + "', '" + prezime + "', "
                + "'" + korisnickoIme + "', '" + lozinka + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " ime = '" + ime + "', prezime = '" + prezime + "', "
                + "korisnickoIme = '" + korisnickoIme + "', "
                + "lozinka = '" + lozinka + "' ";
    }

    @Override
    public String uslovZaWhere() {
        return " PoslasticarID = " + poslasticarID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof Poslasticar)) {
            return false;
        }
        return ((Poslasticar) obj).getPoslasticarID().equals(this.poslasticarID);
    }

    public Long getPoslasticarID() {
        return poslasticarID;
    }

    public void setPoslasticarID(Long poslasticarID) {
        this.poslasticarID = poslasticarID;
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

    public String getKorisnickoIme() {
        return korisnickoIme;
    }

    public void setKorisnickoIme(String korisnickoIme) {
        this.korisnickoIme = korisnickoIme;
    }

    public String getLozinka() {
        return lozinka;
    }

    public void setLozinka(String lozinka) {
        this.lozinka = lozinka;
    }

    @Override
    public String toString() {
        return ime + " " + prezime;
    }

}
