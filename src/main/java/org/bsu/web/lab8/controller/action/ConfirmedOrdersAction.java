package org.bsu.web.lab8.controller.action;

import org.bsu.web.lab8.controller.NavigationConstants;
import org.bsu.web.lab8.model.dao.DAOException;
import org.bsu.web.lab8.model.dao.domain.OrderDao;
import org.bsu.web.lab8.model.entity.Order;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ConfirmedOrdersAction implements Action {
    @Override
    public String getPattern() {
        return "confirmedOrders";
    }

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response,
                        ServletContext servletContext)
            throws ServletException, IOException {
        RequestDispatcher dispatcher =
                servletContext.getRequestDispatcher(NavigationConstants.confirmedOrdersPage);

        OrderDao orderDao = (OrderDao) servletContext.getAttribute("orderDao");
        try {
            List<Order> confirmedOrders = orderDao.getOrders(true);
            request.setAttribute("confirmedOrders", confirmedOrders);
        } catch (DAOException e) {
            dispatcher =
                    servletContext.getRequestDispatcher(NavigationConstants.errorPage);

            request.setAttribute("exception",
                    new ServletException("Error while getting confirmed orders list", e));

            dispatcher.forward(request, response);
            return;
        }

        dispatcher.forward(request, response);
    }

}
