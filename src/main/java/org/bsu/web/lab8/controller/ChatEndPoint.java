package org.bsu.web.lab8.controller;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/chat")
public class ChatEndPoint {
    @OnMessage
    public void onMessage(Session session, String msg) {
        if (!session.getUserProperties().containsKey("sender") ||
                !session.getUserProperties().containsKey("isAdmin")) {
            String[] someInfo = msg.split(":=:");
            session.getUserProperties().put("sender", someInfo[1]);
            session.getUserProperties().put("isAdmin", someInfo[2]);
        } else {
            String sender = (String) session.getUserProperties().get("sender");
            boolean isAdmin = Boolean.parseBoolean((String) session.getUserProperties().get("isAdmin"));
            String receiverName;
            String message;
            if (isAdmin) {
                String[] someInfo = msg.split(":=:");
                receiverName = someInfo[0];
                message = someInfo[1];
            } else {
                receiverName = "admin";
                message = msg;
            }

            for (Session sessionReceiver : session.getOpenSessions()) {
                if (sessionReceiver.isOpen()) {
                    if (sessionReceiver.getUserProperties().containsKey("sender")) {
                        String sessionSenderName = (String) sessionReceiver.getUserProperties().get("sender");
                        if (sessionSenderName.equals(receiverName)) {
                            try {
                                sessionReceiver.getBasicRemote().sendText(sender + ": " + message);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

    }
}
