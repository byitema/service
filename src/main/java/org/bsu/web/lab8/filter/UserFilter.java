package org.bsu.web.lab8.filter;

import org.bsu.web.lab8.controller.NavigationConstants;
import org.bsu.web.lab8.model.entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class UserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String action = (servletRequest).getParameter("action");
        if (action != null && (action.equals("login") || action.equals("signup"))) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        User user = (User) ((HttpServletRequest) servletRequest).getSession().getAttribute("user");
        if (user == null) {
            RequestDispatcher requestDispatcher =
                    servletRequest.getRequestDispatcher(NavigationConstants.loginPage);
            requestDispatcher.forward(servletRequest, servletResponse);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
