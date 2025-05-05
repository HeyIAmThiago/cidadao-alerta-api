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


            statement.executeUpdate("TRUNCATE TABLE user_app RESTART IDENTITY CASCADE");

            String sqlUser = "INSERT INTO user_app (name, password, email, role) VALUES " +
                    "('Thiago Monteiro', 'senha123', 'thiago28@gmail.com', 'ADMIN')";
            statement.executeUpdate(sqlUser);


            statement.executeUpdate("TRUNCATE TABLE report CASCADE");

            String sqlReport = "INSERT INTO report (id, title, description, image_url) VALUES " +
                    "('a6c8e2c4-8d7d-4c0a-bf1e-6a7b3c2d1e4f', 'Denuncia 1', 'Descrição da denuncia 1', 'https://picsum.photos/200/300'), " +
                    "('b5d2f9a1-3c4e-42f9-8b2d-5f6e7c8a9b0c', 'Denuncia 2', 'Descrição da denuncia 2', 'https://picsum.photos/200/300'), " +
                    "('c1e7d4b8-5f3a-4d2e-9c8b-0a1f2e3d4c5b', 'Denuncia 3', 'Descrição da denuncia 3', 'https://picsum.photos/200/300'), " +
                    "('d9b3e7f2-6c5a-4e1b-8d3c-2f4a5b6c7e8f', 'Denuncia 4', 'Descrição da denuncia 4', 'https://picsum.photos/200/300'), " +
                    "('e2f4a7c9-1b3d-48e9-8f2a-6c5b7d8e9f0a', 'Denuncia 5', 'Descrição da denuncia 5', 'https://picsum.photos/200/300');";
            statement.executeUpdate(sqlReport);


        } catch (Exception e) {
            e.printStackTrace();
        }

        // List<ReportDTORequest> reportsDtos = List.of(
        //         new ReportDTORequest("Denuncia 1", "Descrição da denuncia 1", "https://picsum.photos/200/300"),
        //         new ReportDTORequest("Denuncia 2", "Descrição da denuncia 2", "https://picsum.photos/200/300"),
        //         new ReportDTORequest("Denuncia 3", "Descrição da denuncia 3", "https://picsum.photos/200/300"),
        //         new ReportDTORequest("Denuncia 4", "Descrição da denuncia 4", "https://picsum.photos/200/300"),
        //         new ReportDTORequest("Denuncia 5", "Descrição da denuncia 5", "https://picsum.photos/200/300")
        // );

        // reportService.createAll(reportsDtos);
    }
}
