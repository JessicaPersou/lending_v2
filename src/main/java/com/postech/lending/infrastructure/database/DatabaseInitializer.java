package com.postech.lending.infrastructure.database;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final DataSource dataSource;

    public DatabaseInitializer(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void run(String... args) throws Exception {
        try (Connection connection = dataSource.getConnection();
                Statement statement = connection.createStatement()) {

            // Inserindo dados na tabela client
            statement.execute("INSERT INTO client (id, first_name, last_name, birthdate, document, email, phone, dt_created, user_role, profile_state, dt_profile_disabled) VALUES " +
                    "(1, 'Emmanuel', 'Salinas', '2007-11-06', '03251148206', 'Emmanuel.Salinas@mail.com', '11933697637', '2024-11-04', 'USER', '1', NULL), " +
                    "(2, 'Andrew', 'Pavia', '2007-11-06', '57728968572', 'Andrew.Pavia@ig.com.br', '11955398883', '2024-11-04', 'USER', '1', NULL), " +
                    "(3, 'Adrian', 'Esparza', '2007-11-12', '70512088454', 'Adrian.Esparza@mail.com', '11990029326', '2024-11-04', 'USER', '1', NULL), " +
                    "(4, 'Florença', 'Paredes', '2007-11-10', '34594175015', 'Florença.Paredes@icloud.com', '1197130437', '2024-11-04', 'USER', '1', NULL), " +
                    "(5, 'Vanessa', 'Ochoa', '2007-11-07', '73074218525', 'Vanessa.Ochoa@gmail.com', '11922979718', '2024-11-04', 'USER', '1', NULL);");

            // Inserindo dados na tabela address
            statement.execute("INSERT INTO address (id, city, country, neighborhood, number, state, street, zipcode, client_id) VALUES " +
                    "(1, 'Joinville', 'Brasil', 'Christopher', 3932, 'SE', 'Av DRa Christopher', '02031-461', 1), " +
                    "(2, 'Belém', 'Brasil', 'Miguel', 4865, 'PB', 'Av DRa Miguel', '89040-909', 2), " +
                    "(3, 'Goiânia', 'Brasil', 'Antonia', 3211, 'ES', 'Av DRa Antonia', '99120-124', 3), " +
                    "(4, 'Belford Roxo', 'Brasil', 'Agustín', 1963, 'PE', 'Av DRa Agustín', '95080-121', 4), " +
                    "(5, 'Belo Horizonte', 'Brasil', 'Alexandre', 1382, 'PA', 'Av DRa Alexandre', '02030-010', 5);");

            // Inserindo dados na tabela analysis_credit
            statement.execute("INSERT INTO analysis_credit (requested_value, monthly_income, document, number_installment, status_analysis, client_id) VALUES " +
                    "(7000.00, 3500.00, '03251148206', 12, 'IN_PROGRESS', 1), " +
                    "(80000.00, 4000.00, '57728968572', 48, 'IN_PROGRESS', 2), " +
                    "(16000.00, 3000.00, '70512088454', 10, 'IN_PROGRESS', 3), " +
                    "(25000.00, 2500.00, '34594175015', 24, 'IN_PROGRESS', 4), " +
                    "(9000.00, 4500.00, '73074218525', 15, 'IN_PROGRESS',5);");
        }
    }
}

