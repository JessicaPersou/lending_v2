package com.postech.lending.client.dto;

import com.postech.lending.client.model.Address;
import com.postech.lending.client.model.Client;
import com.postech.lending.client.model.enums.ProfileState;
import com.postech.lending.client.model.enums.UserRole;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class ClientDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private String document;
    private String email;
    private String phone;
    private LocalDate dtCreated;
    private UserRole userRole;
    private List<AddressDTO> addressList = new ArrayList<>();
    private ProfileState profileState;
    private LocalDate dtProfileDisabled;

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.birthdate = client.getBirthdate();
        this.document = client.getDocument();
        this.phone = client.getPhone();
        this.email = client.getEmail();
        this.dtCreated = client.getDtCreated();
        this.userRole = client.getUserRole();
        this.profileState = client.getProfileState();
        this.dtProfileDisabled = client.getDtProfileDisabled();
        this.addressList = new ArrayList<>();

        addressList.forEach(item -> {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setStreet(item.getStreet());
            addressDTO.setNumber(item.getNumber());
            addressDTO.setZipcode(item.getZipcode());
            addressDTO.setNeighborhood(item.getNeighborhood());
            addressDTO.setCity(item.getCity());
            addressDTO.setState(item.getState());
            addressDTO.setCountry(item.getCountry());
            addressDTO.setId(item.getId());

            this.addressList.add(addressDTO);
        });

    }

    public ClientDTO(Client client, List<Address> addressList ) {
        this.id = client.getId();
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.birthdate = client.getBirthdate();
        this.document = client.getDocument();
        this.phone = client.getPhone();
        this.dtCreated = client.getDtCreated();
        this.userRole = client.getUserRole();
        this.email = client.getEmail();
        this.profileState = client.getProfileState();
        this.dtProfileDisabled = client.getDtProfileDisabled();

        this.addressList = new ArrayList<>();

        if (addressList != null) {
            addressList.forEach(item -> {
                AddressDTO addressDTO = new AddressDTO(item);
                this.addressList.add(addressDTO);
            });
        }

    }

}
