package com.P.Server.controller;

import com.P.Server.model.Repo.UserRepo;
import com.P.common.Message;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;
import com.P.common.model.Basics.Player;
import com.P.common.model.Basics.User;
import com.P.common.model.Maps.Farm;
import com.P.common.model.Maps.Position;
import com.P.common.model.Maps.Tile;
import com.P.common.model.Objects.Animal;
import com.P.common.model.Objects.Barn;
import com.P.common.model.Resualt;
import com.P.common.model.enums.AnimalType;
import com.P.common.model.enums.BarnType;
import com.P.common.model.game.GameModel;
import com.P.common.model.game.VillageModel;

import java.util.ArrayList;
import java.util.HashMap;

public class TurnServer {
    public static Message handleCommand(Message command) {
        System.out.println("in TurnServer, handle command");
        String request = command.getFromBody("request");
        Resualt resualt = null;

        HashMap<String, Object> body = new HashMap<>();
        body.put("is", resualt.isAccept() ? "true" : "false");
        body.put("ok", resualt.getAnswer());
        return new Message(body, Message.MessageType.response);
    }

}
