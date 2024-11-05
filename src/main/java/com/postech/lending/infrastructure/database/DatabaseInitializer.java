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

            statement.execute("INSERT INTO client (first_name, last_name, birthdate, document, email, phone, dt_created, user_role, profile_state, dt_profile_disabled) VALUES " +
                    "('Emmanuel', 'Salinas', '2007-11-06', '03251148206', 'Emmanuel.Salinas@mail.com', '11933697637', '2024-11-04', 'USER', '1', NULL), " +
                    "('Andrew', 'Pavia', '2007-11-06', '57728968572', 'Andrew.Pavia@ig.com.br', '11955398883', '2024-11-04', 'USER', '1', NULL), " +
                    "('Adrian', 'Esparza', '2007-11-12', '70512088454', 'Adrian.Esparza@mail.com', '11990029326', '2024-11-04', 'USER', '1', NULL), " +
                    "('Florença', 'Paredes', '2007-11-10', '34594175015', 'Florença.Paredes@icloud.com', '1197130437', '2024-11-04', 'USER', '1', NULL), " +
                    "('Vanessa', 'Ochoa', '2007-11-07', '73074218525', 'Vanessa.Ochoa@gmail.com', '11922979718', '2024-11-04', 'USER', '1', NULL);");

            statement.execute("INSERT INTO address (city, country, neighborhood, number, state, street, zipcode, client_id) VALUES " +
                    "('Joinville', 'Brasil', 'Christopher', 3932, 'SE', 'Av DRa Christopher', '02031-461', 1), " +
                    "('Belém', 'Brasil', 'Miguel', 4865, 'PB', 'Av DRa Miguel', '89040-909', 2), " +
                    "('Goiânia', 'Brasil', 'Antonia', 3211, 'ES', 'Av DRa Antonia', '99120-124', 3), " +
                    "('Belford Roxo', 'Brasil', 'Agustín', 1963, 'PE', 'Av DRa Agustín', '95080-121', 4), " +
                    "('Belo Horizonte', 'Brasil', 'Alexandre', 1382, 'PA', 'Av DRa Alexandre', '02030-010', 5);");

        }
    }
}

