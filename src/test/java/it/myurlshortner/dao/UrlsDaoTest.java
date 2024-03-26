/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.myurlshortner.dao;

import it.myurlshortner.entity.Url;
import it.myurlshortner.exception.DaoException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

/**
 *
 * @author NATCRI
 */
public class UrlsDaoTest {

    private static final Long ID = 1L;

    @Spy
    @InjectMocks
    UrlsDao dao;

    @Mock
    EntityManager em;

    @Mock
    Url url;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_existant_id_When_get_Then_return_its_url() throws DaoException {
        doReturn(url).when(em).find(any(), any());
        assertEquals(url, dao.get(ID));
    }

    @Test
    public void given_not_existant_id_When_get_Then_return_null() throws DaoException {
        doReturn(null).when(em).find(any(), any());
        assertNull(dao.get(ID));
    }

    @Test
    public void when_get_throws_IllegalArgumentException_then_rethrow_DaoException() throws DaoException {
        doThrow(IllegalArgumentException.class).when(em).find(any(), any());
        Assert.assertThrows(DaoException.class, () -> {
            dao.get(ID);
        });
    }

    @Test
    public void given_url_to_persist_When_create_Then_return_persisted_url_with_generated_id() throws DaoException {
        doReturn(ID).when(url).getId();
        doNothing().when(em).persist(any());
        assertEquals(ID, dao.create(url).getId());
    }

    @Test
    public void when_create_throws_PersistenceException_then_rethrow_DaoException() throws DaoException {
        doThrow(PersistenceException.class).when(em).persist(any());
        Assert.assertThrows(DaoException.class, () -> {
            dao.create(url);
        });
    }

    @Test
    public void when_create_throws_IllegalArgumentException_then_rethrow_DaoException() throws DaoException {
        doThrow(IllegalArgumentException.class).when(em).persist(any());
        Assert.assertThrows(DaoException.class, () -> {
            dao.create(url);
        });
    }
}
