package org.bsu.web.lab8.controller;

import org.bsu.web.lab8.controller.action.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@WebServlet
public class MainServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final Map<String, Action> actions;

    public MainServlet() {
        super();
        this.actions = new HashMap<>();
    }

    @Override
    public void init() {
        Action[] actions = {
                new ChatAction(),
                new ClientOrdersAction(),
                new ConfirmedOrdersAction(),
                new ConfirmOrderAction(),
                new IndexAction(),
                new LoginAction(),
                new MakeOrderAction(),
                new OpenClientOrdersAction(),
                new OpenConfirmOrderAction(),
                new OpenLoginAction(),
                new OpenMakeOrderAction(),
                new OrdersAction(),
                new PositionsAction(),
                new SignUpAction(),
                new UsersAction(),
                new ContactsAction()
        };
        for (Action c : actions) {
            this.actions.put(c.getPattern(), c);
        }

        getServletContext().setAttribute("userLocale", Locale.forLanguageTag("ru"));
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            actions.get("index").execute(request, response, this.getServletContext());
        } else if (actions.containsKey(action)) {
            actions.get(action).execute(request, response, this.getServletContext());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            actions.get("index").execute(request, response, this.getServletContext());
        } else if (actions.containsKey(action)) {
            actions.get(action).execute(request, response, this.getServletContext());
        }
    }

}
