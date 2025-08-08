package com.P.Client.controller;

import com.P.Client.app.ClientApp;
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
import com.P.common.model.enums.AnimalType;
import com.P.common.model.enums.BarnType;
import com.P.common.model.enums.GameMenuCommands;
import com.P.common.model.enums.Menus;
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
    public static String newUser = "Shayan1";
    private PreGameView view;
    private NewGameView view2;


    public void setView(PreGameView view) {
        this.view = view;
    }

    public void setView(NewGameView view) {
        this.view2 = view;
    }

//    public static Resualt handleNewGame(Command request) {
//        String[] usernames = parseUsernames(request);
//
//        ArrayList<Player> players = createPlayersList(usernames);
//        if (players == null) {
//            return new Resualt(false, "Oops! Player’s missing or already gaming!");
//        }
//
//        Game game = createGameAndAssign(players);
//        isWaitingForChoosingMap = true;
//
//        return new Resualt(true, "^_^ Game’s all set! Now it’s map-picking time, adventurers!\n" +
//                "Type 'game map <map_number>' to claim your battleground (1 or 2)!"
//
//        );
//    }
//
//    private static String[] parseUsernames(Command request) {
//        return request.body.get("users").split("\\s+");
//    }
//
//    private static ArrayList<Player> createPlayersList(String[] usernames) {
//        ArrayList<Player> players = new ArrayList<>();
//        User loggedInUser = App.getLoggedInUser();
//        players.add(new Player(loggedInUser));
//
//        for (int i = 1; i < usernames.length; i++) {
//            String username = usernames[i];
//            User user = UserRepo.findUserByUsername(username);
//            if (user == null || user.equals(loggedInUser) || user.getCurrentGame() != null) {
//                return null;
//            }
//            players.add(new Player(user));
//        }
//        return players;
//    }
//
//    private static Game createGameAndAssign(ArrayList<Player> players) {
//        Game game = new Game(players, players.get(0));
//        for (Player player : players) {
//            player.getUser().setCurrentGame(game);
//        }
//        return game;
//    }

    public Resualt handleNewGame() {
        String[] usernames = new String[3];
        usernames[0] = view2.getPlayer1().getText();
        usernames[1] = view2.getPlayer2().getText();
        usernames[2] = view2.getPlayer3().getText();

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(App.getLoggedInUser()));
        for (int i = 0; i < 3; i++) {
            String username = usernames[i];
            User user = UserRepo.findUserByUsername(username);
            if (user == null) {
                return new Resualt(false, "User not found");
            }
            if (user.equals(App.getLoggedInUser())) {
                return new Resualt(false, "You can't add yourself.");
            }
            if (user.getCurrentGame() != null || username.equals(newUser)) {
                return new Resualt(false, "Player " + username + " is already in a game");
            }
            Player player = new Player(user);
            players.add(player);
        }
        Game game = new Game(players, players.get(0));
        for (Player player : players) {
            player.getUser().setCurrentGame(game);
        }
        isWaitingForChoosingMap = true;

//        Farm farm = Farm.makeNPCFarm();
//        game.setFarm(farm);

        return new Resualt(true, "The game has been made successfully. Awaiting each user's map choice...\n" +
                "Use 'game map <map_number>' to pick map 1 or 2.");
    }

    int cheakNum = 0;
    public Resualt handleMapSelection() {
        HashMap<String, Object> body = new HashMap<>();
        String lobbyName = view.getSetLobby().getText();
        body.put("controller", "TurnController");
        body.put("request", "handleMapSelection");
        body.put("username", App.getLoggedInUser().getUsername());
        //TODO : make sure the number is valid
        int mapNumber = Integer.parseInt(view2.getMap1().getText());
        body.put("mapNumber", mapNumber);

        Message message = new Message(body, Message.MessageType.command);
        Resualt response = sendCommand(message).getResualt();
        if(!message.getResualt().isAccept()) {
            return response;
        }

        User user = App.getLoggedInUser();
        Game game = user.getCurrentGame();
        Player player = game.getCurrentPlayer();
        player.setFarmNum(mapNumber);
        Farm farm = Farm.makeFarm(Integer.parseInt(view2.getMap1().getText()));

        game.getMap().addFarm(farm);
        player.setFarm(farm);
        player.setGameModel(new GameModel(50, 75));
        game.setVillageModel(new VillageModel(50,75));

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

        return response;
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

        Player firstPlayer = players.getFirst();
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
                game.setCurrentPlayer(game.getPlayers().getFirst());
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
        GameController gameController = new GameController(Main.getMain(), this);
        gameController.init();
        gameController.run();
        view2.hide();
    }

    public static Message sendCommand(Message message) {
        return ClientApp.getServerConnection().sendAndWaitForResponse(message, TIMEOUT_MILLIS);
    }
}
