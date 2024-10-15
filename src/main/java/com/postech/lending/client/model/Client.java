package com.postech.lending.client.model;

import com.postech.lending.client.model.enums.ProfileState;
import com.postech.lending.client.model.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client {


    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", length = 50, nullable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private String firstName;

    @Column(name = "last_name", length = 50, nullable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private String lastName;

    @Column(name = "birthdate")
    private Date birthdate;

    @Column(name = "document", length = 14, nullable = false, unique = true)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private String document;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private String email;

    @Column(name = "phone", length = 14)
    private String phone;

    @Column(name = "dt_created", nullable = false, updatable = false)
    private LocalDate dtCreated;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_role", nullable = false)
    private UserRole userRole;

    @OneToMany(mappedBy = "clientId")
    private List<Address> addressList = new ArrayList<>();

    @ToString.Include
    @Column(name = "profile_state")
    private ProfileState profileState;

    @Column(name = "dt_profile_disabled")
    private LocalDate dtProfileDisabled;

    public Client(long id) {
        this.id = id;
    }

}
