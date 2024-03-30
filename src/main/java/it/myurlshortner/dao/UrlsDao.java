/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.myurlshortner.dao;

import it.myurlshortner.entity.Url;
import it.myurlshortner.exception.DaoException;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

/**
 * Dao for creating and loading Urls
 *
 * @author NATCRI
 */
@ApplicationScoped
public class UrlsDao {

    @PersistenceContext(unitName = "urlShortnerPU")
    protected EntityManager em;

    @Transactional(Transactional.TxType.NOT_SUPPORTED)
    public Url get(Long id) throws DaoException {
        try {
            return em.find(Url.class, id);
        } catch (IllegalArgumentException ex) {
            throw new DaoException(ex);
        }
    }

    public Url create(Url url) throws DaoException {
        try {
            em.persist(url);
            return url;
        } catch (PersistenceException | IllegalArgumentException ex) {
            throw new DaoException(ex);
        }
    }
}
