package org.bsu.web.lab8.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Locale;

public class LanguageFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        if (request.getSession().getAttribute("userLocale") == null) {
            request.getSession().setAttribute("userLocale", request.getLocale());
        }

        String language = request.getParameter("language");
        if (language != null) {
            request.getSession().setAttribute("userLocale", new Locale(language));
        }

        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {
    }
}
