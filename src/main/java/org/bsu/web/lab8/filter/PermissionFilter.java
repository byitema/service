package org.bsu.web.lab8.filter;

import org.bsu.web.lab8.controller.NavigationConstants;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Set;

public class PermissionFilter implements Filter {
    public static final String GUEST_PARAM = "guest-actions";
    public static final String ADMIN_PARAM = "admin-actions";
    public static final String NULL_PARAM = "null-actions";

    private Set<String> guestAction;
    private Set<String> adminActions;
    private Set<String> nullActions;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        ActionsReader reader = ActionsReader.getFrom(filterConfig);

        guestAction = reader.getActions(GUEST_PARAM);
        adminActions = reader.getActions(ADMIN_PARAM);
        nullActions = reader.getActions(NULL_PARAM);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws
            IOException,
            ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String incoming = httpServletRequest.getParameter("action");

        if (incoming == null) {
            chain.doFilter(request, response);
            return;
        }

        String userType = (String) httpServletRequest.getSession().getAttribute("usertype");

        if (userType == null) {
            if (!nullActions.contains(incoming)) {
                permissionViolated(request, response);
            } else {
                chain.doFilter(request, response);
            }
            return;
        }

        if (userType.equals("guest") && !guestAction.contains(incoming)) {
            permissionViolated(request, response);
            return;
        }

        if (!userType.equals("admin") && adminActions.contains(incoming)) {
            permissionViolated(request, response);
            return;
        }

        chain.doFilter(request, response);
    }

    void permissionViolated(ServletRequest request, ServletResponse response) {
        try {
            request.setAttribute("exception",
                    new ServletException("Access denied. You do not have enough permissions"));
            request
                    .getRequestDispatcher(NavigationConstants.errorPage)
                    .forward(request, response);
        } catch (ServletException | IOException ignored) {
        }
    }
}
