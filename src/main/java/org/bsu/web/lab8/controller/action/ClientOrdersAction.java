package org.bsu.web.lab8.controller.action;


import org.bsu.web.lab8.controller.NavigationConstants;
import org.bsu.web.lab8.model.dao.DAOException;
import org.bsu.web.lab8.model.dao.domain.OrderDao;
import org.bsu.web.lab8.model.dao.domain.PositionDao;
import org.bsu.web.lab8.model.dao.domain.UserDao;
import org.bsu.web.lab8.model.entity.Order;
import org.bsu.web.lab8.model.entity.Position;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ClientOrdersAction implements Action {

    @Override
    public String getPattern() {
        return "clientOrders";
    }

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response,
                        ServletContext servletContext)
            throws ServletException, IOException {
        RequestDispatcher dispatcher =
                servletContext.getRequestDispatcher(NavigationConstants.clientOrdersPage);

        String clientId = request.getParameter("clientID");
        if (clientId != null) {
            UserDao userDao = (UserDao) servletContext.getAttribute("userDao");

            PositionDao positionDao = (PositionDao) servletContext.getAttribute("positionDao");
            OrderDao orderDao = (OrderDao) servletContext.getAttribute("orderDao");

            List<Order> clientOrders;
            List<Position> positions;
            try {
                clientOrders = orderDao.getOrders(Integer.parseInt(clientId));
                positions = positionDao.getAll();
            } catch (DAOException e) {
                dispatcher =
                        servletContext.getRequestDispatcher(NavigationConstants.errorPage);

                request.setAttribute("exception",
                        new ServletException("Error while getting client's orders", e));

                dispatcher.forward(request, response);
                return;
            }

            request.setAttribute("clientOrders", clientOrders);
            request.setAttribute("positions", positions);
        }
        dispatcher.forward(request, response);
    }

}
