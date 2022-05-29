package org.bsu.web.lab8.controller.action;

import org.bsu.web.lab8.controller.NavigationConstants;
import org.bsu.web.lab8.model.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OpenLoginAction implements Action {
    @Override
    public String getPattern() {
        return "openLogin";
    }

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response,
                        ServletContext servletContext)
            throws ServletException, IOException {
        RequestDispatcher dispatcher =
                servletContext.getRequestDispatcher(NavigationConstants.loginPage);

        User sessionUser = (User) request.getSession().getAttribute("user");
        if (sessionUser != null) {
            request.getSession().setAttribute("usertype", null);
            request.getSession().setAttribute("user", null);
        }
        dispatcher.forward(request, response);
    }
}