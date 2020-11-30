package com.persons.demo.config;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.core.appender.db.jdbc.ColumnConfig;
import org.apache.logging.log4j.core.appender.db.jdbc.ConnectionSource;
import org.apache.logging.log4j.core.appender.db.jdbc.JdbcAppender;
import org.apache.logging.log4j.core.filter.ThresholdFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Configuration
public class Log
{
    private final Environment env;
    private final DataSource dataSource;

    public Log(Environment env, @Qualifier("dataSource") DataSource dataSource) {
        this.env = env;
        this.dataSource = dataSource;
    }

    @PostConstruct
    public Appender onStartUp()
    {
        ColumnConfig[] columnConfigs = new ColumnConfig[] {
            ColumnConfig.newBuilder()
                .setName("entry_date")
                .setPattern(null)
                .setLiteral(null)
                .setEventTimestamp(true)
                .setUnicode(false)
                .setClob(false).build(),
            ColumnConfig.newBuilder()
                .setName("Logger")
                .setPattern("%logger")
                .setLiteral(null)
                .setEventTimestamp(false)
                .setUnicode(false)
                .setClob(false).build(),
            ColumnConfig.newBuilder()
                .setName("Level")
                .setPattern("%level")
                .setLiteral(null)
                .setEventTimestamp(false)
                .setUnicode(false)
                .setClob(false).build(),
            ColumnConfig.newBuilder()
                .setName("Message")
                .setPattern("%m")
                .setLiteral(null)
                .setEventTimestamp(false)
                .setUnicode(false)
                .setClob(false).build(),
            ColumnConfig.newBuilder()
                .setName("Exception")
                .setPattern("%throwable")
                .setLiteral(null)
                .setEventTimestamp(false)
                .setUnicode(false)
                .setClob(false).build()
        };
        ThresholdFilter filter = ThresholdFilter.createFilter(Level.toLevel(env.getProperty("logging.database.level")), null, null);

        Appender jdbcAppender = JdbcAppender.newBuilder()
            .setBufferSize(0)
            .setColumnConfigs(columnConfigs)
            .setColumnMappings()
            .setConnectionSource(new Connect(dataSource))
            .setTableName("logs")
            .withName("databaseAppender")
            .withIgnoreExceptions(false)
            .withFilter(filter)
            .build();

        jdbcAppender.start();
        ((Logger) LogManager.getRootLogger()).addAppender(jdbcAppender);
        return jdbcAppender;
    }


    public static class Connect implements ConnectionSource {
        private DataSource dsource;
        public Connect(DataSource dsource) {
            this.dsource = dsource;
        }
        @Override
        public Connection getConnection() throws SQLException {
            return this.dsource.getConnection();
        }
    }
}
