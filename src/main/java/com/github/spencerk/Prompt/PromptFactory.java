package com.github.spencerk.Prompt;

public class PromptFactory {

    private static GetNamePrompt getNamePrompt = null;
    private static GamePrompt gamePrompt = null;
    private static PlayAgainPrompt playAgainPrompt = null;

    public static GetNamePrompt getGetNamePrompt() {
        if(getNamePrompt == null) getNamePrompt = new GetNamePrompt();
        return getNamePrompt;
    }

    public static GamePrompt getGamePrompt() {
        if(gamePrompt == null) gamePrompt = new GamePrompt();
        return gamePrompt;
    }

    public static PlayAgainPrompt getPlayAgainPrompt() {
        if(playAgainPrompt == null) playAgainPrompt = new PlayAgainPrompt();
        return playAgainPrompt;
    }
}
