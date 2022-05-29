package org.bsu.web.lab8.controller.action;

import org.bsu.web.lab8.controller.NavigationConstants;
import org.bsu.web.lab8.model.dao.domain.UserDao;
import org.bsu.web.lab8.model.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SignUpAction implements Action {
    @Override
    public String getPattern() {
        return "signup";
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext) throws ServletException, IOException {
        RequestDispatcher dispatcher =
                request.getRequestDispatcher(NavigationConstants.loginPage);

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && password != null) {
            if (username.isEmpty() || password.isEmpty()) {
                throw new ServletException("Password and username should not be empty");
            }

            try {
                UserDao userDao = (UserDao) servletContext.getAttribute("userDao");

                List<User> userList = userDao.getAll();

                if (userList.stream()
                        .map(User::getNickname)
                        .anyMatch(user1 -> user1.equals(username))) {
                    throw new ServletException("User with name " + username + " already exist!");
                }

                User newUser = new User();
                newUser.setAdmin(false);
                newUser.setNickname(username);
                newUser.setPassword(password);

                userDao.persist(newUser);

            } catch (Exception e) {
                dispatcher =
                        servletContext.getRequestDispatcher(NavigationConstants.errorPage);

                request.setAttribute("exception",
                        new ServletException("Error while signup", e));

                dispatcher.forward(request, response);
            }

        }

        dispatcher.forward(request, response);

    }
}
