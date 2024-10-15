package com.postech.lending.client.controller;

import com.postech.lending.client.dto.AddressDTO;
import com.postech.lending.client.service.AddressService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private AddressService addressService;

    @GetMapping("/{id}")
    public ResponseEntity<List<AddressDTO>> AddressByClient(@PathVariable Long id){
        return ResponseEntity.ok(addressService.findAddressByClientId(id));
    }

    @PostMapping("{clientId}/")
    public ResponseEntity<AddressDTO> newAddress(@PathVariable Long clientId, @RequestBody AddressDTO addressDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(addressService.createNewAddress(clientId,addressDTO));
    }

    @PutMapping("{idClient}//{idAddress}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long idClient,@PathVariable Long idAddress,@RequestBody AddressDTO addressDTO){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.updateAddress(idClient, idAddress, addressDTO));
    }

    @DeleteMapping("{idClient}/address/{idAddress}")
    public ResponseEntity deleteAddress(@PathVariable Long idClient, @PathVariable Long idAddress){
        addressService.deleteAddress(idClient,idAddress);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Endereço excluído com sucesso.");
    }
}
