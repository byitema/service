package org.bsu.web.lab8.controller.action;

import org.bsu.web.lab8.controller.NavigationConstants;
import org.bsu.web.lab8.model.dao.DAOException;
import org.bsu.web.lab8.model.dao.domain.PositionDao;
import org.bsu.web.lab8.model.entity.Position;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PositionsAction implements Action {

    @Override
    public String getPattern() {
        return "positions";
    }

    @Override
    public void execute(HttpServletRequest request,
                        HttpServletResponse response,
                        ServletContext servletContext)
            throws ServletException, IOException {
        RequestDispatcher dispatcher =
                servletContext.getRequestDispatcher(NavigationConstants.positionsPage);
        PositionDao positionDao = (PositionDao) servletContext.getAttribute("positionDao");
        try {
            List<Position> list = positionDao.getAll();
            request.setAttribute("positionsList", list);
        } catch (DAOException e) {
            dispatcher =
                    servletContext.getRequestDispatcher(NavigationConstants.errorPage);

            request.setAttribute("exception",
                    new ServletException("Error while getting positions list", e));

            dispatcher.forward(request, response);
            return;
        }

        dispatcher.forward(request, response);
    }

}
