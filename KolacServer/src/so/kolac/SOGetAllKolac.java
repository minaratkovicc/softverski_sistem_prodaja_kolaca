/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.kolac;

import so.kupac.*;
import dbb.DBBroker;
import model.AbstractDomainObject;
import model.Kolac;
import java.util.ArrayList;
import sistemske.AbstractSO;

/**
 *
 * @author Mina
 */
public class SOGetAllKolac extends AbstractSO {

    private ArrayList<Kolac> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Kolac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Kolac!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        ArrayList<AbstractDomainObject> kolaci = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Kolac>) (ArrayList<?>) kolaci;
    }

    public ArrayList<Kolac> getLista() {
        return lista;
    }

}
