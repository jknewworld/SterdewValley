package com.P.Client.controller;

import com.P.Client.app.ClientApp;
//import com.P.Client.app.UpdateThread;
import com.P.Main;
import com.P.common.Message;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.Game;
import com.P.common.model.Basics.Player;
import com.P.common.model.Basics.User;
import com.P.Client.model.Command;
import com.P.common.model.Maps.Farm;
import com.P.common.model.Maps.Position;
import com.P.common.model.Maps.Tile;
import com.P.common.model.Objects.Animal;
import com.P.common.model.Objects.Barn;
import com.P.Server.model.Repo.GameRepo;
import com.P.Server.model.Repo.UserRepo;
import com.P.common.model.Resualt;
import com.P.common.model.enums.*;
import com.P.common.model.game.GameModel;
import com.P.common.model.game.VillageModel;
import com.P.Client.view.AppView;
import com.P.Client.view.PlayGame;
import com.P.Client.view.PreGameView.NewGameView;
import com.P.Client.view.PreGameView.PreGameView;
import com.P.Client.controller.game.GameController;

import java.util.*;

import static com.P.Client.app.ClientApp.TIMEOUT_MILLIS;

public class TurnController extends ControllersController {
    public static boolean isWaitingForChoosingMap = true;
    private PreGameView view;
    public  NewGameView view2;

    public void setView(PreGameView view) {
        this.view = view;
    }

    public void setView(NewGameView view) {
        this.view2 = view;
    }

    public static Resualt handleNewGame() {
        ClientApp.setStartGame(true);
        HashMap<String, Object> body = new HashMap<>();
//        body.put("controller", "TurnController");
//        body.put("request", "handleNewGame");
//        body.put("username", App.getLoggedInUser().getUsername());
        //System.out.println("sending command to get turn");
       // Message response = sendCommand(new Message(body, Message.MessageType.command));
        //System.out.println("got it! it's: " + response.getIntFromBody("turnNumber"));
        int turNumber = (App.getLoggedInUser().getAvatarId() == 5) ? 1 : 0;
        ClientApp.setTurnNumber(turNumber);
        List<Integer> avatars = new ArrayList<>();
        List<String> names = new ArrayList<>();
        List<String> nicknames = new ArrayList<>();
        avatars.add(4);
        avatars.add(5);
        avatars.add(1);
        avatars.add(1);
        nicknames.add("May");
        nicknames.add("Jack");
        nicknames.add("Shawn");
        nicknames.add("Rose");
        names.add("Merry");
        names.add("Jerry");
        names.add("Rose");
        names.add("Sam");
        //System.out.println("my turn : " + turNumber);
        Player player = new Player(App.getLoggedInUser());
        ArrayList<Player> players = new ArrayList<>();
        //System.out.println("#1");
        for (int i = 0; i < 4; i++) {
            //System.out.println("* " + i);
            if(i == ClientApp.getTurnNumber())
                players.add(player);
            else {
                Avatar avatar = switch (avatars.get(i)) {
                    case 1 -> Avatar.ELLIOTT;
                    case 2 -> Avatar.HALEY;
                    case 3 -> Avatar.LEAH;
                    case 4 -> Avatar.ROBIN;
                    case 5 -> Avatar.SEBASTIAN;
                    case 6 -> Avatar.SHANE;
                    default -> null;
                };
                //System.out.println("@@");
                players.add(new Player(new User("male", "a", nicknames.get(i), "c", names.get(i), avatar)));
                //System.out.println("first position: " + players.get(i).getPlayerPosition());
            }
        }
        //System.out.println("it's number " + ClientApp.getTurnNumber() + " setting my game!");
        App.getLoggedInUser().setCurrentGame(new Game(players, player));
       // System.out.println("game set");
        //System.out.println(App.getLoggedInUser().getCurrentGame().getCurrentPlayer().getUser().getUsername());
        return new Resualt(true, "game successfully created");
    }

