/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.poslasticar;

import so.kupac.*;
import dbb.DBBroker;
import model.AbstractDomainObject;
import model.Kupac;
import java.util.ArrayList;
import model.Poslasticar;
import sistemske.AbstractSO;

/**
 *
 * @author Mina
 */
public class SOGetAllPoslasticar extends AbstractSO {

    private ArrayList<Poslasticar> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Poslasticar)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Poslasticar!");
}
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> poslasticari = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Poslasticar>) (ArrayList<?>) poslasticari;
    }

    public ArrayList<Poslasticar> getLista() {
        return lista;
    }

}
