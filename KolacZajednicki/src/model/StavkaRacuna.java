package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StavkaRacuna extends AbstractDomainObject {

    private Racun racun;
    private int rb;
    private int kolicina;
    private double cena;
    private double iznos;
    private Kolac kolac;

    public StavkaRacuna() {
    }

    public StavkaRacuna(Racun racun, int rb, int kolicina, double cena, double iznos, Kolac kolac) {
        this.racun = racun;
        this.rb = rb;
        this.kolicina = kolicina;
        this.cena = cena;
        this.iznos = iznos;
        this.kolac = kolac;
    }

    @Override
    public String nazivTabele() {
        return " StavkaRacuna ";
    }

    @Override
    public String alijasTabele() {
        return " SR ";
    }

    @Override
    public String joinUpit() {
        return " JOIN Racun R ON ( R.RacunID = SR.RacunID ) "
                + " JOIN Poslasticar P ON ( P.PoslasticarID = R.PoslasticarID ) "
                + " JOIN Kupac K ON ( K.KupacID = R.KupacID ) "
                + " JOIN Mesto M ON ( M.MestoID = K.MestoID ) "
                + " JOIN Kolac KO ON ( KO.KolacID = SR.KolacID ) ";
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
            Poslasticar p = new Poslasticar(
                    rs.getLong("PoslasticarID"),
                    rs.getString("P.Ime"),
                    rs.getString("P.Prezime"),
                    rs.getString("P.KorisnickoIme"),
                    rs.getString("P.Lozinka")
            );
            Racun r = new Racun(
                    rs.getLong("RacunID"),
                    rs.getTimestamp("R.datumVreme"),
                    rs.getString("R.Status"),
                    rs.getLong("StornoOdRacunaID"),
                    rs.getDouble("R.UkupanIznos"),
                    p, k, new ArrayList<>()
            );
            Kolac ko = new Kolac(
                    rs.getLong("kolacID"),
                    rs.getString("KO.Naziv"),
                    rs.getString("KO.Opis"),
                    rs.getDouble("KO.Cena"),
                    rs.getString("KO.Gluten")
                    
            );
            StavkaRacuna sr = new StavkaRacuna(
                    r,
                    rs.getInt("SR.Rb"),
                    rs.getInt("SR.Kolicina"),
                    rs.getDouble("SR.Cena"),
                    rs.getDouble("SR.Iznos"),
                    ko
            );
            
            lista.add(sr);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (RacunID, Rb, Kolicina, Cena, Iznos, KolacID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + racun.getRacunID()
                + ", " + rb
                + ", " + kolicina
                + ", " + cena
                + ", " + iznos
                + ", " + kolac.getKolacID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Kolicina = " + kolicina
                + ", Cena = " + cena
                + ", Iznos = " + iznos
                + ", KolacID = " + kolac.getKolacID() + " ";
    }

    @Override
    public String uslovZaWhere() {
        return " RacunID = " + racun.getRacunID() + " AND Rb = " + rb;
    }

    @Override
    public String uslovZaSelect() {
        return " WHERE R.RacunID = " + racun.getRacunID();
    }

    public Racun getRacun() {
        return racun;
    }

    public void setRacun(Racun racun) {
        this.racun = racun;
    }

    public int getRb() {
        return rb;
    }

    public void setRb(int rb) {
        this.rb = rb;
    }

    public int getKolicina() {
        return kolicina;
    }

    public void setKolicina(int kolicina) {
        this.kolicina = kolicina;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

    public double getIznos() {
        return iznos;
    }

    public void setIznos(double iznos) {
        this.iznos = iznos;
    }

    public Kolac getKolac() {
        return kolac;
    }

    public void setKolac(Kolac kolac) {
        this.kolac = kolac;
    }

  
}
