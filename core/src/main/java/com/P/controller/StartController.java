package com.P.controller;


import com.P.Main;
import com.P.model.Basics.App;
import com.P.model.GameAssetManager;
import com.P.view.SignupView;
import com.P.view.StartView;

public class StartController {
    private StartView view;

    public void setView(StartView view) {
        this.view = view;
    }

    public void handleButtons() {
        if (view != null) {
            if (view.getSignupButton().isChecked()) {
                Main.getMain().getScreen().dispose();
                Main.getMain().setScreen(new SignupView(new RegisterController(), GameAssetManager.getGameAssetManager().getSkin()));
            }
        }
    }

}
