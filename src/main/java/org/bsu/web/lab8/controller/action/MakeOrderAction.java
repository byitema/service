package org.bsu.web.lab8.controller.action;

import org.bsu.web.lab8.controller.NavigationConstants;
import org.bsu.web.lab8.model.dao.DAOException;
import org.bsu.web.lab8.model.dao.domain.OrderDao;
import org.bsu.web.lab8.model.dao.domain.PositionDao;
import org.bsu.web.lab8.model.dao.domain.UserDao;
import org.bsu.web.lab8.model.entity.Order;
import org.bsu.web.lab8.model.entity.OrderPosition;
import org.bsu.web.lab8.model.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

public class MakeOrderAction implements Action {
    @Override
    public String getPattern() {
        return "makeOrder";
    }

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext)
            throws ServletException, IOException {
        RequestDispatcher dispatcher =
                servletContext.getRequestDispatcher(NavigationConstants.makeOrderPage);

        String clientID = request.getParameter("clientID");

        if (clientID != null) {
            try {
                User sessionUser = (User) request.getSession().getAttribute("user");
                PositionDao positionDao =
                        (PositionDao) servletContext.getAttribute("positionDao");
                int maxPositions = positionDao.getAll().size();

                Map<Integer, String[]> positionValues = new HashMap<>();
                for (int i = 0; i < maxPositions; ++i) {
                    String[] values = request.getParameterValues("position" + i);
                    if (values != null) {
                        positionValues.put(i + 1, values);
                    }
                }

                UserDao userDao = (UserDao) servletContext.getAttribute("userDao");
                OrderDao orderDao = (OrderDao) servletContext.getAttribute("orderDao");

                Order order = new Order();

                List<OrderPosition> orderPositions = new ArrayList<>();

                order.setOrderDate(new Date(System.currentTimeMillis()));
                order.setAdmin(sessionUser);
                order.setConfirmed(false);
                order.setPayed(false);
                order.setClient(userDao.getByPK(Integer.parseInt(clientID)));
                order = orderDao.persist(order);

                for (int i : positionValues.keySet()) {
                    OrderPosition orderPosition = new OrderPosition();
                    orderPosition.setOrder(order);
                    orderPosition.setPosition(positionDao.getByPK(i));
                    orderPosition.setAmount(Arrays
                            .stream(positionValues.get(i))
                            .mapToInt(Integer::parseInt)
                            .sum());
                    orderPositions.add(orderPosition);
                }
                order.setPositions(orderPositions);
                orderDao.update(order);

            } catch (DAOException e) {
                dispatcher =
                        servletContext.getRequestDispatcher(NavigationConstants.errorPage);

                request.setAttribute("exception",
                        new ServletException("Error while making order", e));

                dispatcher.forward(request, response);
                return;
            }
        }
        dispatcher.forward(request, response);
    }
}

