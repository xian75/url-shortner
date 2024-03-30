/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.myurlshortner.facade;

import it.myurlshortner.dao.UrlsDao;
import it.myurlshortner.entity.Url;
import it.myurlshortner.exception.DaoException;
import it.myurlshortner.exception.FacadeException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import static javax.transaction.Transactional.TxType;

/**
 * Facade for reading/writing Urls with Base62 encoding/decoding. For Base62
 * convertion see:
 * https://www.geeksforgeeks.org/how-to-design-a-tiny-url-or-url-shortener/
 *
 * @author NATCRI
 */
@ApplicationScoped
public class UrlsFacade {

    private static final char map[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();

    @Inject
    protected UrlsDao dao;

    @Transactional(TxType.NOT_SUPPORTED)
    public String get(String shortUrl) throws FacadeException {
        try {
            Url url = dao.get(shortURLtoID(shortUrl));
            return url != null ? url.getLongUrl() : null;
        } catch (DaoException ex) {
            throw new FacadeException(ex);
        }
    }

    @Transactional
    public String set(String longUrl) throws FacadeException {
        try {
            Url url = dao.create(getNewUrl(longUrl));
            if (url == null) {
                throw new FacadeException("Something went wrong: url was not created");
            }
            return idToShortURL(url.getId());
        } catch (DaoException ex) {
            throw new FacadeException(ex);
        }
    }

    protected Url getNewUrl(String longUrl) {
        return new Url(longUrl);
    }

    private String idToShortURL(Long n) {
        if (n == null) {
            return null;
        }
        StringBuffer shorturl = new StringBuffer();
        while (n > 0) {
            shorturl.append(map[(int) (n % 62)]);
            n = n / 62;
        }
        return shorturl.toString();
    }

    private Long shortURLtoID(String shortURL) {
        if (shortURL == null) {
            return null;
        }
        long id = 0;
        for (int i = shortURL.length() - 1; i >= 0; i--) {
            char x = shortURL.charAt(i);
            if ('a' <= x && x <= 'z') {
                id = id * 62 + x - 'a';
            }
            if ('A' <= x && x <= 'Z') {
                id = id * 62 + x - 'A' + 26;
            }
            if ('0' <= x && x <= '9') {
                id = id * 62 + x - '0' + 52;
            }
        }
        return id;
    }
}
