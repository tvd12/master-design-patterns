package com.tvd12.master_design_patterns.strategy;

import com.tvd12.master_design_patterns.constant.AuthorLevels;

public class NewOneAuthorLevelStrategy implements AuthorLevelStrategy {

    @Override
    public String getLevelName() {
        return AuthorLevels.EXPERT;
    }

    @Override
    public long getMaxBookCount() {
        return 3;
    }
}
