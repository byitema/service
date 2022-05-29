package org.bsu.web.lab8.controller.action;

import org.bsu.web.lab8.controller.NavigationConstants;
import org.bsu.web.lab8.model.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ChatAction implements Action {

    @Override
    public String getPattern() {
        return "chat";
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        request.setAttribute("senderId", user.getNickname());
        request.setAttribute("isAdmin", user.isAdmin());
        RequestDispatcher dispatcher =
                servletContext.getRequestDispatcher(NavigationConstants.chatPage);
        dispatcher.forward(request, response);
    }
}
