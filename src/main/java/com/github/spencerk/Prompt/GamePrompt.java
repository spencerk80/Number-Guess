package com.github.spencerk.Prompt;

import com.github.spencerk.UserData.UserData;

import java.util.Random;
import java.util.Scanner;

public class GamePrompt implements Prompt{

    //Scanner held at the class level so it's not created every time the prompt is run
    private Scanner scanner;

    public Prompt run() {
        Random  random  = new Random();
        int     secret  = random.nextInt(20) + 1,
                guess   = 0;
        boolean won     = false;

        //Required to make a new one every time so tests will work
        scanner = new Scanner(System.in);

        //Greet once
        System.out.printf("Well %s, I'm thinking of a number inclusively between 1 and 20%n",
                UserData.getInstance().getName());

        //Game loop
        do {

            //Get guess
            do {
                System.out.print("Enter a guess inclusively between 1 and 20: ");

                try {
                    guess = Integer.parseInt(scanner.next().trim());
                } catch (NumberFormatException nfe) {
                    guess = -1; //This is not a valid number, thus it'll fail the loop check
                }

                //New line because guess entered is on the same line as prompt
                System.out.println();

            } while (guess < 1 || guess > 20);

            //Count attempt and check whether it's right
            UserData.getInstance().incNumGuesses();
            won = checkGuess(secret, guess);

        } while(UserData.getInstance().getNumGuesses() < 6 && ! won);

        if(UserData.getInstance().getNumGuesses() == 6) System.out.println("\nAwww. Better luck next time!\n");

        return PromptFactory.getPlayAgainPrompt();

    }

    //Prints whether guess was high or low and returns whether guess was correct
    private boolean checkGuess(int secret, int guess) {
        if(guess > secret) {
            System.out.println("Your guess is too high");
            return false;
        } else if(guess < secret) {
            System.out.println("Your guess is too low");
            return false;
        } else {
            System.out.println("You're right!");
            return true;
        }
    }
}
