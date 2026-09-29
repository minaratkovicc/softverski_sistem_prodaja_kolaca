package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Mesto extends AbstractDomainObject {

    private Long mestoID;
    private String naziv;

    public Mesto() {
    }

    public Mesto(Long mestoID, String naziv) {
        this.mestoID = mestoID;
        this.naziv = naziv;
    }

    @Override
    public String nazivTabele() {
        return " Mesto ";
    }

    @Override
    public String alijasTabele() {
        return " M ";
    }

    @Override
    public String joinUpit() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Mesto m = new Mesto(
                    rs.getLong("MestoID"),
                    rs.getString("M.Naziv")
            );
            lista.add(m);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (Naziv) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + naziv + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " Naziv = '" + naziv + "' ";
    }

    @Override
    public String uslovZaWhere() {
        return " MestoID = " + mestoID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    @Override
    public String toString() {
        return naziv;
    }

    public Long getMestoID() {
        return mestoID;
    }

    public void setMestoID(Long mestoID) {
        this.mestoID = mestoID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
