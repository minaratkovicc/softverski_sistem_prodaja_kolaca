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
import java.util.ArrayList;
import sistemske.AbstractSO;

/**
 *
 * @author Mina
 */
public class SOGetAllRacun extends AbstractSO {

    private ArrayList<Racun> lista;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Racun)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Racun!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        
        ArrayList<AbstractDomainObject> racuni = DBBroker.getInstance().select(ado);
        lista = (ArrayList<Racun>) (ArrayList<?>) racuni;

        for (Racun trenutniRacun : lista) {

            
            StavkaRacuna stavkaRacuna = new StavkaRacuna();
            stavkaRacuna.setRacun(trenutniRacun);

            ArrayList<StavkaRacuna> stavkeTrenutnogRacuna
                    = (ArrayList<StavkaRacuna>) (ArrayList<?>) DBBroker.getInstance().select(stavkaRacuna);

            trenutniRacun.setStavkeRacuna(stavkeTrenutnogRacuna);
        }
    }

    public ArrayList<Racun> getLista() {
        return lista;
    }

}
