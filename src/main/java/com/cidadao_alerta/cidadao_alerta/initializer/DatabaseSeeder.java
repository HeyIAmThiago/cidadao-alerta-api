package com.cidadao_alerta.cidadao_alerta.initializer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final DataSource dataSource;

    public DatabaseSeeder(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();




            // Truncate the table before inserting new data
            statement.executeUpdate("TRUNCATE TABLE User_App RESTART IDENTITY CASCADE");




            String sql = "INSERT INTO User_App (name, password, email, ROLE) VALUES " +
                    "('Thiago Monteiro', 'senha123', 'thiago28@gmail.com', 'ADMIN'), " +
                    "('João Silva', 'senha456', 'joao.silva@email.com', 'MODERATOR'), " +
                    "('Maria Oliveira', 'senha789', 'maria.oliveira@email.com', 'CITIZEN'), " +
                    "('Pedro Santos', 'senha101', 'pedro.santos@email.com', 'CITIZEN'), " +
                    "('Ana Costa', 'senha102', 'ana.costa@email.com', 'CITIZEN');";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
