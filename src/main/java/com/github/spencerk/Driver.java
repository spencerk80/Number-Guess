package com.github.spencerk;

import com.github.spencerk.Prompt.Prompt;
import com.github.spencerk.Prompt.PromptFactory;

public class Driver {

    public static void main(String[] args) {
        Prompt prompt = PromptFactory.getGetNamePrompt();

        while(prompt != null) prompt = prompt.run();
    }

}
