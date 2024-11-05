package com.postech.lending.client.controller;

import com.postech.lending.client.dto.AddressDTO;
import com.postech.lending.client.service.AddressService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{id}")
    public List<AddressDTO> addressByClient(@PathVariable Long id) {
        return addressService.findAddressByClientId(id);
    }

    @PostMapping("/{clientId}")
    public AddressDTO newAddress(@PathVariable Long clientId, @RequestBody AddressDTO addressDTO) {
        return addressService.createNewAddress(clientId, addressDTO);
    }

    @PutMapping("{idClient}/{idAddress}")
    public AddressDTO updateAddress(@PathVariable Long idClient, @PathVariable Long idAddress,
            @RequestBody AddressDTO addressDTO) {
        return addressService.updateAddress(idClient, idAddress, addressDTO);
    }

    @DeleteMapping("{idClient}/address/{idAddress}")
    public void deleteAddress(@PathVariable Long idClient, @PathVariable Long idAddress) {
        addressService.deleteAddress(idClient, idAddress);
    }
}
