/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.racun;

import dbb.DBBroker;
import model.AbstractDomainObject;
import model.Racun;
import model.StavkaRacuna;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import sistemske.AbstractSO;

/**
 *
 * @author Mina
 */
public class SOAddRacun extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }

        Racun r = (Racun) ado;

        if (r.getStavkeRacuna().isEmpty()) {
            throw new Exception("Racun mora imati barem jednu stavku!");
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        PreparedStatement ps = DBBroker.getInstance().insert(ado);

        
        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long noviRacunID = tableKeys.getLong(1);

        
        Racun noviRacun = (Racun) ado;
        noviRacun.setRacunID(noviRacunID);

        
        for (StavkaRacuna stavkaRacuna : noviRacun.getStavkeRacuna()) {
            stavkaRacuna.setRacun(noviRacun);
            DBBroker.getInstance().insert(stavkaRacuna);
        }

    }

}
