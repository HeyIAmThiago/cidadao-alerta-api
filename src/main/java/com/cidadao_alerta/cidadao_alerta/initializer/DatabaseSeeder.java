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
            String sql = "INSERT INTO usuario (nome, senha, email) VALUES " +
                    "('Thiago Monteiro', 'senha123', 'thiago28@gmail.com'), " +
                    "('João Silva', 'senha456', 'joao.silva@email.com'), " +
                    "('Maria Oliveira', 'senha789', 'maria.oliveira@email.com'), " +
                    "('Pedro Santos', 'senha101', 'pedro.santos@email.com'), " +
                    "('Ana Costa', 'senha102', 'ana.costa@email.com');";
            statement.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
