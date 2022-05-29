package org.bsu.web.lab8.controller.tag;

import org.bsu.web.lab8.model.dao.DAOException;
import org.bsu.web.lab8.model.dao.domain.OrderDao;
import org.bsu.web.lab8.model.entity.Order;
import org.bsu.web.lab8.model.entity.OrderPosition;

import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class TableTag extends SimpleTagSupport {
    private List<Order> orders;
    private boolean isAdmin;

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public void getTableHeader(StringBuilder stringBuilder) {
        Locale locale = (Locale) getJspContext()
                .getAttribute("userLocale", PageContext.SESSION_SCOPE);
        ResourceBundle bundle = ResourceBundle.getBundle("bundle", locale);

        stringBuilder
                .append("<th>ID</th>")
                .append(String.format("<th>%s</th>", bundle.getString("table.date")))
                .append(String.format("<th>%s</th>", bundle.getString("table.isConfirmed")))
                .append(String.format("<th>%s</th>", bundle.getString("table.isPayed")))
                .append(String.format("<th>%s</th>", bundle.getString("table.clientID")))
                .append(String.format("<th>%s</th>", bundle.getString("table.adminID")))
                .append(String.format("<th>%s</th>", bundle.getString("table.positions")))
                .append(String.format("<th>%s</th>", bundle.getString("table.cost")));

        if (isAdmin) {
            stringBuilder.append(String.format("<th>%s</th>", bundle.getString("table.confirm")));
        }
    }


    public void getOrderRow(Order order, StringBuilder stringBuilder) {
        stringBuilder.append(String.format("<td>%d</td>", order.getId()));
        stringBuilder.append(String.format("<td>%s</td>", order.getOrderDate()));
        stringBuilder.append(String.format("<td>%s</td>", order.isConfirmed()));
        stringBuilder.append(String.format("<td>%s</td>", order.isPayed()));
        stringBuilder.append(String.format("<td>%d</td>", order.getClient().getId()));
        stringBuilder.append(String.format("<td>%d</td>", order.getAdmin().getId()));

        StringBuilder positionsSB = new StringBuilder();
        for (OrderPosition position : order.getPositions()) {
            positionsSB.append(String.format("%s: %s\n", position.getPosition().getItemName(), position.getAmount()));
        }

        stringBuilder.append(String.format("<td>%s</td>", positionsSB));

        try {
            OrderDao orderDao = (OrderDao) getJspContext()
                    .getAttribute("orderDao", PageContext.APPLICATION_SCOPE);
            stringBuilder.append(String.format("<td>%s</td>", orderDao.getOrderCost(order.getId())));
        } catch (DAOException ignored) {

        }


        if (isAdmin && !order.isConfirmed()) {
            PageContext pageContext = (PageContext) getJspContext();
            String action = pageContext.getServletContext().getContextPath() + "/serv";

            Locale locale = (Locale) getJspContext()
                    .getAttribute("userLocale", PageContext.SESSION_SCOPE);
            ResourceBundle bundle = ResourceBundle.getBundle("bundle", locale);


            stringBuilder.append("<td>");
            stringBuilder
                    .append(String.format("<form method=\"post\" action=\"%s\">", action))
                    .append("<input type=\"hidden\" ")
                    .append(String.format("name=\"orderID\" value=\"%d\"/>", order.getId()))
                    .append("<input type=\"hidden\" name=\"action\" value=\"confirmOrder\"/>")
                    .append(
                            String.format("<button type=\"submit\" class=\"btn btn-primary\">%s</button>",
                                    bundle.getString("table.confirm")))
                    .append("</form>");
            stringBuilder.append("</td>");
        }
    }

    @Override
    public void doTag() throws IOException {
        if (orders == null) {
            return;
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<table class=\"table table-bordered\">").append("<thead>").append("<tr>");
        getTableHeader(stringBuilder);
        stringBuilder.append("</tr>").append("</thead>").append("<tbody>");

        for (Order order : orders) {
            stringBuilder.append("<tr>");
            getOrderRow(order, stringBuilder);
            stringBuilder.append("</tr>");
        }

        stringBuilder.append("</tbody>").append("</table>");

        getJspContext().getOut().write(stringBuilder.toString());
        getJspContext().getOut().flush();
    }

}
