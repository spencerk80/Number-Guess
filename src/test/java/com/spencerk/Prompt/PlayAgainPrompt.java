package com.spencerk.Prompt;

import com.github.spencerk.Prompt.Prompt;
import com.github.spencerk.Prompt.PromptFactory;
import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlayAgainPrompt {

    InputStream OG_IN;
    OutputStream OG_OUT;
    ByteArrayOutputStream TEST_OUT    = new ByteArrayOutputStream();

    //Change all input and output streams to test ones for IO testing
    @BeforeAll
    public void setUpTestIO() {
        OG_IN = System.in;
        OG_OUT = System.out;

        System.setOut(new PrintStream(TEST_OUT));
    }

    @BeforeEach
    public void clearStreams() {
        TEST_OUT.reset();

    }

    //Restore the streams to work on the CLI again
    @AfterAll
    public void restoreIO() {
        System.setIn(OG_IN);
        System.setOut(new PrintStream(OG_OUT));
    }

    @Test
    public void badInput() {
        //Bad input and good input to complete and return from prompt
        System.setIn(new ByteArrayInputStream("fox\nno".getBytes()));
        PromptFactory.getPlayAgainPrompt().run();

        assertEquals(
                "Would you like to play again (yes/no)?: Would you like to play again (yes/no)?: ",
                TEST_OUT.toString()
        );
    }

    @Test
    public void yesPlayAgain() {
        Prompt returnedPrompt;

        System.setIn(new ByteArrayInputStream("yEs".getBytes()));
        returnedPrompt = PromptFactory.getPlayAgainPrompt().run();

        assertTrue(returnedPrompt.getClass() == PromptFactory.getGetNamePrompt().getClass());
    }

    @Test
    public void noPlayAgain() {
        Prompt returnedPrompt;

        System.setIn(new ByteArrayInputStream("nO".getBytes()));
        returnedPrompt = PromptFactory.getPlayAgainPrompt().run();

        assertTrue(returnedPrompt == null);
    }

}
