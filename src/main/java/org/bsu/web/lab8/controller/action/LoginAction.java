package org.bsu.web.lab8.controller.action;

import org.bsu.web.lab8.controller.NavigationConstants;
import org.bsu.web.lab8.model.dao.DAOException;
import org.bsu.web.lab8.model.dao.domain.UserDao;
import org.bsu.web.lab8.model.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

public class LoginAction implements Action {
    @Override
    public String getPattern() {
        return "login";
    }

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response,
                        ServletContext servletContext) throws ServletException, IOException {
        RequestDispatcher dispatcher;
        User user;

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (username != null && password != null) {
            if (username.isEmpty() && password.isEmpty()) {
                startNewSessionAndSaveCookies(request, response, "guest", null);
            } else {
                UserDao userDao = (UserDao) servletContext.getAttribute("userDao");
                try {
                    user = userDao.getUserByUsernameAndPassword(username, password);
                } catch (DAOException e) {
                    dispatcher =
                            servletContext.getRequestDispatcher(NavigationConstants.errorPage);

                    request.setAttribute("exception",
                            new ServletException("Wrong username and/or password", e));

                    dispatcher.forward(request, response);
                    return;
                }

                startNewSessionAndSaveCookies(request, response, user.isAdmin() ? "admin" : "user", user);
            }
            dispatcher = servletContext.getRequestDispatcher(NavigationConstants.indexPage);
            dispatcher.forward(request, response);
        }
    }


    private void startNewSessionAndSaveCookies(HttpServletRequest request,
                                               HttpServletResponse response,
                                               String usertype,
                                               User user) {
        HttpSession session = request.getSession(true);
        session.setAttribute("usertype", usertype);
        session.setAttribute("user", user);
        Cookie lastEnterTime = null;
        try {
            lastEnterTime = new Cookie("lastEnterTime", URLEncoder.encode(new Date().toString(), "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return;
        }
        lastEnterTime.setComment("Date of last use by current user");
        Cookie usageCount = new Cookie("usageCount", "1");
        usageCount.setComment("Number of visits of the resource");

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("usageCount")) {
                    int lastUsageCount = Integer.parseInt(cookie.getValue());
                    lastUsageCount += 1;
                    usageCount.setValue(Integer.toString(lastUsageCount));
                }
            }
        }
        response.addCookie(lastEnterTime);
        response.addCookie(usageCount);
    }

}