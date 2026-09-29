/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transfer;

import operacije.StatusOdg;
import java.io.Serializable;

/**
 *
// * @author Mina
 */
public class ServerskiOdgovor implements Serializable {

    private Object odgovor;
    private Exception exc;
    private StatusOdg statusOdgovora;

    public ServerskiOdgovor() {
    }

    public ServerskiOdgovor(Object odgovor, Exception exc, StatusOdg statusOdgovora) {
        this.odgovor = odgovor;
        this.exc = exc;
        this.statusOdgovora = statusOdgovora;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object data) {
        this.odgovor = data;
    }

    public Exception getException() {
        return exc;
    }

    public void setException(Exception exc) {
        this.exc = exc;
    }

    public StatusOdg getStatusOdgovora() {
        return statusOdgovora;
    }

    public void setStatusOdgovora(StatusOdg statusOdgovora) {
        this.statusOdgovora = statusOdgovora;
    }

}
