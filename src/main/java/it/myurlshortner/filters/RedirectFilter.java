/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Filter.java to edit this template
 */
package it.myurlshortner.filters;

import it.myurlshortner.exception.FacadeException;
import it.myurlshortner.facade.UrlsFacade;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author NATCRI
 */
@WebFilter(filterName = "RedirectFilter", urlPatterns = {"/redirect/*"})
public class RedirectFilter implements Filter {

    @Inject
    private UrlsFacade facade;

    /**
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are creating
     * @param chain The filter chain we are processing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        String shortUrl = ((HttpServletRequest) request).getServletPath()
                .replace("/redirect", "").replaceFirst("/", "");
        try {
            String longUrl = facade.get(shortUrl);
            if (longUrl != null) {
                ((HttpServletResponse) response).sendRedirect(longUrl);
                return;
            }
        } catch (FacadeException ex) {
            Logger.getLogger(RedirectFilter.class.getName()).log(Level.WARNING, null, ex);
        }

        try {
            chain.doFilter(request, response);
        } catch (Throwable ex) {
            Logger.getLogger(RedirectFilter.class.getName()).log(Level.WARNING, null, ex);
        }
    }

}
