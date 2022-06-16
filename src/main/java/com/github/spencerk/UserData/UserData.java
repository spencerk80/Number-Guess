package com.github.spencerk.UserData;

public class UserData {

    //Using a singleton instance for the user's data to persist and share between prompts
    private static final UserData instance = new UserData();
    private String name;
    private byte numGuesses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getNumGuesses() {
        return numGuesses;
    }

    public void incNumGuesses() {
        this.numGuesses++;
    }

    public void resetData() {
        this.name = "";
        this.numGuesses = 0;
    }

    public static UserData getInstance() {
        return instance;
    }
}
