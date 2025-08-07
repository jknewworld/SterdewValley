package com.P.Server.app;

import com.P.Server.controller.*;
import com.P.common.Message;


public class ServerConnectionController {
    public static Message handleCommand(Message command) {
        String controller = command.getFromBody("controller");
        System.out.println("controller found");
        if (controller.equals("RegisterController")) {
            System.out.println("it's register controller");
            return RegisterController.handleCommand(command);
        }
        return null;
    }
}
