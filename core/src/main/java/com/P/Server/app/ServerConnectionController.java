package com.P.Server.app;

import com.P.Server.controller.*;
import com.P.common.Message;


public class ServerConnectionController {
    public static Message handleCommand(Message command) {
        String controller = command.getFromBody("controller");
        if (controller.equals("RegisterController")) {
            return RegisterController.handleCommand(command);
        }
        return null;
    }
}
