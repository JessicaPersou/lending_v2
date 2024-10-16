package com.postech.lending.client.service;

import com.postech.lending.client.dto.AddressDTO;
import java.util.List;

public interface AddressService {

    List<AddressDTO> findAddressByClientId(Long id);

    AddressDTO createNewAddress(Long clientId, AddressDTO addressDTO);

    AddressDTO updateAddress(Long idClient, Long idAddress, AddressDTO addressDTO);

    void deleteAddress(Long idClient, Long idAddress);
}
