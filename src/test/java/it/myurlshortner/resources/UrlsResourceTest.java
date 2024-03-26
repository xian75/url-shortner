/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package it.myurlshortner.resources;

import it.myurlshortner.exception.FacadeException;
import it.myurlshortner.facade.UrlsFacade;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
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
public class UrlsResourceTest {

    private static final String SHORT_URL = "dnh";
    private static final String LONG_URL = "https://www.payroc.com/";

    @Spy
    @InjectMocks
    UrlsResource resource;

    @Mock
    UrlsFacade facade;

    @Before
    public void init() throws FacadeException {
        MockitoAnnotations.openMocks(this);
        doReturn(LONG_URL).when(facade).get(any());
    }

    @Test
    public void given_existant_shortUrl_When_get_Then_return_its_longUrl() {
        Assert.assertEquals(LONG_URL, resource.get(SHORT_URL).getEntity());
    }

    @Test
    public void given_existant_shortUrl_When_get_Then_return_200_status_code() {
        Assert.assertEquals(200, resource.get(SHORT_URL).getStatus());
    }

    @Test
    public void given_unexistant_shortUrl_When_get_Then_return_204_status_code() throws FacadeException {
        doReturn(null).when(facade).get(any());
        Assert.assertEquals(204, resource.get(SHORT_URL).getStatus());
    }

    @Test
    public void when_get_throws_FacadeException_then_return_500_status_code() throws FacadeException {
        doThrow(FacadeException.class).when(facade).get(any());
        Assert.assertEquals(500, resource.get(SHORT_URL).getStatus());
    }

    @Test
    public void given_longUrl_When_set_Then_return_200_status_code() {
        Assert.assertEquals(200, resource.set(LONG_URL).getStatus());
    }

    @Test
    public void when_set_throws_FacadeException_then_return_500_status_code() throws FacadeException {
        doThrow(FacadeException.class).when(facade).set(any());
        Assert.assertEquals(500, resource.set(LONG_URL).getStatus());
    }
}
