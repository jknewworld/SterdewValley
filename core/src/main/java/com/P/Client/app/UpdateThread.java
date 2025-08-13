package com.P.Client.app;

import com.P.common.Message;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Player;
import com.P.common.model.Maps.Position;
import com.P.Client.model.Pair;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;

//public class UpdateThread extends Thread {
//    private AtomicBoolean end;
//
//    public UpdateThread() {
//        this.end = new AtomicBoolean(false);
//    }
//
//    private void updateServer() {
//        System.out.println("updating server");
//        HashMap<String, Object> body = new HashMap<>();
//        int turn = ClientApp.getTurnNumber();
//        Player player = App.getLoggedInUser().getCurrentGame().getCurrentPlayer();
//
//        if(player.isInVillage()) {
//            body.put("isInVillage" + turn, "true");
//            Pair<Float, Float> position = player.getPlayerPosition();
//            body.put("playerF" + turn, position.first);
//            body.put("playerS" + turn, position.second);
//        }
//
//        if((System.currentTimeMillis() - player.getReactionTime()) < 1000)
//            body.put("reaction" + turn, player.getReaction());
//        ClientApp.getServerConnection().sendMessage(new Message(body, Message.MessageType.update));
//    }
//
//    @Override
//    public void run() {
//        while (!end.get()) {
//            updateServer();
//            try {
//                Thread.sleep(5 * 1000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        }
//    }
//
//    public void end() {
//        end.set(true);
//    }
//}
