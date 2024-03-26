/**
 * Eccezione del dao.
 *
 * (C) Copyright (2022) Zucchetti Hospitality s.r.l.
 * author GALLUA on 25/01/2022
 */
package it.myurlshortner.exception;

/**
 *
 * @author NATCRI
 */
public class DaoException extends Exception {

    private static final long serialVersionUID = -6256194374708015714L;

    public DaoException(Exception ex) {
        super(ex);
    }

}
