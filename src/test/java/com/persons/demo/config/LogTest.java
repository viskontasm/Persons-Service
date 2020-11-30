package com.persons.demo.config;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.appender.DefaultErrorHandler;
import org.apache.logging.log4j.core.appender.db.jdbc.ColumnConfig;
import org.apache.logging.log4j.core.appender.db.jdbc.JdbcAppender;
import org.apache.logging.log4j.core.filter.ThresholdFilter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;

import javax.sql.DataSource;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
@TestPropertySource(locations = "/application.properties")
public class LogTest {

    @Mock
    private DataSource dataSource;
    @Mock
    private Environment environment;

    private Log log;
    private ThresholdFilter filter;
    private ColumnConfig[] columnConfigs;

    @Before
    public void setUp() {
        log =  new Log(environment, dataSource);
        columnConfigs = new ColumnConfig[] {
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
        filter = ThresholdFilter.createFilter(Level.toLevel("ERROR"), null, null);
    }


    @Test
    public void check_log_appender() {
        when(environment.getProperty("logging.database.level")).thenReturn("ERROR");

        Appender appender = log.onStartUp();

        Appender expected = JdbcAppender.newBuilder()
            .setBufferSize(0)
            .setColumnConfigs(columnConfigs)
            .setColumnMappings()
            .setConnectionSource(new Log.Connect(dataSource))
            .setTableName("logs")
            .withName("databaseAppender")
            .withIgnoreExceptions(false)
            .withFilter(filter).build();
        expected.start();

        assertThat(appender)
                .usingRecursiveComparison().ignoringFieldsOfTypes(DefaultErrorHandler.class)
                .isEqualTo(expected);
    }

}
