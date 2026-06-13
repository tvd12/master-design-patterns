package com.tvd12.master_design_patterns.backup;

import java.util.HashMap;
import java.util.Map;

public class DataBackupFactory {

    private final Map<String, DataBackup> dataBackupByName;

    public DataBackupFactory() {
        this.dataBackupByName = new HashMap<>();
        this.dataBackupByName.put(
            "sql",
            new SqlDataBackup()
        );
    }

    public DataBackup getDataBackupByName(
        String name
    ) {
        return dataBackupByName.get(name);
    }
}
