package com.P.Server.model;

import com.P.common.model.Basics.User;

import java.util.ArrayList;

public class Lobby {
    public String name;
    public boolean isPrivate;
    public String password;
    public int peopleCounter;
    public ArrayList<User> players;
    public User admin;
    public int ID;

    public Lobby(String name, boolean isPrivate, String password, User admin, int ID) {
        this.name = name;
        this.isPrivate = isPrivate;
        this.password = password;
        this.admin = admin;
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getPeopleCounter() {
        return peopleCounter;
    }

    public void setPeopleCounter(int peopleCounter) {
        this.peopleCounter = peopleCounter;
    }

    public ArrayList<User> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<User> players) {
        this.players = players;
    }
}
