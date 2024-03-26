/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.myurlshortner.dao;

import it.myurlshortner.dao.interfaces.IUrlsDao;
import it.myurlshortner.entity.Url;
import it.myurlshortner.exception.DaoException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 * Dao for creating and loading Urls
 *
 * @author NATCRI
 */
@Stateless(name = "myurlshortner/dao/urls", mappedName = "myurlshortner/dao/urls")
public class UrlsDao implements IUrlsDao {

    @PersistenceContext(unitName = "urlShortnerPU")
    protected EntityManager em;

    @Override
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Url get(Long id) throws DaoException {
        try {
            return em.find(Url.class, id);
        } catch (IllegalArgumentException ex) {
            throw new DaoException(ex);
        }
    }

    @Override
    public Url create(Url url) throws DaoException {
        try {
            em.persist(url);
            return url;
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DaoException(ex);
        }
    }
}
