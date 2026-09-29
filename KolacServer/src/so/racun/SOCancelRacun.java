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
public class SOCancelRacun extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        PreparedStatement ps = DBBroker.getInstance().insert(ado);

        ResultSet tableKeys = ps.getGeneratedKeys();
        tableKeys.next();
        Long stornoRacunID = tableKeys.getLong(1);

        Racun stornoRacun = (Racun) ado;
        stornoRacun.setRacunID(stornoRacunID);

        for (StavkaRacuna stavkaRacuna : stornoRacun.getStavkeRacuna()) {
            stavkaRacuna.setRacun(stornoRacun);
            DBBroker.getInstance().insert(stavkaRacuna);
        }
    }

}
