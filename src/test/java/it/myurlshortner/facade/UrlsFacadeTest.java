/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.myurlshortner.facade;

import it.myurlshortner.dao.UrlsDao;
import it.myurlshortner.entity.Url;
import it.myurlshortner.exception.DaoException;
import it.myurlshortner.exception.FacadeException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

/**
 *
 * @author NATCRI
 */
public class UrlsFacadeTest {

    private static final long ID = 12345L;
    private static final String SHORT_URL = "dnh";
    private static final String LONG_URL = "https://www.payroc.com/";

    @Spy
    @InjectMocks
    UrlsFacade facade;

    @Mock
    UrlsDao dao;

    @Mock
    Url url;

    @Before
    public void init() throws DaoException {
        MockitoAnnotations.openMocks(this);
        doReturn(url).when(dao).get(ID);
        doReturn(url).when(dao).create(url);
        doReturn(ID).when(url).getId();
        doReturn(LONG_URL).when(url).getLongUrl();
        doReturn(url).when(facade).getNewUrl(any());
    }

    @Test
    public void when_get_throws_DaoException_then_rethrow_FacadeException() throws DaoException, FacadeException {
        doThrow(DaoException.class).when(dao).get(anyLong());
        Assert.assertThrows(FacadeException.class, () -> {
            facade.get(SHORT_URL);
        });
    }

    @Test
    public void given_existant_shortUrl_When_get_Then_return_its_longUrl() throws DaoException, FacadeException {
        Assert.assertEquals(LONG_URL, facade.get(SHORT_URL));
    }

    @Test
    public void given_unexistant_shortUrl_When_get_Then_return_null() throws DaoException, FacadeException {
        Assert.assertNull(facade.get(SHORT_URL + "a"));
    }

    @Test
    public void when_set_throws_DaoException_then_rethrow_FacadeException() throws DaoException, FacadeException {
        doThrow(DaoException.class).when(dao).create(any(Url.class));
        Assert.assertThrows(FacadeException.class, () -> {
            facade.set(LONG_URL);
        });
    }

    @Test
    public void given_not_null_longUrl_When_set_Then_return_its_shortUrl() throws DaoException, FacadeException {
        Assert.assertEquals(SHORT_URL, facade.set(LONG_URL));
    }

    @Test
    public void given_no_created_url_When_set_Then_throw_FacadeException() throws DaoException, FacadeException {
        doReturn(null).when(dao).create(url);
        Assert.assertThrows(FacadeException.class, () -> {
            facade.set(LONG_URL);
        });
    }

}
