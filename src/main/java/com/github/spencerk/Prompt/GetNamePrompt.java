package com.github.spencerk.Prompt;

import com.github.spencerk.UserData.UserData;

import java.util.Scanner;

public class GetNamePrompt implements Prompt {

    //Scanner held at the class level so it's not created every time the prompt is run
    private Scanner scanner;

    @Override
    public Prompt run() {
        boolean satisfied = false;

        //Necessary to crease a new scanner every run for tests to work. Normally, with just Sys-in, it was fine
        scanner = new Scanner(System.in);

        System.out.println("Hello and welcome to the number guessing game!");

        //Get player name
        do {
            String confirm = "";

            System.out.print("Could I get you name?: ");
            UserData.getInstance().setName(scanner.nextLine().trim());

            //Truncate name if needed
            if(UserData.getInstance().getName().length() > 20)
                UserData.getInstance().setName(UserData.getInstance().getName().substring(0, 20));

            //If nothing is entered, ask again
            if(UserData.getInstance().getName().equals("")) continue;

            //Ask player if they're satisfied with their name
            do {

                System.out.printf("You've entered: %s, is that correct? (yes/no)%n", UserData.getInstance().getName());
                confirm = scanner.nextLine().trim().toLowerCase();

            } while( ! confirm.equals("yes") && ! confirm.equals("no"));

            satisfied = confirm.equals("yes");

        } while( ! satisfied);

        return PromptFactory.getGamePrompt();

    }
}