    int cheakNum = 0;
    public Resualt handleMapSelection() {
        //System.out.println("in handleMApSelection");
        int mapNumber = Integer.parseInt(view2.getMap1().getText());
        if(mapNumber != 1 && mapNumber != 2) {
            return new Resualt(false, "Invalid map number");
        }

        //System.out.println("I'm " + ClientApp.getTurnNumber() + " with map " + mapNumber);

//        HashMap<String, Object> body = new HashMap<>();
//        body.put("request", "handleMapSelection");
//        body.put("turnNumber", ClientApp.getTurnNumber());
//        body.put("mapNumber", mapNumber);
//        informServer(new Message(body, Message.MessageType.update));
//        System.out.println("server informed");

        User user = App.getLoggedInUser();
        //System.out.println("my name is " + user.getUsername());
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        player.setFarmNum(mapNumber);
        Farm farm = Farm.makeFarm(mapNumber);

        game.getMap().addFarm(farm);
        player.setFarm(farm);
        player.setGameModel(new GameModel(50, 75, ClientApp.getTurnNumber()));
        for(int i = 0; i < 4; i++)
            if(i != ClientApp.getTurnNumber()) {
                Player player1 = game.getPlayers().get(i);
                player1.setFarmNum(1);
                Farm farm1 = Farm.makeFarm(2);

                game.getMap().addFarm(farm1);
                player1.setFarm(farm1);
                player1.setGameModel(new GameModel(50, 75, i));
            }
        game.setVillageModel(new VillageModel(50,75));
        //System.out.println("my game is completely set!");

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


        App.getLoggedInUser().getCurrentGame().getNpcs().get(0).setTiles(farm.getCells());
        App.getLoggedInUser().getCurrentGame().getNpcs().get(3).setTiles(farm.getCells());
        App.getLoggedInUser().getCurrentGame().getNpcs().get(1).setTiles(farm.getCells());
        App.getLoggedInUser().getCurrentGame().getNpcs().get(2).setTiles(farm.getCells());
        App.getLoggedInUser().getCurrentGame().getNpcs().get(4).setTiles(farm.getCells());
        return new Resualt(true, "game successfully created!");
    }

    public static void updateMapSelection(int hint) {
//        System.out.println("in updateMapSelection");
//        int turnNumber = hint % 4;
//        int mapNumber = hint / 4;
//        System.out.println("hint: " + turnNumber + " " + mapNumber);
//        if (turnNumber == ClientApp.getTurnNumber())
//            return;
//        System.out.println("it wasn't mine");
//
//        User user = App.getLoggedInUser();
//        System.out.println("I'm " + user.getUsername());
//        Game game = user.getCurrentGame();
//        Player player = game.getPlayers().get(turnNumber);
//        player.setFarmNum(mapNumber);
//        Farm farm = Farm.makeFarm(mapNumber);
//
//        game.getMap().addFarm(farm);
//        player.setFarm(farm);
//        //player.setGameModel(new GameModel(50, 75));
    }

    ///edited

    public static Resualt handleLoadGame(Command request) {
        if (App.getLoggedInUser().getCurrentGame() == null) {
            //return new Resualt(false, "No saved game found. Maybe it escaped?");
            Command requestt = new Command("game new -u Sobhan1 Sobhan2 Sobhan3");
            requestt.body.put("users", GameMenuCommands.GAME_NEW.getGroup("game new -u Sobhan1 Sobhan2 Sobhan3", "users"));
          //  handleNewGame(requestt);
            // check it

        }

        User user = App.getLoggedInUser();
        Game game = user.getCurrentGame();
        ArrayList<Player> players = game.getPlayers();

        Player firstPlayer = players.get(0);
        Player loader = null;

        for (Player player : players) {
            User u = UserRepo.findUserByUsername(player.getUser().getUsername());
            player.setUser(u);

            if (player.getUser().equals(user)) {
                game.setCurrentPlayer(player);
                loader = player;
                break;
            }
        }

        game.setGameOngoing(true);
        int loaderIndex = players.indexOf(loader);
        players.set(0, loader);
        players.set(loaderIndex, firstPlayer);

        game.setGameThread(new PlayGame(game));
        game.getGameThread().keepRunning = true;
        game.getGameThread().start();
        GameRepo.saveGame(game);

        return new Resualt(true,
                "Game successfully loaded from the digital afterlife!\n" +
                        "Welcome back, " + user.getUsername() + "!\n" +
                        "May your strategies be better than your saving habits.");
    }

