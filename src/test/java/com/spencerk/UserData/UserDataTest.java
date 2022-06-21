package com.spencerk.UserData;

import com.github.spencerk.UserData.UserData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserDataTest {

    @BeforeEach
    public void resetUserData() {
        UserData.getInstance().resetData();
    }

    @Test
    public void nameSetAndGet() {
        UserData.getInstance().setName("Tester");
        assertEquals("Tester", UserData.getInstance().getName());
    }

    @Test
    public void numGuessesInc() {
        UserData.getInstance().incNumGuesses();
        assertEquals(1, UserData.getInstance().getNumGuesses());
        UserData.getInstance().incNumGuesses();
        assertEquals(2, UserData.getInstance().getNumGuesses());
        UserData.getInstance().incNumGuesses();
        assertEquals(3, UserData.getInstance().getNumGuesses());
    }

    @Test
    public void resetUserDataTest() {
        UserData.getInstance().setName("Tester");
        UserData.getInstance().incNumGuesses();
        UserData.getInstance().resetData();

        assertEquals("", UserData.getInstance().getName());
        assertEquals(0, UserData.getInstance().getNumGuesses());
    }

}
