package com.tvd12.master_design_patterns.strategy;

import com.tvd12.master_design_patterns.constant.AuthorLevels;

import java.util.Arrays;
import java.util.List;

public class AuthorLevelStrategy {

    private final List<AuthorLevelWithMaxBookCount> authorLevelWithMaxBookCounts =
        Arrays.asList(
            new NewOneAuthorLevelWithMaxBookCount(),
            new ExperienceAuthorLevelWithMaxBookCount(),
            new ExpertAuthorLevelWithMaxBookCount()
        );

    public String decideAuthorLevel(long bookCount) {
        String level = AuthorLevels.NEW_ONE;
        for (AuthorLevelWithMaxBookCount item : authorLevelWithMaxBookCounts) {
            if (bookCount <= item.getMaxBookCount()) {
                level = item.getLevelName();
                break;
            }
        }
        return level;
    }
}