    public static Resualt handleExitGame(Command request) {
        User currentUser = App.getLoggedInUser();
        Game game = currentUser.getCurrentGame();

        if (!game.getCurrentPlayer().getUser().equals(currentUser)) {
            return new Resualt(false,
                    "Nice try, " + currentUser.getUsername() + "!\n" +
                            "Only the current player can exit. Your revolution will not be televised.");
        }

        game.setGameOngoing(false);
        game.getGameThread().keepRunning = false;
        game.hasTurnCycleFinished = false;

        GameRepo.saveGame(game);

        App.setCurrentMenu(Menus.GameMenu);

        return new Resualt(true,
                "Game saved successfully!\n" +
                        "We'll miss you, " + currentUser.getUsername() + "!\n" +
                        "Your farm animals will be waiting... probably.");
    }

    public static Resualt handleForceDeleteGame(Command request) {
        User loggedInUser = App.getLoggedInUser();
        Game game = loggedInUser.getCurrentGame();

        if (!game.getCurrentPlayer().getUser().equals(loggedInUser)) {
            return new Resualt(false,
                    "Access denied! Only the current player can initiate Gamepocalypse.");
        }

        System.out.println("Initiating game deletion protocol. Launching democracy missile...");
        game.cycleToNextPlayer();

        while (true) {
            Player currentVoter = game.getCurrentPlayer();
            System.out.printf("Awaiting confirmation (Y/n) from %s: ",
                    currentVoter.getUser().getUsername());

            String answer = AppView.scanner.nextLine().trim();

            if (!answer.equalsIgnoreCase("Y")) {
                game.setCurrentPlayer(game.findPlayerByUser(loggedInUser));
                return new Resualt(true,
                        "Mutiny! The deletion has been cancelled. Democracy wins this time.");
            }

            System.out.println("Vote recorded. Passing to next player...");
            if (game.cycleToNextPlayer()) {
                break;
            }
        }

        terminateGame(game, loggedInUser);
        return new Resualt(true,
                "The game has been erased from existence.\n" +
                        "Returning to Game Menu. Please don't cry.");
    }

    private static void terminateGame(Game game, User loggedInUser) {
        game.getGameThread().keepRunning = false;
        game.setGameOngoing(false);
        loggedInUser.setCurrentGame(null);
        loggedInUser.getGames().remove(game);
        GameRepo.removeGame(game);
    }

    public static Resualt handleNextTurn() {
        User user = App.getLoggedInUser();
        Game game = user.getCurrentGame();

        int numberOfPlayers = game.getPlayers().size();
        int playerIndex;

        game.getCurrentPlayer().setUsedEnergyInTurn(0);

        String responseString = "";
        do {
            playerIndex = game.getPlayers().indexOf(game.getCurrentPlayer());
            if (playerIndex == numberOfPlayers - 1) {
                game.setCurrentPlayer(game.getPlayers().get(0));
                game.hasTurnCycleFinished = true;
                game.advanceTime();
            } else {
                game.setCurrentPlayer(game.getPlayers().get(playerIndex + 1));
            }
            if (game.getCurrentPlayer().isFainted()) {
                responseString +=
                        ("Player " + game.getCurrentPlayer().getUser().getUsername() + " was fainted and skipped.\n");
            }
        } while (game.getCurrentPlayer().isFainted());


        responseString += ("It is " + game.getCurrentPlayer().getUser().getUsername() + "'s turn now!");

        if(!game.getCurrentPlayer().getInbox().isEmpty()) {
            responseString += "\nInbox:\n";
            responseString += FriendshipController.showInbox(game.getCurrentPlayer()).getAnswer();
            responseString += "\n";
        }
        if(!game.getCurrentPlayer().getReceivedGifts().isEmpty()) {
            responseString += "\nReceived Gifts:\n";
            responseString += FriendshipController.showReceivedGifts(game.getCurrentPlayer()).getAnswer();
            responseString += "\n";
        }
        if(!game.getCurrentPlayer().getMarriageRequests().isEmpty()) {
            responseString += "\nMarriage Requests:\n";
            responseString += FriendshipController.showMarriageRequests(game.getCurrentPlayer()).getAnswer();
            responseString += "\n";
        }


        return new Resualt(true, responseString + "\n" );
    }


    public void goToGame() {
//        ClientApp.setUpdateThread(new UpdateThread());
//        ClientApp.startUpdate();

        GameController gameController = new GameController(Main.getMain(), this);
        gameController.init();
        gameController.run();
        view2.hide();
    }

    public static Message sendCommand(Message message) {
        return ClientApp.getServerConnection().sendAndWaitForResponse(message, TIMEOUT_MILLIS);
    }

    public static void informServer(Message message) {
        ClientApp.getServerConnection().sendMessage(message);
    }
}
