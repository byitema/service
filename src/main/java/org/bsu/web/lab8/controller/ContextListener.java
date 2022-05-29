package org.bsu.web.lab8.controller;

import org.bsu.web.lab8.model.dao.domain.OrderDao;
import org.bsu.web.lab8.model.dao.domain.PositionDao;
import org.bsu.web.lab8.model.dao.domain.UserDao;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ContextListener implements ServletContextListener {

    private OrderDao orderDao = new OrderDao();

    private PositionDao positionDao = new PositionDao();

    private UserDao userDao = new UserDao();


    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        context.setAttribute("orderDao", this.orderDao);
        context.setAttribute("positionDao", this.positionDao);
        context.setAttribute("userDao", this.userDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}