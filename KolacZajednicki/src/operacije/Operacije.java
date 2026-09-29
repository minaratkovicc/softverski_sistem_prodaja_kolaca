/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package operacije;

/**
 *
 * @author Mina
 */
public interface Operacije {

    public static final int LOGIN = 0;
    public static final int LOGOUT = 1;

    public static final int ADD_KUPAC = 2;
    public static final int DELETE_KUPAC = 3;
    public static final int UPDATE_KUPAC = 4;
    public static final int GET_ALL_KUPAC = 5;

    public static final int ADD_KOLAC = 6;
    public static final int DELETE_KOLAC = 7;
    public static final int UPDATE_KOLAC = 8;
    public static final int GET_ALL_KOLAC = 9;

    public static final int ADD_RACUN = 10;
    public static final int CANCEL_RACUN = 11;
    public static final int UPDATE_RACUN = 12;
    public static final int GET_ALL_RACUN = 13;

    public static final int GET_ALL_MESTO = 14;
    public static final int GET_ALL_POSLASTICAR = 15;

}
