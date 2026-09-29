/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.kupac;

import dbb.DBBroker;
import model.AbstractDomainObject;
import model.Kupac;
import java.util.ArrayList;
import java.util.regex.Pattern;
import sistemske.AbstractSO;

/**
 *
 * @author Mina
 */
public class SOUpdateKupac extends AbstractSO {

    private static final Pattern EMAIL_PATTERN
            = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern TELEFON_PATTERN
            = Pattern.compile("^06[0-9]{8}$");

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Kupac)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Kupac!");
        }

        Kupac k = (Kupac) ado;

        if (!EMAIL_PATTERN.matcher(k.getEmail()).matches()) {
            throw new Exception("Email nije u ispravnom formatu!");
        }

        if (!TELEFON_PATTERN.matcher(k.getTelefon()).matches()) {
            throw new Exception("Telefon mora biti u formatu 06XXXXXXXX!");
        }

        ArrayList<Kupac> kupci = (ArrayList<Kupac>) (ArrayList<?>) DBBroker.getInstance().select(new Kupac());

        for (Kupac kupac : kupci) {
            if (!kupac.getKupacID().equals(k.getKupacID())) {
                if (kupac.getEmail().equals(k.getEmail())) {
                    throw new Exception("Kupac sa tim emailom vec postoji u bazi!");
                }
                if (kupac.getTelefon().equals(k.getTelefon())) {
                    throw new Exception("Kupac sa tim telefonom vec postoji u bazi!");
                }
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().update(ado);
    }

}
