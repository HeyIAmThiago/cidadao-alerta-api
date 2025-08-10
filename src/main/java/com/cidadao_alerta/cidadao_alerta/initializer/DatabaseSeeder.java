package com.cidadao_alerta.cidadao_alerta.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cidadao_alerta.cidadao_alerta.services.ReportService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final DataSource dataSource;
    private final ReportService reportService;

    public DatabaseSeeder(DataSource dataSource, ReportService reportService) {
        this.dataSource = dataSource;
        this.reportService = reportService;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();

            // Limpa usuários
            statement.executeUpdate("TRUNCATE TABLE user_app RESTART IDENTITY CASCADE");

            // Usuário padrão
            String sqlUser = "INSERT INTO user_app (name, password, email, role) VALUES " +
                    "('Thiago Monteiro', 'senha123', 'thiago28@gmail.com', 'ADMIN')";
            statement.executeUpdate(sqlUser);

            // Limpa reports
            statement.executeUpdate("TRUNCATE TABLE report CASCADE");

            // Reports
            String sqlReport = "INSERT INTO report (id, title, description, image_url) VALUES " +
                    "('a6c8e2c4-8d7d-4c0a-bf1e-6a7b3c2d1e4f', 'Denuncia 1', 'Descrição da denuncia 1', 'https://picsum.photos/200/300'), " +
                    "('b5d2f9a1-3c4e-42f9-8b2d-5f6e7c8a9b0c', 'Denuncia 2', 'Descrição da denuncia 2', 'https://picsum.photos/200/300'), " +
                    "('c1e7d4b8-5f3a-4d2e-9c8b-0a1f2e3d4c5b', 'Denuncia 3', 'Descrição da denuncia 3', 'https://picsum.photos/200/300'), " +
                    "('d9b3e7f2-6c5a-4e1b-8d3c-2f4a5b6c7e8f', 'Denuncia 4', 'Descrição da denuncia 4', 'https://picsum.photos/200/300'), " +
                    "('e2f4a7c9-1b3d-48e9-8f2a-6c5b7d8e9f0a', 'Denuncia 5', 'Descrição da denuncia 5', 'https://picsum.photos/200/300');";
            statement.executeUpdate(sqlReport);

            // Categorias
            String sqlCategory = "INSERT INTO category (description) VALUES " +
                    "('Enchente'), " +
                    "('Tiroteio'), " +
                    "('Arrastão'), " +
                    "('Traficantes');";
            statement.executeUpdate(sqlCategory);

            // Limpa comentários
            statement.executeUpdate("TRUNCATE TABLE comment CASCADE");

            // Comentários vinculados aos reports

            String sqlComments = ""
                    + "INSERT INTO comment (text, user_id, report_id, upvotes) VALUES "
                    + "('Isso é muito preocupante, espero que as autoridades resolvam logo.', "
                    + "(SELECT id FROM user_app WHERE email = 'thiago28@gmail.com'), "
                    + "'a6c8e2c4-8d7d-4c0a-bf1e-6a7b3c2d1e4f', 5), "
                    + "('Eu moro perto e realmente está bem perigoso.', "
                    + "(SELECT id FROM user_app WHERE email = 'thiago28@gmail.com'), "
                    + "'a6c8e2c4-8d7d-4c0a-bf1e-6a7b3c2d1e4f', 2), "
                    + "('Também vi essa situação ontem, precisa de atenção urgente.', "
                    + "(SELECT id FROM user_app WHERE email = 'thiago28@gmail.com'), "
                    + "'b5d2f9a1-3c4e-42f9-8b2d-5f6e7c8a9b0c', 7), "
                    + "('Tem muita gente reclamando disso no bairro.', "
                    + "(SELECT id FROM user_app WHERE email = 'thiago28@gmail.com'), "
                    + "'b5d2f9a1-3c4e-42f9-8b2d-5f6e7c8a9b0c', 3), "
                    + "('Concordo totalmente, já está passando dos limites.', "
                    + "(SELECT id FROM user_app WHERE email = 'thiago28@gmail.com'), "
                    + "'c1e7d4b8-5f3a-4d2e-9c8b-0a1f2e3d4c5b', 10), "
                    + "('Achei que era só boato, mas realmente aconteceu!', "
                    + "(SELECT id FROM user_app WHERE email = 'thiago28@gmail.com'), "
                    + "'d9b3e7f2-6c5a-4e1b-8d3c-2f4a5b6c7e8f', 1), "
                    + "('Muito triste ver isso acontecendo na nossa cidade.', "
                    + "(SELECT id FROM user_app WHERE email = 'thiago28@gmail.com'), "
                    + "'e2f4a7c9-1b3d-48e9-8f2a-6c5b7d8e9f0a', 4), "
                    + "('Precisamos nos mobilizar e cobrar ação das autoridades.', "
                    + "(SELECT id FROM user_app WHERE email = 'thiago28@gmail.com'), "
                    + "'e2f4a7c9-1b3d-48e9-8f2a-6c5b7d8e9f0a', 6);";

            statement.executeUpdate(sqlComments);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
