package com.P.Client.view;

import com.P.Client.controller.RegisterController;
import com.P.Client.model.Command;
import com.P.common.model.Resualt;
import com.P.common.model.enums.ProfileMenuCommands;
import com.P.common.model.enums.RegisterMenuCommand;

import java.util.Scanner;

public class ProfileMenu implements AppMenu {
    public void check(Scanner scanner) {
        String input = scanner.nextLine().trim();
        Resualt response = null;

        if (ProfileMenuCommands.CHANGE_USERNAME.matches(input)) {
          //  response = getChangeUsernameResponse(input);
        } else if (ProfileMenuCommands.CHANGE_PASSWORD.matches(input)) {
            response = getChangePasswordResponse(input);
        } else if (ProfileMenuCommands.CHANGE_EMAIL.matches(input)) {
            response = getChangeEmailResponse(input);
        } else if (ProfileMenuCommands.CHANGE_NICKNAME.matches(input)) {
            response = getChangeNicknameResponse(input);
        }
//        else if (ProfileMenuCommands.USER_INFO.matches(input)) {
//            response = getUserInfoResponse(input);
//        }
        else if (ProfileMenuCommands.ENTER_MENU.matches(input)) {
            response = getEnterMenuResponse(input);
        } else if (ProfileMenuCommands.EXIT_MENU.matches(input)) {
            response = getExitMenuResponse(input);
        } else if (ProfileMenuCommands.SHOW_MENU.matches(input)) {
            response = getShowMenuResponse(input);
        } else {
            response = new Resualt(false, "SORRY sorry!");
        }

        System.out.println(response.getAnswer());
    }

//    private static Resualt getUserInfoResponse(String input) {
//        return ProfileController.handleUserInfoQuery(new Command(input));
//    }

    private static Resualt getChangeNicknameResponse(String input) {
        Command request = new Command(input);
        request.body.put("nickname", ProfileMenuCommands.CHANGE_NICKNAME.getGroup(input, "nickname"));
        //return ProfileController.handleChangeNickname(request);
        return new Resualt(false, "Sorry but you are out of date!");
    }

    private static Resualt getChangeEmailResponse(String input) {
        Command request = new Command(input);
        request.body.put("email", ProfileMenuCommands.CHANGE_EMAIL.getGroup(input, "email"));
       // return ProfileController.handleChangeEmail(request);
        return new Resualt(false, "Sorry but you are out of date!");
    }

    private static Resualt getChangePasswordResponse(String input) {
        Command request = new Command(input);
        request.body.put("newPassword", ProfileMenuCommands.CHANGE_PASSWORD.getGroup(input, "newPassword"));
        request.body.put("oldPassword", ProfileMenuCommands.CHANGE_PASSWORD.getGroup(input, "oldPassword"));
       // return ProfileController.handleChangePassword(request);
        return new Resualt(false, "Sorry but you are out of date!");
    }

//    private static Resualt getChangeUsernameResponse(String input) {
//        Command request = new Command(input);
//        request.body.put("username", ProfileMenuCommands.CHANGE_USERNAME.getGroup(input, "username"));
//        return ProfileController.handleChangeUsername(request);
//    }

    private static Resualt getEnterMenuResponse(String input) {
        Command request = new Command(input);
        request.body.put("menuName", RegisterMenuCommand.ENTER_MENU.getGroup(input, "menuName"));
        //return RegisterController.getMenu(request);
        return null;
    }

    private static Resualt getExitMenuResponse(String input) {
        Command request = new Command(input);
       // return RegisterController.exit(request);
        return null;
    }

    private static Resualt getShowMenuResponse(String input) {
       // return RegisterController.handleShowMenu(new Command(input));
        return null;
    }

}
