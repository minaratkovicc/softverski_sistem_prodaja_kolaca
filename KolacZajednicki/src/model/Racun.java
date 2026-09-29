package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

public class Racun extends AbstractDomainObject {

    private Long racunID;
    private Date datumVreme;
    private String status;
    private Long stornoOdRacunaID;
    private double ukupanIznos;
    private Poslasticar poslasticar;
    private Kupac kupac;
    private ArrayList<StavkaRacuna> stavkeRacuna;
    private String parametar = "";

    public Racun() {
    }

    public Racun(Long racunID, Date datumVreme, String status, Long stornoOdRacunaID, double ukupanIznos,
            Poslasticar poslasticar, Kupac kupac, ArrayList<StavkaRacuna> stavke) {
        this.racunID = racunID;
        this.datumVreme = datumVreme;
        this.status = status;
        this.stornoOdRacunaID = stornoOdRacunaID;
        this.ukupanIznos = ukupanIznos;
        this.poslasticar = poslasticar;
        this.kupac = kupac;
        this.stavkeRacuna = stavke;
    }

    @Override
    public String nazivTabele() {
        return " Racun ";
    }

    @Override
    public String alijasTabele() {
        return " R ";
    }

    @Override
    public String joinUpit() {
        return " JOIN Poslasticar P ON ( P.PoslasticarID = R.PoslasticarID ) "
                + " JOIN Kupac K ON ( K.KupacID = R.KupacID ) "
                + " JOIN Mesto M ON ( M.MestoID = K.MestoID ) ";
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
                    rs.getLong("R.StornoOdRacunaID"),
                    rs.getDouble("R.UkupanIznos"),
                    p, k, new ArrayList<>()
            );
            lista.add(r);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (datumVreme, Status, StornoOdRacunaID, UkupanIznos, PoslasticarID, KupacID) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + new Timestamp(datumVreme.getTime()) + "', "
                + "'" + status + "', " + stornoOdRacunaID + ", " + ukupanIznos + ", "
                + poslasticar.getPoslasticarID() + ", " + kupac.getKupacID();
    }

    @Override
    public String vrednostiZaUpdate() {
        return "ukupanIznos = " + ukupanIznos + ", "
                + "status = '" + status + "' " + ", "
                + "datumVreme = '" + new java.sql.Timestamp(datumVreme.getTime()) + "' ";
    }

    @Override
    public String uslovZaWhere() {
        return " RacunID = " + racunID;
    }

    @Override
    public String uslovZaSelect() {
       String uslov = " WHERE 1=1 ";

    if (kupac != null) {
        uslov += " AND K.KupacID = " + kupac.getKupacID();
    }

    if (poslasticar != null) {
        uslov += " AND P.PoslasticarID = " + poslasticar.getPoslasticarID();
    }

    if (parametar != null && !parametar.trim().isEmpty()) {
        uslov += " AND (K.Ime LIKE '%" + parametar + "%' OR K.Prezime LIKE '%" + parametar + "%' "
               + "OR P.Ime LIKE '%" + parametar + "%' OR P.Prezime LIKE '%" + parametar + "%')";
    }

    uslov += " ORDER BY R.RacunID ASC ";
    return uslov;
    }

    public Long getRacunID() {
        return racunID;
    }

    public void setRacunID(Long racunID) {
        this.racunID = racunID;
    }

    public Date getDatumVreme() {
        return datumVreme;
    }

    public void setDatumVreme(Date datumVreme) {
        this.datumVreme = datumVreme;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getUkupanIznos() {
        return ukupanIznos;
    }

    public void setUkupanIznos(double ukupanIznos) {
        this.ukupanIznos = ukupanIznos;
    }

    public Poslasticar getPoslasticar() {
        return poslasticar;
    }

    public void setPoslasticar(Poslasticar poslasticar) {
        this.poslasticar = poslasticar;
    }

   

    public Kupac getKupac() {
        return kupac;
    }

    public void setKupac(Kupac kupac) {
        this.kupac = kupac;
    }

    public ArrayList<StavkaRacuna> getStavkeRacuna() {
        return stavkeRacuna;
    }

    public void setStavkeRacuna(ArrayList<StavkaRacuna> stavkeRacuna) {
        this.stavkeRacuna = (stavkeRacuna != null) ? stavkeRacuna : new ArrayList<>();
    }

    public Long getStornoOdRacunaID() {
        return stornoOdRacunaID;
    }

    public void setStornoOdRacunaID(Long stornoOdRacunaID) {
        this.stornoOdRacunaID = stornoOdRacunaID;
    }

    public String getParametar() {
        return parametar;
    }

    public void setParametar(String parametar) {
        this.parametar = parametar;
    }
    
}
