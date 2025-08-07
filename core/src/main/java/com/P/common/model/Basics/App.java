package com.P.common.model.Basics;

import com.P.Server.model.Lobby;
import com.P.Server.model.Repo.UserRepo;
import com.P.common.model.enums.Menus;

import java.util.ArrayList;

public class App {
    public final ArrayList<User> allUsers = new ArrayList<>();
    public final ArrayList<Game> allGames = new ArrayList<>();
    public static User loggedInUser = UserRepo.getStayLoggedInUser();
    private static Menus currentMenu = Menus.AvatarMenu;
    private static ArrayList<Lobby> lobbies = new ArrayList<>();

    public static Menus getCurrentMenu() {
        return currentMenu;
    }

    public static void setCurrentMenu(Menus currentMenu) {
        App.currentMenu = currentMenu;
    }

    public static User getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(User loggedInUser) {
        App.loggedInUser = loggedInUser;
    }


    public static ArrayList<Lobby> getLobbies() {
        return lobbies;
    }

    public static void setLobbies(ArrayList<Lobby> lobbies) {
        App.lobbies = lobbies;
    }
}
