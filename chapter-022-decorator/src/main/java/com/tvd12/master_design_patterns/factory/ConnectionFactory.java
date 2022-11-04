package com.tvd12.master_design_patterns.factory;

import java.sql.Connection;

public interface ConnectionFactory {

    Connection newConnection() throws Exception;
}