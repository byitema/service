package org.bsu.web.lab8.controller.action;

import org.bsu.web.lab8.controller.NavigationConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OpenClientOrdersAction implements Action {
    @Override
    public String getPattern() {
        return "openClientOrders";
    }

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response,
                        ServletContext servletContext)
            throws ServletException, IOException {
        RequestDispatcher dispatcher =
                servletContext.getRequestDispatcher(NavigationConstants.clientOrdersPage);
        dispatcher.forward(request, response);
    }

}
