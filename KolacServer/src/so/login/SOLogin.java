/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.login;

import controller.ServerController;
import dbb.DBBroker;
import model.AbstractDomainObject;
import model.Poslasticar;
import java.util.ArrayList;
import sistemske.AbstractSO;

/**
 *
 * @author Mina
 */
public class SOLogin extends AbstractSO {

    Poslasticar ulogovani;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Poslasticar)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Poslasticar!");
        }

        Poslasticar p = (Poslasticar) ado;

        for (Poslasticar poslasticar : ServerController.getInstance().getUlogovaniPoslasticari()) {
            if (poslasticar.getKorisnickoIme().equals(p.getKorisnickoIme())) {
                throw new Exception("Ovaj poslasticar je vec ulogovan na sistem!");
            }
        }

    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        Poslasticar p = (Poslasticar) ado;

        ArrayList<Poslasticar> poslasticari
                = (ArrayList<Poslasticar>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Poslasticar poslasticar : poslasticari) {
            if (poslasticar.getKorisnickoIme().equals(p.getKorisnickoIme())
                    && poslasticar.getLozinka().equals(p.getLozinka())) {
                ulogovani = poslasticar;
                ServerController.getInstance().getUlogovaniPoslasticari().add(poslasticar);
                return;
            }
        }

        throw new Exception("Korisničko ime i šifra nisu ispravni.");

    }

    public Poslasticar getUlogovani() {
        return ulogovani;
    }

}
