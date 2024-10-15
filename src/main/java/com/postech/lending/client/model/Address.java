package com.postech.lending.client.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(length = 30, nullable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private String street;

    @Column(name = "number", length = 5, nullable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private String number;

    @Column(name = "zipcode", length = 9, nullable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private String zipcode;

    @Column(name = "neighborhood", length = 20, nullable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private String neighborhood;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private String state;

    @Column(length = 20)
    private String city;

    @Column(name = "country", length = 20, nullable = false)
    @NotBlank(message = "Campo não pode ser vazio ou nulo")
    private String country;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Client.class)
    @JoinColumn(name = "client_id")
    private Client clientId;
}
