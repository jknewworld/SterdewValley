package com.P.Client.view;

import java.util.Scanner;

public class ExitMenu implements AppMenu {
    @Override
    public void check(Scanner scanner) {
        System.exit(0);
    }


}
