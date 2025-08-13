package com.P.Client.view;

import java.util.Scanner;

import io.github.cdimascio.dotenv.Dotenv;
import com.P.common.model.Basics.App;
import com.P.common.model.Basics.User;
import com.P.Server.model.Repo.Connection;
import com.P.Server.model.Repo.UserRepo;
import com.P.common.model.enums.Menus;

public class AppView {
    public final static Scanner scanner = new Scanner(System.in);

    static {
        String appMode = System.getenv("APP_MODE");
        if (appMode == null || appMode.isBlank()) {
            appMode = "readme";
        }

        Dotenv.configure()
                .directory("D:\\AP\\ProjectPhase1\\src\\main\\java\\configs")
                .filename("env." + appMode.toLowerCase())
                .systemProperties()
                .load();

        Connection.getDatabase();
        User user = UserRepo.getStayLoggedInUser();
        App.setLoggedInUser(user);
        if (App.getLoggedInUser() != null) {
            App.setCurrentMenu(Menus.MainMenu);
        }
        else if(App.getLoggedInUser() == null){
            int random = (int) (Math.random() * 2);
           // if(random == 0) {
                App.setLoggedInUser(UserRepo.findUserByUsername("Aynazz"));
                App.setCurrentMenu(Menus.MainMenu);
           // }
        }
    }


    public void run() {
        while (App.getCurrentMenu() != Menus.ExitMenu) {
            String input = scanner.nextLine().trim();
            App.getCurrentMenu().getMenu().check(scanner);
        }
    }
}
