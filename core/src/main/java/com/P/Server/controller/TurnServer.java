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
    public static boolean isWaitingForChoosingMap = true;
    public static String newUser = "Shayan1";

    public static Resualt handleNewGame() {
        ArrayList<Player> players = new ArrayList<>();
        System.out.println("handlllllllllllll new game");
        System.out.println(App.getCurrentLobby());
        System.out.println(App.getCurrentLobby().getPlayers());
        for(User user : App.getCurrentLobby().getPlayers())
            players.add(new Player(user));

        System.out.println("handlllllllllllll new game22222222222");
        Game game = new Game(players, players.get(0));
        System.out.println("handlllllllllllll new game3333333333333333333");
        App.getCurrentLobby().setGame(game);
        System.out.println("handlllllllllllll new game444444444444444444444444444");
        isWaitingForChoosingMap = true;

        return new Resualt(true, "The game has been made successfully. Awaiting each user's map choice...\n" +
            "Use 'game map <map_number>' to pick map 1 or 2.");
    }

    public static Message handleCommand(Message command) {
        System.out.println("handle command: SERVER");
        String request = command.getFromBody("request");
        Resualt resualt = null;
        if (request.equals("handleMapSelection")) {
            resualt = handleMapSelection(command);
        }
        HashMap<String, Object> body = new HashMap<>();
        body.put("is", resualt.isAccept() ? "true" : "false");
        body.put("ok", resualt.getAnswer());
        return new Message(body, Message.MessageType.response);
    }

    public static Resualt handleMapSelection(Message command) {
        System.out.println("handle map selection: SERVER");
        String username = command.getFromBody("username");
        System.out.println("between");
        int mapNumber = command.getIntFromBody("mapNumber");

        System.out.println(App.getCurrentLobby()+ " nulll?");

        User user =  UserRepo.findUserByUsername(username);

        Game game = App.getCurrentLobby().getGame();
        Player player = game.getPlayerByName(username);
        player.setFarmNum(mapNumber);
        Farm farm = Farm.makeFarm(mapNumber);


        game.getMap().addFarm(farm);
        player.setFarm(farm);
        player.setGameModel(new GameModel(50, 75));
        game.setVillageModel(new VillageModel(50,75));

        System.out.println("handle map selection: SERVER ENDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
        // DEBUG2

        BarnType barnType = BarnType.BigBarn;
        int width = barnType.getWidth();
        int length = barnType.getLength();
        ArrayList<Tile> floor = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < length; j++) {
                Position position = new Position(5 + i, 5 + j);
                Tile tile = player.getFarm().findCellByCoordinate(position.getX(), position.getY());
                floor.add(tile);
            }
        }

        Barn barn = new Barn(floor, barnType);
        player.getFarm().getBuildings().add(barn);
        Position position = new Position(2, 2);
        Animal animal   = new Animal(AnimalType.Sheep, " Rose",position);
        animal.setTiles(farm.getCells());
        barn.getAnimals().add(animal);

        System.out.println("handle map selection: SERVER ENDDDDDDDDDDDDDDDDDDDDDDDDDDDD");
        return new Resualt(true, "game successfully created!");
    }

}
