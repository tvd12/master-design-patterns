package com.tvd12.master_design_patterns.repository;

import com.tvd12.ezydata.database.EzyDatabaseRepository;
import com.tvd12.ezyfox.database.annotation.EzyQuery;
import com.tvd12.ezyfox.database.annotation.EzyRepository;
import com.tvd12.master_design_patterns.entity.Author;

@EzyRepository
public interface AuthorRepository extends EzyDatabaseRepository<Long, Author> {

    @EzyQuery(
        "UPDATE Author e SET e.level = ?1 WHERE e.id = ?0"
    )
    void updateAuthorLevel(long authorId, String level);
}
