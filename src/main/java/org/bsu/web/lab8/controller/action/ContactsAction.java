package org.bsu.web.lab8.controller.action;

import org.bsu.web.lab8.controller.NavigationConstants;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ContactsAction implements Action {
    @Override
    public String getPattern() {
        return "contacts";
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
        RequestDispatcher dispatcher =
                servletContext.getRequestDispatcher(NavigationConstants.contactPage);
        dispatcher.forward(request, response);
    }
}
