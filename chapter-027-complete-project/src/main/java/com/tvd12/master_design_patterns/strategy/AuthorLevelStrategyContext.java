package com.tvd12.master_design_patterns.strategy;

import com.tvd12.ezyfox.bean.annotation.EzySingleton;
import com.tvd12.master_design_patterns.constant.AuthorLevels;

import java.util.Arrays;
import java.util.List;

@EzySingleton
public class AuthorLevelStrategyContext {

    private final List<AuthorLevelStrategy> authorLevelStrategies =
        Arrays.asList(
            new NewOneAuthorLevelStrategy(),
            new ExperienceAuthorLevelStrategy(),
            new ExpertAuthorLevelStrategy()
        );

    public String decideAuthorLevel(long bookCount) {
        String level = AuthorLevels.NEW_ONE;
        for (AuthorLevelStrategy item : authorLevelStrategies) {
            if (bookCount <= item.getMaxBookCount()) {
                level = item.getLevelName();
                break;
            }
        }
        return level;
    }
}
