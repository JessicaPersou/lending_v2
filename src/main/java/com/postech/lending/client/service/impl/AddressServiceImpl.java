package com.postech.lending.client.service.impl;

import com.postech.lending.client.dto.AddressDTO;
import com.postech.lending.client.model.Address;
import com.postech.lending.client.model.Client;
import com.postech.lending.client.repository.AddressRepository;
import com.postech.lending.client.repository.ClientRepository;
import com.postech.lending.client.service.AddressService;
import com.postech.lending.common.exception.ErrorMessageException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    private final ClientRepository clientRepository;

    public AddressServiceImpl(AddressRepository addressRepository, ClientRepository clientRepository) {
        this.addressRepository = addressRepository;
        this.clientRepository = clientRepository;
    }

    public List<AddressDTO> findAddressByClientId(Long id) {
        Client clients = clientRepository.findById(id).orElseThrow(() -> new ErrorMessageException(
                ErrorMessageException.Message.CLIENT_NOT_FOUND.getMessageEnum() + id + "."));
        List<AddressDTO> dtoList = new ArrayList<>();

        for (Address address : clients.getAddressList()) {
            dtoList.add(new AddressDTO(address));
        }
        return dtoList;
    }

    @Override
    public AddressDTO createNewAddress(Long idClient, AddressDTO addressDTO) {
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new ErrorMessageException(
                        ErrorMessageException.Message.CLIENT_NOT_FOUND.getMessageEnum() + idClient
                                + ", endereço não pode ser cadastrado."));

        if (client.getAddressList().size() >= 3) {
            throw new ErrorMessageException(ErrorMessageException.Message.ADDRESS_EXCEEDED_LIMIT.getMessageEnum());
        }

        Address address = new Address();
        address.setStreet(addressDTO.getStreet());
        address.setNumber(addressDTO.getNumber());
        address.setZipcode(addressDTO.getZipcode());
        address.setNeighborhood(addressDTO.getNeighborhood());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
        address.setCountry(addressDTO.getCountry());

        address.setClientId(client);
        addressRepository.save(address);
        addressDTO.setId(address.getId());

        return new AddressDTO(address);
    }

    @Override
    public AddressDTO updateAddress(Long idClient, Long idAddress, AddressDTO addressDTO) {
        try {
            Client client = clientRepository.findById(idClient)
                    .orElseThrow(() -> new ErrorMessageException(
                            ErrorMessageException.Message.CLIENT_NOT_FOUND.getMessageEnum() + idClient
                                    + ", endereço não pode ser atualizado."));

            if (client != null) {
                Address address = addressRepository.findById(idAddress)
                        .orElseThrow(() -> new ErrorMessageException(
                                ErrorMessageException.Message.CLIENT_NOT_FOUND.getMessageEnum() + idAddress
                                        + ", endereço não pode ser cadastrado."));

                if (address != null) {
                    BeanUtils.copyProperties(addressDTO, address, "id");
                    addressRepository.save(address);
                    return new AddressDTO(address);
                }
            }
        } catch (ErrorMessageException e) {
            throw new ErrorMessageException(ErrorMessageException.Message.ADDRESS_NOT_UPDATE.getMessageEnum());
        }
        return addressDTO;
    }

    @Override
    public void deleteAddress(Long idClient, Long idAddress) {
        try {
            Client client = clientRepository.findById(idClient)
                    .orElseThrow(() -> new ErrorMessageException(
                            ErrorMessageException.Message.CLIENT_NOT_FOUND.getMessageEnum() + idClient
                                    + ", endereço não pode ser deletado."));
            if (client != null) {
                Address address = addressRepository.findById(idAddress)
                        .orElseThrow(() -> new ErrorMessageException(
                                ErrorMessageException.Message.CLIENT_NOT_FOUND.getMessageEnum() + idAddress
                                        + ", endereço não pode ser deletado."));

                if (address != null) {
                    addressRepository.delete(address);
                }
            }
        } catch (ErrorMessageException e) {
            throw new ErrorMessageException(ErrorMessageException.Message.ADDRESS_NOT_DELETED.getMessageEnum());
        }
    }
}
