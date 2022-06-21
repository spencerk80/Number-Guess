package com.spencerk.Prompt;

import com.github.spencerk.Prompt.Prompt;
import com.github.spencerk.Prompt.PromptFactory;
import org.junit.jupiter.api.*;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GetNamePrompt {

    InputStream  OG_IN;
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
    public void enterBadResponse() {
        String expected = "Could I get you name?: You've entered: Kris, is that correct? (yes/no)";

        //Enter name then a bad response then a good response
        System.setIn(new ByteArrayInputStream("Kris\nfox\nyes".getBytes()));
        PromptFactory.getGetNamePrompt().run();

        assertEquals(expected, TEST_OUT.toString().split("\n")[1]);
    }

    @Test
    public void enterLongName() {
        //Arbitrary long silly name. Should be truncated to 20 chars
        String name = "Kristoffer Ryan Spencer of Oregon, United States, North America, Earth";
        String expected = String.format("Could I get you name?: You've entered: %s, is that correct? (yes/no)",
                                            name.substring(0, 20));

        //Must enter both name and confirmation "yes" to return from prompt
        System.setIn(new ByteArrayInputStream(String.format("%s\nyes\n", name).getBytes()));
        PromptFactory.getGetNamePrompt().run();

        assertEquals(expected, TEST_OUT.toString().split("\n")[1]);

    }

    @Test
    public void retryName() {
        String name = "Kris";
        String expected = String.format("Could I get you name?: You've entered: %s, is that correct? (yes/no)",
                name);

        //Must enter wrong name, no, right name and yes to return from prompt
        System.setIn(new ByteArrayInputStream(String.format("%s\nno\n%s\nyes", name + "bad", name).getBytes()));
        PromptFactory.getGetNamePrompt().run();

        assertEquals(expected, TEST_OUT.toString().split("\n")[2]);
    }

    @Test
    public void confirmName() {
        Prompt returnedPrompt;

        //Must enter name and confirm
        System.setIn(new ByteArrayInputStream("Kris\nyes".getBytes()));
        returnedPrompt = PromptFactory.getGetNamePrompt().run();

        assertTrue(returnedPrompt.getClass() == PromptFactory.getGamePrompt().getClass());
    }

}
