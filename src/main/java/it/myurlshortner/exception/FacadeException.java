/**
 * Eccezione del facade.
 *
 * (C) Copyright (2022) Zucchetti Hospitality s.r.l.
 * author GALLUA on 25/01/2022
 */
package it.myurlshortner.exception;

/**
 *
 * @author NATCRI
 */
public class FacadeException extends Exception {

    private static final long serialVersionUID = -2717930870343935673L;

    public FacadeException(Exception ex) {
        super(ex);
    }

    public FacadeException(String message) {
        super(message);
    }
}
