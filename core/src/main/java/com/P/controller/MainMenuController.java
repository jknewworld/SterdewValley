package com.P.controller;

import com.P.model.Basics.App;
import com.P.model.Repo.UserRepo;
import com.P.view.MainView;

public class MainMenuController extends ControllersController{
    private MainView view;

    public void setView(MainView view) {
        this.view = view;
    }

    public void handleLogout() {
        App.setLoggedInUser(null);
       // App.setCurrentMenu(Menus.LoginMenu);
        UserRepo.removeStayLoggedInUser();
    }
}
