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
public class PoslasticarSS extends AbstractDomainObject {
    private Poslasticar poslasticar;
    private StrucnaSprema strucnaSprema;
    private Date datumSticanja;

    public PoslasticarSS() {
    }

    public PoslasticarSS(Poslasticar poslasticar, StrucnaSprema strucnaSprema, Date datumSticanja) {
        this.poslasticar = poslasticar;
        this.strucnaSprema = strucnaSprema;
        this.datumSticanja = datumSticanja;
    }

    public Poslasticar getPoslasticar() {
        return poslasticar;
    }

    public void setPoslasticar(Poslasticar poslasticar) {
        this.poslasticar = poslasticar;
    }

    public StrucnaSprema getStrucnaSprema() {
        return strucnaSprema;
    }

    public void setStrucnaSprema(StrucnaSprema strucnaSprema) {
        this.strucnaSprema = strucnaSprema;
    }

    public Date getDatumSticanja() {
        return datumSticanja;
    }

    public void setDatumSticanja(Date datumSticanja) {
        this.datumSticanja = datumSticanja;
    }
    
    

    @Override
    public String nazivTabele() {
        return " PoslasticarSS ";
    }

    @Override
    public String alijasTabele() {
        return " PSS ";
    }

    @Override
    public String joinUpit() {
        return " JOIN POSLASTICAR P ON ( P.POSLASTICARID = PSS.POSLASTICARID ) "
                + " JOIN StrucnaSprema SS ON ( SS.StrucnaSpremaID = PSS.StrucnaSpremaID ) ";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();
        while (rs.next()) {
            Poslasticar p = new Poslasticar(
                    rs.getLong("PoslasticarID"),
                    rs.getString("P.Ime"),
                    rs.getString("P.Prezime"),
                    rs.getString("P.KorisnickoIme"),
                    rs.getString("P.Lozinka")
            );
             StrucnaSprema ss = new StrucnaSprema(
                    rs.getLong("SS.StrucnaSpremaID"),
                    rs.getString("SS.Naziv")
            );
            PoslasticarSS pss = new PoslasticarSS(
                    p,
                    ss,
                    rs.getDate("PSS.DatumSticanja")
            );
            lista.add(pss);
        }
        rs.close();
        return lista;
    
    }

    @Override
    public String koloneZaInsert() {
        return " (PoslasticarID, StrucnaSpremaID, DatumSticanja) ";
    }

    @Override
    public String vrednostiZaInsert() {
        return " " + poslasticar.getPoslasticarID()
                + ", " + strucnaSprema.getStrucnaSpremaID()
                + ", '" + new java.sql.Date(datumSticanja.getTime()) + "' ";
    }

    @Override
    public String vrednostiZaUpdate() {
         return " DatumSticanja = '" + new java.sql.Date(datumSticanja.getTime()) + "' ";
    }

    @Override
    public String uslovZaWhere() {
        return " poslasticarID = " + poslasticar.getPoslasticarID()
                + " AND StrucnaSpremaID = " + strucnaSprema.getStrucnaSpremaID();
    }

    @Override
    public String uslovZaSelect() {
        return "";
    }
    
}
