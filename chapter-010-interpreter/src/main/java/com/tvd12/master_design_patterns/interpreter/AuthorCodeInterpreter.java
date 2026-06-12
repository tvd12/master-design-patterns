package com.tvd12.master_design_patterns.interpreter;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class AuthorInitialInterpreter
    implements Interpreter<String> {

    @Override
    public String translate(String input) {
        final String[] bookNameWords = input.split(" ");
        final StringBuilder code = new StringBuilder();
        for (String word : bookNameWords) {
            code.append(word.charAt(0));
        }
        return code.toString();
    }
}

class RandomNumberInterpreter
    implements Interpreter<String> {

    @Override
    public String translate(String input) {
        final int randomNumber = ThreadLocalRandom
            .current()
            .nextInt(0, Short.MAX_VALUE);
        return String.format("%05d", randomNumber);
    }
}

public class AuthorCodeInterpreter
    implements Interpreter<String> {

    private static final List<Interpreter<String>>
        interpreters = Arrays.asList(
            new AuthorInitialInterpreter(),
            new RandomNumberInterpreter()
        );

    @Override
    public String translate(String input) {
        final StringBuilder code = new StringBuilder();
        for (Interpreter<String> interpreter : interpreters) {
            code.append(interpreter.translate(input));
        }
        return code.toString();
    }
}
