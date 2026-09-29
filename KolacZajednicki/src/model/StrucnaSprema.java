package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class StrucnaSprema extends AbstractDomainObject {

    private Long strucnaSpremaID;
    private String naziv;

    public StrucnaSprema(Long strucnaSpremaID, String naziv) {
        this.strucnaSpremaID = strucnaSpremaID;
        this.naziv = naziv;
    }

    public StrucnaSprema() {
    }

    @Override
    public String nazivTabele() {
        return " StrucnaSprema ";
    }

    @Override
    public String alijasTabele() {
        return " ss ";
    }

    @Override
    public String joinUpit() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            StrucnaSprema ss = new StrucnaSprema(rs.getLong("strucnaSpremaID"),
                    rs.getString("naziv"));
            lista.add(ss);
        }
        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (naziv) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " '" + naziv + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
        return " naziv = '" + naziv + "' ";
    }

    @Override
    public String uslovZaWhere() {
        return " strucnaSpremaID = " + strucnaSpremaID;
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }

    public Long getStrucnaSpremaID() {
        return strucnaSpremaID;
    }

    public void setStrucnaSpremaID(Long strucnaSpremaID) {
        this.strucnaSpremaID = strucnaSpremaID;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
}
