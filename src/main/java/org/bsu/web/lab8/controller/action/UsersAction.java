package org.bsu.web.lab8.controller.action;

import org.bsu.web.lab8.controller.NavigationConstants;
import org.bsu.web.lab8.model.dao.DAOException;
import org.bsu.web.lab8.model.dao.domain.UserDao;
import org.bsu.web.lab8.model.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UsersAction implements Action {
    @Override
    public String getPattern() {
        return "users";
    }

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response,
                        ServletContext servletContext)
            throws ServletException, IOException {
        RequestDispatcher dispatcher =
                servletContext.getRequestDispatcher(NavigationConstants.usersPage);
        UserDao userDao = (UserDao) servletContext.getAttribute("userDao");
        try {
            List<User> list = userDao.getAll();
            request.setAttribute("users", list);
        } catch (DAOException e) {
            dispatcher =
                    servletContext.getRequestDispatcher(NavigationConstants.errorPage);

            request.setAttribute("exception",
                    new ServletException("Error while getting users list", e));

            dispatcher.forward(request, response);
            return;
        }
        dispatcher.forward(request, response);
    }
}
