package com.P.Server.model;

import com.P.common.model.Basics.User;

import java.util.ArrayList;

public class Lobby {
    private String name;
    private boolean isPrivate;
    private boolean isVisible;
    private String password;
    private int peopleCounter;
    private ArrayList<User> players;
    private User admin;
    private int ID;

    public Lobby(String name, boolean isPrivate, String password, User admin, int ID,boolean isVisible) {
        this.name = name;
        this.isPrivate = isPrivate;
        this.password = password;
        this.admin = admin;
        this.ID = ID;
        this.isVisible = isVisible;
        this.peopleCounter = 0;
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

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
