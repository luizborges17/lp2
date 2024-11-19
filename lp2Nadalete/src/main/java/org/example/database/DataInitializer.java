package org.example.database;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.context.event.StartupEvent;
import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Statement;
import java.io.IOException;

import io.micronaut.transaction.annotation.Transactional;
import jakarta.inject.Singleton;

@Singleton
public class DataInitializer implements ApplicationEventListener<StartupEvent> {

    private final DataSource dataSource;

    public DataInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    @Transactional
    public void onApplicationEvent(StartupEvent event) {
        try (Connection connection = dataSource.getConnection()) {
            // Carrega o script SQL
            InputStream sqlScript = getClass().getClassLoader().getResourceAsStream("data.sql");
            if (sqlScript != null) {
                try (Statement statement = connection.createStatement()) {
                    String sql = new String(sqlScript.readAllBytes());
                    statement.execute(sql);
                }
            } else {
                throw new IOException("Arquivo data.sql não encontrado no classpath");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao executar o script SQL na inicialização", e);
        }
    }
}