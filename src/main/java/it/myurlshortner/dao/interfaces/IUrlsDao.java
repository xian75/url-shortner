/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package it.myurlshortner.dao.interfaces;

import it.myurlshortner.entity.Url;
import it.myurlshortner.exception.DaoException;
import javax.ejb.Local;

/**
 *
 * @author NATCRI
 */
@Local
public interface IUrlsDao {

    /**
     * Load Url by id
     *
     * @param id the Url id to load
     * @return Url instance if exists or null otherwise
     * @throws DaoException if id is illegal or inappropriate argument
     */
    public Url get(Long id) throws DaoException;

    /**
     * Create a new Url
     *
     * @param url a new Url instance
     * @return the new Url
     * @throws DaoException if the entity already exists, the instance is not an
     * entity or there is no transaction when invoked on this container-managed
     * bean
     */
    public Url create(Url url) throws DaoException;
}
