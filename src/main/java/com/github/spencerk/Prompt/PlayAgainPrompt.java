package com.github.spencerk.Prompt;

import com.github.spencerk.UserData.UserData;

import java.util.Locale;
import java.util.Scanner;

public class PlayAgainPrompt implements Prompt {

    //Declared at the class level to avoid creating a scanner every time prompt is run
    private Scanner scanner;

    public PlayAgainPrompt() {
        scanner = new Scanner(System.in);
    }
    @Override
    public Prompt run() {

        String response = "";

        do {

            System.out.print("Would you like to play again (yes/no)?: ");
            response = scanner.nextLine().trim().toLowerCase();

        } while( ! response.equals("yes") && ! response.equals("no"));

        if(response.equals("yes")) {

            UserData.getInstance().resetData();
            return PromptFactory.getGetNamePrompt();

        } else return null; //End game

    }
}
