package com.P.Server.app;

import com.P.common.Message;

import java.util.HashMap;

public class ServerConnectionController {
    public static Message handleCommand(Message command) {
//        if (command.getType() == Message.MessageType.command) {
//            HashMap<String, Object> body = new HashMap<>();
//            String name = command.getFromBody("name");
//            int age = command.getIntFromBody("age");
//            body.put("result", name + " in 4 years: " + (age + 4));
//            return new Message(body, Message.MessageType.response);
//        }
//        HashMap<String, Object> body = new HashMap<>();
//        String name = command.getFromBody("name");
//        int age = command.getIntFromBody("age");
//        body.put("result", name + " 4 years ago: " + (age - 4));
//        return new Message(body, Message.MessageType.response);
        return null;
    }
}
