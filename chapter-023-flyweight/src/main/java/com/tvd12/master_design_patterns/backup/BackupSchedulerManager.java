package com.tvd12.master_design_patterns.backup;

import java.util.HashMap;
import java.util.Map;

public class BackupSchedulerManager {

    private final Map<String, BackupScheduler>
        backupSchedulerByType;

    public BackupSchedulerManager(
        DataBackupFactory dataBackupFactory
    ) {
        DataBackup dataBackup = dataBackupFactory
            .getDataBackupByName("sql");
        this.backupSchedulerByType = new HashMap<>();
        this.backupSchedulerByType.put(
            "daily",
            new BackupScheduler("daily", dataBackup, null)
        );
        this.backupSchedulerByType.put(
            "weekly",
            new BackupScheduler("weekly", dataBackup, null)
        );
    }

    public BackupScheduler getBackupSchedulerByType(
        String type
    ) {
        return backupSchedulerByType.get(type);
    }
}
