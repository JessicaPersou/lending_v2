package com.postech.lending.client.service;

import com.postech.lending.client.dto.ClientDTO;
import java.util.List;

public interface ClientService {

    List<ClientDTO> findAll();
    ClientDTO clientById(Long id);
    List<ClientDTO> clientsByDocument(String document);
    List<ClientDTO> clientStatusProfileDisabled();
    ClientDTO createNewClient(ClientDTO clientDTO);
    ClientDTO clientUpdate(Long id, ClientDTO clientDTO);
    ClientDTO disableClient(Long id);

}
