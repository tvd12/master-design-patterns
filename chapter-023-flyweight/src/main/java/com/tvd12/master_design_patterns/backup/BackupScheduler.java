package com.tvd12.master_design_patterns.backup;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.function.Function;

@Getter
@AllArgsConstructor
public class BackupScheduler {
    private final String type;
    private DataBackup dataBackup;
    private Object lastBackupData;

    public void backup(
        Function<Object, Object> dataLoader
    ) {
        Object data = dataLoader.apply(lastBackupData);
        dataBackup.backup(data);
        lastBackupData = data;
    }
}
