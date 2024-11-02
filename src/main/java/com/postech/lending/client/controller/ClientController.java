package com.postech.lending.client.controller;

import com.postech.lending.client.dto.ClientDTO;
import com.postech.lending.client.service.ClientService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<ClientDTO> allClients() {
        return clientService.findAll();
    }

    @GetMapping("/{id}")
    public ClientDTO clientById(@PathVariable Long id) {
        return clientService.clientById(id);
    }

    @GetMapping("/document")
    public List<ClientDTO> allClientsByDocument(@Param("document") String document) {
        return clientService.clientsByDocument(document);
    }

    @GetMapping("/profile")
    public List<ClientDTO> clientsProfile() {
        return clientService.clientStatusProfileDisabled();
    }

    @PostMapping
    public ClientDTO newClient(@Valid @RequestBody ClientDTO clientDTO) {
        ClientDTO newClient = clientService.createNewClient(clientDTO);
        return newClient;
    }

    @PutMapping("/{id}")
    public ClientDTO clientUpdate(@PathVariable Long id, @Valid @RequestBody ClientDTO clientDTO) {
        return clientService.clientUpdate(id, clientDTO);
    }

    @PostMapping("disabled/{id}")
    public ClientDTO disableClient(@PathVariable Long id) {
        return clientService.disableClient(id);
    }

}
