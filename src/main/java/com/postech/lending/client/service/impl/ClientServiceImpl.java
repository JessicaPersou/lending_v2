package com.postech.lending.client.service.impl;

import com.postech.lending.client.dto.ClientDTO;
import com.postech.lending.client.model.Address;
import com.postech.lending.client.model.Client;
import com.postech.lending.client.model.enums.ProfileState;
import com.postech.lending.client.repository.ClientRepository;
import com.postech.lending.client.service.ClientService;
import com.postech.lending.infrastructure.exception.ErrorMessageException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public List<ClientDTO> findAll() {
        List<Client> clients = clientRepository.findAll();
        List<ClientDTO> dtoList = new ArrayList<>();

        for (Client client : clients) {
            List<Address> addressList = client.getAddressList();
            ClientDTO clientDTO = new ClientDTO(client, addressList);
            dtoList.add(clientDTO);
        }
        return dtoList;
    }

    @Override
    public ClientDTO clientById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ErrorMessageException(
                ErrorMessageException.Message.CLIENT_NOT_FOUND.getMessageEnum() + id + "."));
        return new ClientDTO(client, client.getAddressList());
    }

    public List<ClientDTO> clientsByDocument(String document) {
        var client = clientRepository.findClientsByDocument(document);
        if (!client.isEmpty()) {
            return client;
        }
        throw new ErrorMessageException(ErrorMessageException.Message.CLIENT_DOCUMENT_EMPTY.getMessageEnum());
    }

    @Override
    public List<ClientDTO> clientStatusProfileDisabled() {
        return clientRepository.findClientsByProfileState();
    }

    @Override
    public ClientDTO createNewClient(ClientDTO clientDTO) {
        Client client = new Client();

        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setBirthdate(clientDTO.getBirthdate());
        client.setDocument(clientDTO.getDocument());
        client.setEmail(clientDTO.getEmail());
        client.setPhone(clientDTO.getPhone());
        client.setDtCreated(LocalDate.now());
        client.setUserRole(clientDTO.getUserRole());
        client.setProfileState(ProfileState.ACTIVE);

        Client savedClient = clientRepository.save(client);
        clientDTO.setId(savedClient.getId());

        return new ClientDTO(savedClient);
    }

    @Override
    public ClientDTO clientUpdate(Long id, ClientDTO clientDTO) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente com o id + " + id + " não encontrado."));
        if (client.getProfileState() == ProfileState.DISABLED) {
            throw new ErrorMessageException(ErrorMessageException.Message.CLIENT_PROFILE_DISABLED.getMessageEnum());
        }
        BeanUtils.copyProperties(clientDTO, client, "id", "userRole");
        clientRepository.save(client);

        return new ClientDTO(client);
    }

    @Override
    public ClientDTO disableClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente com o id + " + id + " não encontrado."));
        if (client.getId() != null) {
            client.setDtProfileDisabled(LocalDate.now());
            client.setProfileState(ProfileState.DISABLED);
        }
        clientRepository.save(client);
        return new ClientDTO(client);
    }
}
