/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package it.myurlshortner.facade.interfaces;

import it.myurlshortner.exception.FacadeException;
import javax.ejb.Local;

/**
 *
 * @author NATCRI
 */
@Local
public interface IUrlsFacade {

    /**
     * Get long url by its short url
     *
     * @param shortUrl the short url to find
     * @return the related long url
     * @throws FacadeException
     */
    public String get(String shortUrl) throws FacadeException;

    /**
     * Save a new long url returning its unique short url
     *
     * @param longUrl the long url to save
     * @return the generated short url
     * @throws FacadeException
     */
    public String set(String longUrl) throws FacadeException;
}
