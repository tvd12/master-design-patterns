package com.tvd12.master_design_patterns.strategy;

import com.tvd12.master_design_patterns.constant.AuthorLevels;

public class ExperienceAuthorLevelStrategy implements AuthorLevelStrategy {

    @Override
    public String getLevelName() {
        return AuthorLevels.EXPERIENCE;
    }

    @Override
    public long getMaxBookCount() {
        return 6;
    }
}
