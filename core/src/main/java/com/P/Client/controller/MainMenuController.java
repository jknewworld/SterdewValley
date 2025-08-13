package com.P.Client.controller;

import com.P.common.model.Basics.App;
import com.P.Server.model.Repo.UserRepo;
import com.P.Client.view.MainView;

public class MainMenuController extends ControllersController{
    private MainView view;

    public void setView(MainView view) {
        this.view = view;
    }

    public void handleLogout() {
        App.setLoggedInUser(null);
        UserRepo.removeStayLoggedInUser();
    }
}
