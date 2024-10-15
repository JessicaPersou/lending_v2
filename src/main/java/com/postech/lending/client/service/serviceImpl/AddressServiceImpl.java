package com.postech.lending.client.service.serviceImpl;

import com.postech.lending.client.dto.AddressDTO;
import com.postech.lending.client.model.Address;
import com.postech.lending.client.model.Client;
import com.postech.lending.client.repository.AddressRepository;
import com.postech.lending.client.repository.ClientRepository;
import com.postech.lending.client.service.AddressService;
import com.postech.lending.infrastructure.exception.ErrorMessageException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<AddressDTO> findAddressByClientId(Long id){
        Client clients = clientRepository.findById(id).orElseThrow(() -> new ErrorMessageException(ErrorMessageException.Message.CLIENT_NOT_FOUND.getMessage() + id + "."));
        List<AddressDTO> dtoList = new ArrayList<>();

        for (Address address : clients.getAddressList() ) {
            dtoList.add(new AddressDTO(address));
        }
        return dtoList;
    }

    @Override
    public AddressDTO createNewAddress(Long idClient, AddressDTO addressDTO) {
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new ErrorMessageException(ErrorMessageException.Message.CLIENT_NOT_FOUND.getMessage() + idClient + ", endereço não pode ser cadastrado."));

        if (client.getAddressList().size() >= 3) {
            throw new ErrorMessageException(ErrorMessageException.Message.ADDRESS_EXCEEDED_LIMIT.getMessage());
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
        AddressDTO newAddressDTO = new AddressDTO(address);

        return newAddressDTO;
    }

    @Override
    public AddressDTO updateAddress(Long idClient, Long idAddress, AddressDTO addressDTO) {
        //Encontra o cliente e verifica se ele existe, caso contrario é lançado uma exception
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new ErrorMessageException(ErrorMessageException.Message.CLIENT_NOT_FOUND.getMessage() + idClient + ", endereço não pode ser atualizado."));

        //Verifica se o id do Address existe dentro da lista de endereço, se exixtir, ele faz a atualização
        Address address = addressRepository.findById(idAddress)
                .orElseThrow(() -> new ErrorMessageException(ErrorMessageException.Message.CLIENT_NOT_FOUND.getMessage() + idAddress + ", endereço não pode ser cadastrado."));
        try{
            if(address != null){
                BeanUtils.copyProperties(addressDTO, address, "id");
                addressRepository.save(address);
                return new AddressDTO(address);
            }
        }catch (ErrorMessageException e){
            throw new ErrorMessageException(ErrorMessageException.Message.ADDRESS_NOT_UPDATE.getMessage());
        }
        return null;
    }

    @Override
    public void deleteAddress(Long idClient, Long idAddress) {
        //Encontra o cliente e verifica se ele existe, caso contrario é lançado uma exception
        Client client = clientRepository.findById(idClient)
                .orElseThrow(() -> new ErrorMessageException(ErrorMessageException.Message.CLIENT_NOT_FOUND.getMessage() + idClient + ", endereço não pode ser deletado."));

        //Verifica se o id do Address existe dentro da lista de endereço, se exixtir, ele faz a atualização
        Address address = addressRepository.findById(idAddress)
                .orElseThrow(() -> new ErrorMessageException(ErrorMessageException.Message.CLIENT_NOT_FOUND.getMessage() + idAddress + ", endereço não pode ser deletado."));
        try{
            if(address != null){
                addressRepository.delete(address);
            }
        }catch (ErrorMessageException e){
            throw new ErrorMessageException(ErrorMessageException.Message.ADDRESS_NOT_DELETED.getMessage());
        }
    }
}
